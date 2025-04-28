package com.saddik.leanRecipes.utils.dto

import java.time.Instant

class ErrorResponseDto {
    var message: String? = ""
    var timestamp: Instant? = null
    var requestid: String? = ""
    var data: Any? = null
}