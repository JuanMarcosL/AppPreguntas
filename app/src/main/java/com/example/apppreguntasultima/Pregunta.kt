package com.example.apppreguntasultima

data class Pregunta(
    val pregunta: String,
    val imagen:String,
    val descripcionImagen:String,
    val opcionA:String,
    val opcionB:String,
    val opcionC:String,
    val opcionD:String,
    val respuestaCorrecta:String,
)
