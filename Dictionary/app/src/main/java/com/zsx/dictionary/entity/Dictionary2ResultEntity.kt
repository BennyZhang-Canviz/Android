package com.zsx.dictionary.entity

data class Dictionary2ResultEntity(
    val error_code: Int,
    val reason: String,
    val result: List<Dictionary2Entity>,
    val total: Int
)