package com.at3nas.ludya.domain.model.course

import com.at3nas.ludya.R

enum class CourseCategory {
    ENGINEERING_TECH,
    EXACT_SCI,
    MEDICAL_SCI,
    SOCIAL_SCI,
    ARTS_HUM,
    LANGUAGES,
    OTHER,
}

fun stringToCourseCategory(string: String?): CourseCategory {
    return when (string) {
        "ENGINEERING_TECH" -> CourseCategory.ENGINEERING_TECH
        "EXACT_SCI" -> CourseCategory.EXACT_SCI
        "MEDICAL_SCI" -> CourseCategory.MEDICAL_SCI
        "SOCIAL_SCI" -> CourseCategory.SOCIAL_SCI
        "ARTS_HUM" -> CourseCategory.ARTS_HUM
        "LANGUAGES" -> CourseCategory.LANGUAGES
        "OTHER" -> CourseCategory.OTHER
        else -> {
            CourseCategory.OTHER
        }
    }
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

fun getCourseCategoryIcon(category: CourseCategory): Int {
    return when (category) {
        CourseCategory.OTHER -> R.drawable.coursecategory_other
        CourseCategory.ENGINEERING_TECH -> R.drawable.coursecategory_eng_tech
        CourseCategory.EXACT_SCI -> R.drawable.coursecategory_exact_sci
        CourseCategory.MEDICAL_SCI -> R.drawable.coursecategory_medical_sci
        CourseCategory.SOCIAL_SCI -> R.drawable.coursecategory_social_sci
        CourseCategory.ARTS_HUM -> R.drawable.coursecategory_arts_humanities
        CourseCategory.LANGUAGES -> R.drawable.coursecategory_languages
    }
}