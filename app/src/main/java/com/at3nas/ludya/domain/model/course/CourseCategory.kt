package com.at3nas.ludya.domain.model.course

import com.at3nas.ludya.R

enum class CourseCategory {
    OTHER,
    ENGINEERING_TECH,
    EXACT_SCI,
    MEDICAL_SCI,
    SOCIAL_SCI,
    ARTS_HUM,
    LANGUAGES,
}

fun localizeCourseCategory(category: CourseCategory): Int {
    return when (category) {
        CourseCategory.OTHER -> R.string.category_other
        CourseCategory.ENGINEERING_TECH -> R.string.category_tech
        CourseCategory.EXACT_SCI -> R.string.category_exact_sci
        CourseCategory.MEDICAL_SCI -> R.string.category_medical_sci
        CourseCategory.SOCIAL_SCI -> R.string.category_social_sci
        CourseCategory.ARTS_HUM -> R.string.category_arts_humanities
        CourseCategory.LANGUAGES -> R.string.category_languages
    }
}