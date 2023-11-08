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

                var linea: String
                var contador = -1
                var imagen: String = ""
                var descripcionImagen: String = ""
                var pregunta: String = ""
                var opcionA: String = ""
                var opcionB: String = ""
                var opcionC: String = ""
                var opcionD: String = ""
                var respuestaCorrecta: String = ""

                lector.forEachLine { linea ->
                    if (linea.isNotBlank()) {
                        contador++
                        when (contador) {
                            0 -> pregunta = linea
                            1 -> imagen = linea
                            2 -> descripcionImagen = linea
                            3 -> opcionA = linea
                            4 -> opcionB = linea
                            5 -> opcionC = linea
                            6 -> opcionD = linea
                            7 -> {
                                respuestaCorrecta = linea
                                cuestiones.add(
                                    Pregunta(
                                        pregunta,
                                        imagen,
                                        descripcionImagen,
                                        opcionA,
                                        opcionB,
                                        opcionC,
                                        opcionD,
                                        respuestaCorrecta
                                    )
                                )
                                contador = -1
                            }
                        }

                    }
                }
                lector.close()

            } catch (e: IOException) {
                e.printStackTrace()
            }


            return cuestiones
        }
    }
}
