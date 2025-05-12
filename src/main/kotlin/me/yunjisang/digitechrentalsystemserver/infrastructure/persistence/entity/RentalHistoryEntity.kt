package me.yunjisang.digitechrentalsystemserver.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table(name = "rental_histories")
data class RentalHistoryEntity(
    @Id
    val id : UUID = UUID.randomUUID(),

    @Column("equipment_id")
    val equipmentId : UUID,

    @Column("user_id")
    val userId : UUID,

    val rentedAt : LocalDateTime,

    val dueDate : LocalDateTime,

    val returnedAt : LocalDateTime? = null,

    val rentalStatus : RentStatus
) {
    enum class RentStatus {
        RENTAL,
        RETURNED,
        OVERDUE
    }
}