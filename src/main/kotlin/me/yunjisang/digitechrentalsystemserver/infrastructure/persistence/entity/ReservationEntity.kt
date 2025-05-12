package me.yunjisang.digitechrentalsystemserver.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("reservations")
data class ReservationEntity(
    @Id
    val id : UUID = UUID.randomUUID(),

    @Column("user_id")
    val userId : UUID,

    @Column("equipment_id")
    val equipmentId : UUID,

    val reservationAt : String,

    val status : String
) {
}