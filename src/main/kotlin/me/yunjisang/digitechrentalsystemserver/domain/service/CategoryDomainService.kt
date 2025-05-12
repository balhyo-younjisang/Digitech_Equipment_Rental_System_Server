package me.yunjisang.digitechrentalsystemserver.domain.service

import me.yunjisang.digitechrentalsystemserver.domain.model.Category

class CategoryDomainService {

    private val forbiddenNames = setOf("기타", "없음", "기기")

    /**
     * 카테고리 이름이 유효한지 확인합니다.
     * 이름은 공백이 아니어야 하며, 금지된 이름 목록에 포함되지 않아야 합니다.
     *
     * @author Yunjisang
     * @since 2025-05-12
     * @param category Category 객체
     * @return 유효하면 true를 반환합니다.
     */
    fun isValidCategoryName(category: Category): Boolean {
        return category.name.isNotBlank() && !forbiddenNames.contains(category.name.trim())
    }

    /**
     * 주어진 이름이 기존 카테고리들과 중복되는지 확인합니다.
     * (대소문자 무시하고 비교)
     *
     * @author Yunjisang
     * @since 2025-05-12
     * @param newCategory 새로 추가하려는 Category 객체
     * @param existingCategories 기존 카테고리 목록
     * @return 이름이 중복되면 true를 반환합니다.
     */
    fun isDuplicateCategoryName(newCategory: Category, existingCategories: List<Category>): Boolean {
        return existingCategories.any {
            it.name.equals(newCategory.name, ignoreCase = true)
        }
    }
}
