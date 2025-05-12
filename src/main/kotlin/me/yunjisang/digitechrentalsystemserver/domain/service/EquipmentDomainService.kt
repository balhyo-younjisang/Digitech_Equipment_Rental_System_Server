package me.yunjisang.digitechrentalsystemserver.domain.service

import me.yunjisang.digitechrentalsystemserver.domain.model.Equipment
import java.util.UUID

class EquipmentDomainService {

    /**
     * 대여 가능한 장비인지 확인합니다.
     * 장비의 available 값이 true 이고, condition이 AVAILABLE 인지 확인합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param equipment Equipment 대여 상태를 확인할 장비
     * @return 대여 가능하다면 true, 대여 불가능하다면 false 를 반환합니다.
     */
    fun isRentable(equipment: Equipment): Boolean {
        return equipment.available && equipment.condition == Equipment.EquipmentStatus.AVAILABLE
    }

    /**
     * 장비의 상태를 대여로 변경합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param equipment Equipment 대여할 장비
     * @param userId UUID 대여를 한 사용자의 UUID
     * @return 상태를 변경한 장비를 반환합니다.
     * @throws String 대여가 불가능한 장비라면 에러 메시지를 반환합니다.
     */
    fun markAsRented(equipment: Equipment, userId: UUID): Equipment {
        require(isRentable(equipment)) { "Equipment is not available for rent." }
        return equipment.copy(
            available = false,
            condition = Equipment.EquipmentStatus.RENTED
        )
    }

    /**
     * 장비의 상태를 반환됨으로 변경합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param equipment Equipment 반환할 장비
     * @return 상태를 변경한 장비를 반환합니다.
     * @throws String 대여가 불가능한 장비라면 에러 메시지를 반환합니다.
     */
    fun markAsReturned(equipment: Equipment): Equipment {
        require(equipment.condition == Equipment.EquipmentStatus.RENTED) { "Equipment was not rented." }
        return equipment.copy(
            available = true,
            condition = Equipment.EquipmentStatus.RETURNED
        )
    }

    /**
     * 장비의 상태를 수리 중으로 변경합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param equipment Equipment 변경할 장비
     * @return 상태를 변경한 장비를 반환합니다.
     * @throws String 대여가 불가능한 장비라면 에러 메시지를 반환합니다.
     */
    fun markAsUnderRepair(equipment: Equipment): Equipment {
        require(equipment.condition != Equipment.EquipmentStatus.DAMAGED) { "Equipment is already damaged." }
        return equipment.copy(condition = Equipment.EquipmentStatus.UNDER_REPAIR)
    }

    /**
     * 장비의 상태를 수리 중에서 사용 가능함으로 변경합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param equipment Equipment 변경할 장비
     * @return 상태를 변경한 장비를 반환합니다.
     * @throws String 대여가 불가능한 장비라면 에러 메시지를 반환합니다.
     */
    fun markAsRepaired(equipment: Equipment): Equipment {
        require(equipment.condition == Equipment.EquipmentStatus.UNDER_REPAIR) { "Equipment is not under repair." }
        return equipment.copy(condition = Equipment.EquipmentStatus.AVAILABLE)
    }

    /**
     * 장비의 상태를 장기 대여로 변경합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param equipment Equipment 변경할 장비
     * @return 상태를 변경한 장비를 반환합니다.
     * @throws String 대여가 불가능한 장비라면 에러 메시지를 반환합니다.
     */
    fun markAsLongTerm(equipment: Equipment): Equipment {
        require(equipment.condition == Equipment.EquipmentStatus.RENTED) { "Equipment is not rented." }
        return equipment.copy(condition = Equipment.EquipmentStatus.LONG_TERM_RENTED)
    }

    /**
     * 장비의 상태가 대여 중인지 확인합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param equipment Equipment 상태를 확인할 장비
     * @return 대여 중인 장비라면 true, 대여 중인 장비가 아니라면 false를 반환
     */
    fun isCurrentlyRented(equipment: Equipment): Boolean {
        return equipment.condition == Equipment.EquipmentStatus.RENTED || equipment.condition == Equipment.EquipmentStatus.LONG_TERM_RENTED
    }

    /**
     * 장비의 상태를 장기 대여 중으로 변경합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param equipment Equipment 변경할 장비
     * @param userId UUID 장기 대여한 사용자의 UUID
     * @return 상태를 변경한 장비를 반환합니다.
     */
    fun markAsLongTerm(equipment: Equipment, userId: UUID): Equipment {
        return equipment.copy(condition = Equipment.EquipmentStatus.LONG_TERM_RENTED)
    }
}
