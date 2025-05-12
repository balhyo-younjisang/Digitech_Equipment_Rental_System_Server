package me.yunjisang.digitechrentalsystemserver.domain.service

import me.yunjisang.digitechrentalsystemserver.domain.model.RentalHistory
import java.time.LocalDateTime

class RentalHistoryDomainService {
    /**
     * 반납 완료 여부를 확인합니다.
     * returnedAt 필드가 존재하고, rentalStatus가 RETURNED 라면 true를 반환합니다.
     *
     * @author Yun jisang
     * @param rental RentalHistory 도메인 객체
     * @return 반납 완료되었다면 true, 반납 완료되지 않았다면 false 반환
     */
    fun isReturned(rental: RentalHistory): Boolean {
        return rental.returnedAt != null && rental.rentalStatus == RentalHistory.RentalStatus.RETURNED
    }

    /**
     * dueDate 를 기준으로 연체 여부를 판단합니다.
     *
     * @author Yun jisang
     * @param rental RentalHistory 도메인 객체
     * @param now 현재 localDateTime
     * @return 연체되었다면 true, 연체되지 않았다면 false 반환
     */
    fun isOverdue(rental: RentalHistory, now: LocalDateTime = LocalDateTime.now()): Boolean {
        if (isReturned(rental)) return false
        return now.isAfter(rental.dueDate)
    }

    /**
     * 연체 상태로 변경합니다.
     *
     * @author Yun jisang
     * @param rental RentalHistory 도메인 객체
     * @return 변경된 도메인 객체
     * @throws String 이미 반환된 장비라면 에러 메시지를 반환합니다.
     */
    fun markAsOverdue(rental: RentalHistory): RentalHistory {
        require(!isReturned(rental)) { "Returned rental cannot be marked as overdue." }
        return rental.copy(rentalStatus = RentalHistory.RentalStatus.OVERDUE)
    }

    /**
     * 반납 상태로 변경합니다.
     *
     * @author Yun jisang
     * @param rental RentalHistory 도메인 객체
     * @return 변경된 도메인 객체
     * @throws String 이미 반환된 장비라면 에러 메시지를 반환합니다.
     */
    fun markAsReturn(rental: RentalHistory, returnedAt: LocalDateTime = LocalDateTime.now()): RentalHistory {
        require(!isReturned(rental)) { "Equipment already returned." }

        val status = if (returnedAt.isAfter(rental.dueDate)) {
            RentalHistory.RentalStatus.OVERDUE
        } else {
            RentalHistory.RentalStatus.RETURNED
        }

        return rental.copy(
            returnedAt = returnedAt,
            rentalStatus = status
        )
    }

    /**
     * 대여 시작 시 Rental 상태로 초기화합니다.
     *
     * @author Yun jisang
     * @param rental RentalHistory 도메인 객체
     * @return 초기화된 도메인 객체
     */
    fun startRental(rental: RentalHistory): RentalHistory {
        return rental.copy(
            returnedAt = null,
            rentalStatus = RentalHistory.RentalStatus.RENTAL
        )
    }
}