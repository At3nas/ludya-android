package com.at3nas.ludya.domain.model

class Profile(
    var name: String,
    var level: Int,
    var exp: Double,
    var user: User,
    var currency: Currency
) {



}