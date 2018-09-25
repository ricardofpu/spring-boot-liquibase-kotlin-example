package br.com.spring.boot.liquibase.kotlin.example.repository

import br.com.spring.boot.liquibase.kotlin.example.model.dummyPerson
import br.com.spring.boot.liquibase.kotlin.example.model.randomUUID
import br.com.spring.boot.liquibase.kotlin.example.repository.config.RepositoryBaseTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class PersonRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save person and then find by id`() {
        val person = dummyPerson()

        val saved = personRepository.save(person)
        assertNotNull(saved)

        val find = personRepository.findById(saved.id).get()
        assertNotNull(find)
        assertEquals(saved.id, find.id)
        assertEquals(person.fullName, find.fullName)
        assertEquals(person.nickName, find.nickName)
        assertEquals(person.email, find.email)
        assertEquals(person.phoneNumber, find.phoneNumber)
        assertEquals(person.gender, find.gender)
        assertNotNull(find.createdAt)
    }

    @Test
    fun `shouldn't find person when not exists`() {
        val find = personRepository.findById(randomUUID()).isPresent
        assertFalse(find)
    }

}