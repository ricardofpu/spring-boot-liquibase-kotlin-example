package br.com.spring.boot.liquibase.kotlin.example.repository.config

import br.com.spring.boot.liquibase.kotlin.example.model.dummyAddress
import br.com.spring.boot.liquibase.kotlin.example.model.dummyPerson
import br.com.spring.boot.liquibase.kotlin.example.model.entity.Address
import br.com.spring.boot.liquibase.kotlin.example.model.entity.Person
import br.com.spring.boot.liquibase.kotlin.example.repository.AddressRepository
import br.com.spring.boot.liquibase.kotlin.example.repository.PersonRepository
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlConfig
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertNotNull

@RunWith(SpringRunner::class)
@ContextConfiguration
@SpringBootTest(classes = [RepositoryTestConfig::class])
@Sql(
    scripts = ["classpath:sqlScripts/clear_tables.sql"],
    config = SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED),
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
abstract class RepositoryBaseTest {

    @Autowired
    protected lateinit var personRepository: PersonRepository

    @Autowired
    protected lateinit var addressRepository: AddressRepository

    protected fun createPerson(): Person {
        val person = dummyPerson()

        val saved = personRepository.save(person)
        assertNotNull(saved)

        return saved
    }

    protected fun createAddress(address: Address = dummyAddress()): Address {
        val saved = addressRepository.save(address)
        assertNotNull(saved)

        return saved
    }

}