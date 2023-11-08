package com.example.apppreguntasultima

import android.content.Context
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader

class ManejoFichero {

    companion object {
        fun leerFichero(contexto: Context): List<Pregunta> {
            val cuestiones: MutableList<Pregunta> = ArrayList()
            try {
                val assetManager = contexto.assets
                val inputStream = assetManager.open("Preguntas.txt")
                val lector = BufferedReader(InputStreamReader(inputStream))

                var linea: String?
                var contador = 0
                var pregunta: String? = ""
                var opcionA: String? = ""
                var opcionB: String? = ""
                var opcionC: String? = ""
                var opcionD: String? = ""
                var respuestaCorrecta: String? = ""

                while (lector.readLine().also { linea = it } != null) {
                    if (linea!!.isNotBlank()) {

                        when (contador % 7) {
                            0 -> pregunta = linea
                            1 -> opcionA = linea
                            2 -> opcionB = linea
                            3 -> opcionC = linea
                            4 -> opcionD = linea
                            5 -> {
                                respuestaCorrecta = linea
                                cuestiones.add(
                                    Pregunta(
                                        pregunta!!,
                                        opcionA!!,
                                        opcionB!!,
                                        opcionC!!,
                                        opcionD!!,
                                        respuestaCorrecta!!
                                    )
                                )
                            }
                        }
                    }
                    contador++
                }
                lector.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return cuestiones
        }
    }
}