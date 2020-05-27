package com.zsx.dictionary.entity

data class Dictionary1ResultEntity(
    val error_code: Int,
    val reason: String,
    val result: List<Dictionary1Entity>
)