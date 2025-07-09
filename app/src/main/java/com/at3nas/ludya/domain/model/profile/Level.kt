package com.at3nas.ludya.domain.model.profile

class Level(
    var level: Int = 1,
    var currentExp: Double = 0.0,
    val totalExp: Double = 0.5 * level
) {
    fun levelUp() {
        if (currentExp > totalExp) {
            level++
            currentExp = 0.0
        }
    }
}