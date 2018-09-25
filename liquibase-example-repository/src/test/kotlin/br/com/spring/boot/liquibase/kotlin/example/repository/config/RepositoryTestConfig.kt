package br.com.spring.boot.liquibase.kotlin.example.repository.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@Configuration
@EnableAutoConfiguration
@Import(RepositoryConfig::class)
@ActiveProfiles(profiles = ["test", "postgresql"])
@ComponentScan(basePackages = ["br.com.spring.boot.liquibase.kotlin.example"])
@EntityScan("br.com.spring.boot.liquibase.kotlin.example.model")
open class RepositoryTestConfig

