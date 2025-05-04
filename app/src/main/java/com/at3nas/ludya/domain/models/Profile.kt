package com.at3nas.ludya.domain.models

class Profile(
    var name: String,
    var level: Int,
    var exp: Double,
    var user: User,
    var currency: Currency
) {



}