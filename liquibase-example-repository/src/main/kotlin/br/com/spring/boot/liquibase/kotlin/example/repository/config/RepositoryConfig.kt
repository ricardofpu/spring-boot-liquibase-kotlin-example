package br.com.spring.boot.liquibase.kotlin.example.repository.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@Configuration
@ComponentScan(basePackages = ["br.com.spring.boot.liquibase.kotlin.example"])
@EnableJpaRepositories(basePackages = ["br.com.spring.boot.liquibase.kotlin.example.repository"])
@EnableJpaAuditing
open class RepositoryConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    open fun dataSource(): DataSource = DataSourceBuilder.create().build()!!

}