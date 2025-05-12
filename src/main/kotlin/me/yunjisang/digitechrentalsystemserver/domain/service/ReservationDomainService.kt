package me.yunjisang.digitechrentalsystemserver.domain.service

import me.yunjisang.digitechrentalsystemserver.domain.model.Reservation
import java.time.LocalDateTime

class ReservationDomainService {

    /**
     * 예약이 취소 가능한 상태인지 확인합니다.
     * WAITING 상태일 경우에만 취소가 가능합니다.
     *
     * @author Yunjisang
     * @since 2025-05-12
     * @param reservation Reservation 객체
     * @return 예약이 취소 가능하다면 true를 반환합니다.
     */
    fun isCancelable(reservation: Reservation): Boolean {
        return reservation.status == Reservation.ReservationStatus.WAITING
    }

    /**
     * 예약을 취소 처리합니다.
     * 예약 상태가 WAITING이 아니면 예외가 발생합니다.
     *
     * @author Yunjisang
     * @since 2025-05-12
     * @param reservation Reservation 객체
     * @return 예약 상태가 CANCELLED로 변경된 새 Reservation 객체를 반환합니다.
     * @throws String 예약이 취소가 불가능하다면 에러 메시지를 반환합니다.
     */
    fun cancel(reservation: Reservation): Reservation {
        require(isCancelable(reservation)) { "Reservation cannot be cancelled." }
        return reservation.copy(status = Reservation.ReservationStatus.CANCELLED)
    }

    /**
     * 예약을 이행(FULFILLED) 처리합니다.
     * WAITING 상태일 때만 이행 가능합니다.
     *
     * @author Yunjisang
     * @since 2025-05-12
     * @param reservation Reservation 객체
     * @return 예약 상태가 FULFILLED로 변경된 새 Reservation 객체를 반환합니다.
     * @throws String 예약 상태가 WAITING이 아니라면 에러 메시지를 반환합니다.
     */
    fun fulfill(reservation: Reservation): Reservation {
        require(reservation.status == Reservation.ReservationStatus.WAITING) { "Only waiting reservations can be fulfilled." }
        return reservation.copy(status = Reservation.ReservationStatus.FULFILLED)
    }

    /**
     * 예약이 오래 되어 만료되었는지 확인합니다.
     * 1달 이상 예약 상태일 시 만료됩니다.
     *
     * @author Yunjisang
     * @since 2025-05-12
     * @param reservation Reservation 객체
     * @param now 현재 시간 (기본값은 LocalDateTime.now())
     * @return 예약이 만료되었다면 true를 반환합니다.
     */
    fun isExpired(reservation: Reservation, now: LocalDateTime = LocalDateTime.now()): Boolean {
        return reservation.status == Reservation.ReservationStatus.WAITING &&
                reservation.reservationAt.plusMonths(1).isBefore(now)
    }
}
