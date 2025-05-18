package com.at3nas.ludya.utils

// Function | Capitalize string //
fun capitalizeText(text: String): String {
    if (text[0].isLowerCase()) {
        return text.replaceFirstChar {
            it.uppercase()
        }
    }
    return text
}