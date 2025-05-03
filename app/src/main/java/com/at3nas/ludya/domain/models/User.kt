package com.at3nas.ludya.models

import java.util.UUID

class User(
    var email: String,
    var password: String
) {
    // Initializes userID //
    private var userId: UUID = UUID.randomUUID()
}