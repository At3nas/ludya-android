package com.at3nas.ludya.domain.model

class Level(
    var level: Int = 1,
    var currentExp: Double = 0.0,
    var maxExp: Double = 1.0
) {
    fun levelUp() {
        if (currentExp > maxExp) {
            level++
        }
    }
}