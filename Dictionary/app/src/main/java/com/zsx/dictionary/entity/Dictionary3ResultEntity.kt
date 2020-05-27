package com.zsx.dictionary.entity

data class Dictionary3ResultEntity(
    val error_code: Int,
    val reason: String,
    val result: Dictionary3Entity
)