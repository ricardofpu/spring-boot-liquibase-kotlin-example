package br.com.spring.boot.liquibase.kotlin.example.repository

import br.com.spring.boot.liquibase.kotlin.example.model.dummyAddress
import br.com.spring.boot.liquibase.kotlin.example.model.dummyPerson
import br.com.spring.boot.liquibase.kotlin.example.model.randomUUID
import br.com.spring.boot.liquibase.kotlin.example.repository.config.RepositoryBaseTest
import org.junit.Test
import org.springframework.dao.DataIntegrityViolationException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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

    @Test
    fun `should exists person`() {
        val person = createPerson()

        val exists = personRepository.existsById(person.id)
        assertTrue(exists)
    }

    @Test
    fun `shouldn't exists person when id is invalid`() {
        val exists = personRepository.existsById(randomUUID())
        assertFalse(exists)
    }

    @Test
    fun `should delete person`() {
        val person = createPerson()

        personRepository.delete(person)

        val find = personRepository.findById(person.id).isPresent
        assertFalse(find)
    }

    @Test(expected = DataIntegrityViolationException::class)
    fun `shouldn't delete person when exists address`() {
        val person = createPerson()

        createAddress(dummyAddress(person = person))

        personRepository.delete(person)
    }

}