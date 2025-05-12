package me.yunjisang.digitechrentalsystemserver.domain.service

import me.yunjisang.digitechrentalsystemserver.domain.model.User

class UserDomainService {
    /**
     * 사용자가 관리자 권한인지 확인
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param user User 확인할 사용자
     * @return 사용자가 관리자라면 true 반환
     */
    fun isAdmin(user: User): Boolean {
        return user.role == User.UserRole.ADMIN
    }

    /**
     * 사용자의 정보를 변경합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param user  User 변경할 사용자
     * @param name  String 변경할 사용자 이름
     * @param phone  String 변경할 사용자 전화번호
     * @param email  String 변경할 사용자 이메일
     */
    fun updateUserInfo(
        user: User,
        name: String? = null,
        phone: String? = null,
        email: String? = null
    ): User {
        return user.copy(
            name = name ?: user.name,
            phone = phone ?: user.phone,
            email = email ?: user.email
        )
    }

    /**
     * 사용자의 이메일이 올바른지 확인합니다.
     * 이메일의 도메인은 sdh.hs.kr 이여야 합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param user User 확인할 사용자
     * @return 이메일이 올바른 형식이라면 true를 반환합니다.
     */
    fun isValidEmail(user: User): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@sdh\\.hs\\.kr$".toRegex()
        return user.email.matches(emailRegex)
    }

    /**
     * 전화번호가 국내 전화번호 형식인지 확인합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param user user 전화번호를 확인할 사용자
     * @return 전화번호가 올바른 형식이라면 true를 반환합니다.
     */
    fun isValidPhone(user: User): Boolean {
        val phoneRegex = "^01[0-9]-?\\d{3,4}-?\\d{4}$".toRegex()
        return user.phone.matches(phoneRegex)
    }

    /**
     * 사용자의 권한을 변경합니다.
     *
     * @author Yun jisang
     * @since 2025-05-12
     * @param user User 변경할 대상 사용자
     * @param newRole User.UserRole 변경할 권한
     * @return User 권한을 변경한 사용자
     */
    fun changeRole(user: User, newRole: User.UserRole): User {
        return user.copy(role = newRole)
    }
}