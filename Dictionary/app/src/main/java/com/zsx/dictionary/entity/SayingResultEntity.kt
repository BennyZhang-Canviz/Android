package com.zsx.dictionary.entity

data class SayingResultEntity(
    val error_code: Int,
    val reason: String,
    val result: List<SayingEntity>,
    val total: Int
)