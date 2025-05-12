package me.yunjisang.digitechrentalsystemserver.infrastructure.persistence.entity

import me.yunjisang.digitechrentalsystemserver.domain.model.Equipment
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "equipments")
data class EquipmentEntity(
    @Id
    val id : UUID = UUID.randomUUID(),

    val name : String,

    @Column("category_id")
    val categoryId : UUID,

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