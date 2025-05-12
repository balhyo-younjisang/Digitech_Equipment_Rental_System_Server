package me.yunjisang.digitechrentalsystemserver.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class Reservation(
    val id : UUID,
    val userId : UUID,
    val equipmentId : UUID,
    val reservationAt : LocalDateTime,
    val status : ReservationStatus
){
    enum class ReservationStatus {
        WAITING,
        CANCELLED,
        FULFILLED
    }
}