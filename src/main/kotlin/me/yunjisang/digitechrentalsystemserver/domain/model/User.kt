package me.yunjisang.digitechrentalsystemserver.domain.model

import java.util.UUID

data class User(
    val id : UUID,
    val name : String,
    val phone : String,
    val email : String,
    val role: UserRole
) {
    enum class UserRole {
        ADMIN,
        USER
    }
}