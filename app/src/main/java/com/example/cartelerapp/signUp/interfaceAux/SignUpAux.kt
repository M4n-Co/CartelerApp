package com.example.cartelerapp.signUp.interfaceAux

interface SignUpAux {
    fun initRegistration(email : String, pass : String, firebaseId : String)
    fun finishRegistration(
        name : String,
        lastName : String,
        birthdate : String,
        gender : String,
        phone : String,
        weight : String,
        height : String,
        avatar : String
    )
}