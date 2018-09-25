package br.com.spring.boot.liquibase.kotlin.example.repository

import br.com.spring.boot.liquibase.kotlin.example.model.dummyAddress
import br.com.spring.boot.liquibase.kotlin.example.model.randomUUID
import br.com.spring.boot.liquibase.kotlin.example.repository.config.RepositoryBaseTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class AddressRepositoryTest : RepositoryBaseTest() {

    @Test
    fun `should save address and then find by id`() {
        val person = createPerson()

        val address = dummyAddress(person = person)

        val saved = addressRepository.save(address)
        assertNotNull(saved)

        val find = addressRepository.findById(saved.id).get()
        assertNotNull(find)
        assertEquals(saved.id, find.id)
        assertEquals(address.address, find.address)
        assertEquals(address.city, find.city)
        assertEquals(address.country, find.country)
        assertEquals(address.number, find.number)
        assertEquals(address.state, find.state)
        assertEquals(address.complement, find.complement)
        assertEquals(address.zipCode, find.zipCode)
        assertEquals(address.district, find.district)
        assertNotNull(find.createdAt)
    }

    fun `shouldn't find address when not exists`() {
        val find = addressRepository.findById(randomUUID()).isPresent
        assertFalse(find)
    }
}