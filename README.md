# Spring Boot and Liquibase example
This quickstart example let's show how to build Spring Boot Application using liquibase to database migration.

## Project Frameworks

- Kotlin
- Spring Boot
- JPA
- Maven
- PostgresSQL
- Docker
- Liquibase

## Configuration
We will starts configuration setting Spring Boot starter parent at our pom.xml

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
        <relativePath/>
    </parent>
```

After, we need to add a few dependencies like Liquibase, HikariCP and Postgres.

```xml
<dependencies>

    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
        <version>2.6.1</version>
    </dependency>

    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.1.1</version>
    </dependency>

    <dependency>
        <groupId>org.liquibase</groupId>
        <artifactId>liquibase-core</artifactId>
        <version>3.5.3</version>
    </dependency>

</dependencies>
```

## Liquibase Configuration
Liquibase needs some settings that will be placed in the `application.properties` file. In this example, we will configure only to tests.
The liquibase scripts are created in resource `./liquibase-example-repository/src/main/resources/config/liquibase`

## Application Properties
```properties
spring.datasource.jdbcUrl=jdbc:postgresql://localhost:5432/liquibase_example
spring.datasource.username=liquibase_example
spring.datasource.password=liquibase_example
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.hikari.data-source-properties.pool-name=liquibase-example
spring.datasource.hikari.maximum-pool-size=10

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false

spring.liquibase.change-log=classpath:config/liquibase/master.xml
spring.liquibase.default-schema=public
```

## Datasource
```kotlin
@Configuration
@ComponentScan(basePackages = ["br.com.spring.boot.liquibase.kotlin.example"])
@EnableJpaRepositories(basePackages = ["br.com.spring.boot.liquibase.kotlin.example.repository"])
@EnableJpaAuditing
open class RepositoryConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    open fun dataSource(): DataSource = DataSourceBuilder.create().build()!!

}
```

## Running tests
Use below commands to build the project and running tests

```
    docker-compose up
```

```
    mvn clean install
```

>**Note**: After each test, we are deleting all rows from tables through the configuration made in `RepositoryBaseTest.kt`. See below

```kotlin
@RunWith(SpringRunner::class)
@ContextConfiguration
@SpringBootTest(classes = [RepositoryTestConfig::class])
@Sql(
    scripts = ["classpath:sqlScripts/clear_tables.sql"],
    config = SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED),
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
abstract class RepositoryBaseTest
```

## Documentation
https://spring.io/  
http://www.liquibase.org/  
http://kotlinlang.org/  
https://docs.docker.com/compose/