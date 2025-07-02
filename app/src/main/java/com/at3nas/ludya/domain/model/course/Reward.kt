package com.at3nas.ludya.domain.model.course

import androidx.annotation.Keep

@Keep
data class Reward(
    val gainedExp: Int = 0,
    val gainedCoins: Int = 0,
)
