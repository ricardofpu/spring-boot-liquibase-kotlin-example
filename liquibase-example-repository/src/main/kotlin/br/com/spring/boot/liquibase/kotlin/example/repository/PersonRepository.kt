package br.com.spring.boot.liquibase.kotlin.example.repository

import br.com.spring.boot.liquibase.kotlin.example.model.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, String>