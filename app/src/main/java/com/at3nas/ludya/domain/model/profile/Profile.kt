package com.at3nas.ludya.domain.model.profile

class Profile(
    //var avatar: Avatar?,
    var displayName: String = "",
    var level: Level = Level(),
    var currency: Currency = Currency()
) {
}