package cn.edu.hit.coursety.dao

import java.sql.ResultSet
import java.time.ZonedDateTime
import java.util.Date

abstract class BaseDao<T> {

    abstract val dataBaseFieldNameMapper: Map<String, String>

    fun String.dataBaseFieldName(): String? {
        return dataBaseFieldNameMapper[this]
    }

    fun String.dataClassFieldName(): String? {
        return dataBaseFieldNameMapper.entries.find { it.value == this }?.key
    }

    abstract val mapRow: (ResultSet, Int) -> T

    fun ResultSet.getStringOrNull(columnLabel: String?): String? {
        val value = getString(columnLabel)
        return if (wasNull()) {
            null
        } else {
            value
        }
    }

    fun ResultSet.getDateFromString(columnLabel: String?): Date? {
        val passwordChangedAt = getString(columnLabel) ?: return null
        val zonedDateTime = ZonedDateTime.parse(passwordChangedAt)
        val instant = zonedDateTime.toInstant()
        return Date.from(instant)
    }
}