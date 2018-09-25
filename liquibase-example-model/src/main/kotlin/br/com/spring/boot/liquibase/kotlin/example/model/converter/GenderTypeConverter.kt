package br.com.spring.boot.liquibase.kotlin.example.model.converter

import br.com.spring.boot.liquibase.kotlin.example.model.enums.GenderType
import java.util.*
import javax.persistence.AttributeConverter

open class GenderTypeConverter : AttributeConverter<GenderType, String> {
    override fun convertToDatabaseColumn(type: GenderType): String {
        return type.name
    }

    override fun convertToEntityAttribute(value: String?): GenderType? {
        return if (value == null) null else Arrays.stream(GenderType.values())
            .filter { p -> p.name == value }
            .findFirst()
            .orElseThrow { IllegalArgumentException() }
    }
}