package br.com.spring.boot.liquibase.kotlin.example.model

import br.com.spring.boot.liquibase.kotlin.example.model.entity.Address
import br.com.spring.boot.liquibase.kotlin.example.model.entity.Person
import br.com.spring.boot.liquibase.kotlin.example.model.enums.GenderType

const val EXCEPTION_TEST_FAILED_MESSAGE = "Your test failed because your business rule broke. Please check this."

fun dummyPerson(): Person =
    Person(
        fullName = "Ricardo Borges",
        nickName = "Ricardo",
        email = "ricardoborges@test.com",
        gender = GenderType.MALE,
        phoneNumber = "3499998888"
    )

fun dummyAddress(
    address: String = "Rua Moderna",
    city: String = "Udia",
    country: String = "Brazil",
    number: String = "100",
    zipCode: String = "38000-000",
    complement: String? = null,
    district: String? = null,
    state: String = "MG",
    person: Person = dummyPerson()
): Address =
    Address(
        address = address,
        city = city,
        country = country,
        number = number,
        zipCode = zipCode,
        complement = complement,
        district = district,
        state = state,
        person = person
    )