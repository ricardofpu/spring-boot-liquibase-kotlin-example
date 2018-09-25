package br.com.spring.boot.liquibase.kotlin.example.model.entity

import br.com.spring.boot.liquibase.kotlin.example.model.converter.GenderTypeConverter
import br.com.spring.boot.liquibase.kotlin.example.model.entity.DBEntity
import br.com.spring.boot.liquibase.kotlin.example.model.enums.GenderType
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "person")
data class Person(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = "",

    val fullName: String,

    val nickName: String,

    val phoneNumber: String,

    val email: String?,

    @Convert(converter = GenderTypeConverter::class)
    val gender: GenderType

) : DBEntity() {

    @OneToOne(mappedBy = "person", targetEntity = Address::class, fetch = FetchType.LAZY)
    lateinit var address: Address

}