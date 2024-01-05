package cn.edu.hit.coursety.dao

import java.sql.ResultSet

interface IDao<T> {
    companion object : IDaoCompanionObject {
        //        val dataBaseFieldNameMapper: Map<String, String> = mapOf()
        override val dataBaseFieldNameMapper: Map<String, String>
            get() = mapOf()
    }

    val mapRow: (ResultSet, Int) -> T
}

interface IDaoCompanionObject {
    val dataBaseFieldNameMapper: Map<String, String>

    fun String.dataBaseFieldName(): String? {
        return dataBaseFieldNameMapper[this]
    }

    fun String.dataClassFieldName(): String? {
        return CourseDao.dataBaseFieldNameMapper.entries.find { it.value == this }?.key
    }
}