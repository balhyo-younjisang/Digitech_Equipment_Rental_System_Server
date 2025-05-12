package me.yunjisang.digitechrentalsystemserver.domain.model

import java.util.UUID

data class Category(
    val id : UUID,
    val name : String,
    val imagePath : String? = null
)