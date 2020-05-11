package com.example.basicapp.data

class MyResponse<out T>(val errorCode: Int, val ErrorMsg: String, val data: T)