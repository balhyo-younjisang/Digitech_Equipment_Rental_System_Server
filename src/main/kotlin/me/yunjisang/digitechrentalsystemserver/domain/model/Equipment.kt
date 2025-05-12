package me.yunjisang.digitechrentalsystemserver.domain.model

import java.util.UUID

data class Equipment(
    val id : UUID,
    val name : String,
    val category : Category,
    val available : Boolean,
    val condition : EquipmentStatus,
) {
    enum class EquipmentStatus {
        AVAILABLE,
        RENTED,
        DAMAGED,
        UNDER_REPAIR,
        LONG_TERM_RENTED,
        RETURNED
    }
}