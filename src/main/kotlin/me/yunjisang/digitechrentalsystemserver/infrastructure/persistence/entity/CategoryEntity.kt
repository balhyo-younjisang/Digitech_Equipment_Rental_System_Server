package me.yunjisang.digitechrentalsystemserver.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "categories")
data class CategoryEntity(
    @Id
    val id : UUID = UUID.randomUUID(),

    val name : String,

    val imagePath : String? = null,
)