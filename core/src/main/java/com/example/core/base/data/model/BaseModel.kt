package com.example.core.base.data.model

open class BaseModel<T>(
    var code: String? = null,
    var message: String? = null,
    var numberOfForCast: String? = null,
    var list: T? = null
)