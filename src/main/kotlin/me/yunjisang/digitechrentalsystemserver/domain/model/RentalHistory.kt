package me.yunjisang.digitechrentalsystemserver.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class RentalHistory(
    val id : UUID,
    val equipmentId : UUID,
    val userId : UUID,
    val rentedAt : LocalDateTime,
    val dueDate : LocalDateTime,
    val returnedAt : LocalDateTime? = null,
    val rentalStatus : RentalStatus
) {
    enum class RentalStatus {
        RENTAL,
        RETURNED,
        OVERDUE
    }
}