package me.yunjisang.digitechrentalsystemserver.infrastructure.persistence.entity

import me.yunjisang.digitechrentalsystemserver.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "users")
data class UserEntity(
    @Id
    val id : UUID = UUID.randomUUID(),

    val name : String,

    val phone : String,

    val email : String,

    val role : UserRole
) {
    enum class UserRole {
        ADMIN,
        USER
    }
}