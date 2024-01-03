package cn.edu.hit.coursety.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlin.math.max

class ApiFeatures<T>(private val query: Map<String, String>, private val dataBaseFieldNameMapper: Map<String, String>) {
    private var featureQuerySql = ""
    private var featureParamValues = arrayOf<String>()

    private var limitFields: String? = null

    var queryResultMaps: List<Map<*, *>> = listOf()
        private set

    fun filter(): ApiFeatures<T> {

        val queryFilter = query.filterKeys {
            it !in listOf("page", "sort", "limit", "fields")
        }

        val conditions = queryFilter.keys.joinToString("AND ") { key -> "$key = ?" }

        if (conditions.isNotEmpty()) {
            featureQuerySql += " WHERE $conditions"
            featureParamValues += queryFilter.values
        }

        return this;
    }

    fun sort(): ApiFeatures<T> {

        val sort = query["sort"]
        val sortKeywords = sort?.split(",") ?: listOf()

        val sorts = sortKeywords.map { keyword ->
            val isDescending = keyword.startsWith("-")
            val property = keyword.removePrefix("-")
            (property.dataBaseFieldName() ?: "") to if (isDescending) "ASC" else "DESC"
        }
            .filter { (property, _) ->
                property.isNotEmpty()
            }

        if (sorts.isNotEmpty()) {
            println(sorts)
            featureQuerySql += " ORDER BY ${sorts.joinToString(", ") { (property, sortType) -> "$property $sortType" }}"
        }

        return this;
    }

    fun limitFields(): ApiFeatures<T> {
        limitFields = query["fields"]

        return this;
    }

    fun paginate(): ApiFeatures<T> {

        val page = query["page"]?.toIntOrNull() ?: 0;
        val limitRowCount = query["limit"]?.toIntOrNull() ?: 100;
        val limitOffset = max((page - 1) * limitRowCount, 0)

        featureQuerySql += if (limitRowCount < 0) {
            " LIMIT $limitOffset, 100"
        } else {
            " LIMIT $limitOffset, $limitRowCount"
        }

        return this;
    }

    fun execute(queryBlock: (featureQuerySql: String, featureParamValues: Array<String>) -> List<T>): ApiFeatures<T> {

        val queryResults = queryBlock(this.featureQuerySql, this.featureParamValues)

        val objectMapper = jacksonObjectMapper()
        queryResultMaps = queryResults.map { item ->
            val itemMap = objectMapper.convertValue(item, Map::class.java)
            itemMap
        }

        limitFields?.let {
            val fields = limitFields!!.split(",")
            queryResultMaps = queryResultMaps!!.map { queryResultMap ->
                val newQueryResultMap = queryResultMap.filterKeys { it in fields }
                newQueryResultMap
            }
        }

        return this
    }

    private fun String.dataBaseFieldName(): String? {
        return dataBaseFieldNameMapper[this]
    }


}