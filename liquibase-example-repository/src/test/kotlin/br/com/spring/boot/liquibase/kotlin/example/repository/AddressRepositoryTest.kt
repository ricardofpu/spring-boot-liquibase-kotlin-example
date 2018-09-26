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
        val address = createAddress(dummyAddress(person = person))

        val find = addressRepository.findById(address.id).get()
        assertNotNull(find)
        assertEquals(address.id, find.id)
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

    @Test
    fun `shouldn't find address when not exists`() {
        val find = addressRepository.findById(randomUUID()).isPresent
        assertFalse(find)
    }

    @Test
    fun `should updated address`() {
        val person = createPerson()
        val address = createAddress(dummyAddress(person = person))

        val saved = addressRepository.save(address)
        assertNotNull(saved)

        val updatedAddress = saved.copy(
            address = "new address",
            number = "500",
            zipCode = "99999",
            country = "New City",
            state = "New state"
        )
        updatedAddress.createdAt = saved.createdAt

        val updated = addressRepository.save(updatedAddress)

        val find = addressRepository.findById(updated.id).get()
        assertNotNull(find)
        assertEquals(saved.id, find.id)
        assertEquals(updatedAddress.address, find.address)
        assertEquals(updatedAddress.city, find.city)
        assertEquals(updatedAddress.country, find.country)
        assertEquals(updatedAddress.number, find.number)
        assertEquals(updatedAddress.state, find.state)
        assertEquals(updatedAddress.complement, find.complement)
        assertEquals(updatedAddress.zipCode, find.zipCode)
        assertEquals(updatedAddress.district, find.district)
        assertNotNull(find.createdAt)
        assertNotNull(find.updatedAt)
    }

    @Test
    fun `should delete address`() {
        val person = createPerson()
        val address = createAddress(dummyAddress(person = person))

        val deleted = addressRepository.delete(address)
        assertNotNull(deleted)

        val find = addressRepository.findById(address.id).isPresent
        assertFalse(find)
    }

}