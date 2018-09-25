package br.com.spring.boot.liquibase.kotlin.example.model.enums

enum class GenderType(val value: String) {
    MALE("M"),
    FEMALE("F");

    companion object {
        fun from(findValue: String): GenderType = GenderType.values().first { it.value == findValue }
    }
}