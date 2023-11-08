package com.example.apppreguntasultima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppreguntasultima.ui.theme.AppPreguntasUltimaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPreguntasUltimaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val listaPreguntas = ManejoFichero.leerFichero(LocalContext.current)
                    var indice by remember { mutableStateOf(0) }

                    listaPreguntas.forEach { println(listaPreguntas.toString()) }
                    if (indice == -1) {
                        indice = listaPreguntas.lastIndex
                    } else if (indice > listaPreguntas.lastIndex) {
                        indice = 0
                    }
                    mostrarPregunta(listaPreguntas.get(indice)) { cambiaIndice ->
                        indice += cambiaIndice;
                    }
                }
            }
        }
    }
}

@Composable
fun mostrarPregunta(question: Pregunta, indice: (Int) -> Unit) {
    println("Pregunta recibida en pregunta: ${question.toString()}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFA2C4DB))
    ) {
        imagen(question)
        Spacer(modifier = Modifier.height(8.dp))
        pregunta(question)
        Spacer(modifier = Modifier.height(20.dp))
        opciones(question)
        //Spacer(modifier = Modifier.height(20.dp))
        botonesAnteriorYSiguiente(question) { cambiaIndice -> indice(cambiaIndice) }
    }
}

@Composable
fun imagen(pregunta: Pregunta) {

    val imageResourceID = LocalContext.current.resources.getIdentifier(
        pregunta.imagen, "drawable", LocalContext.current.packageName)
    Image(
        painter = painterResource(id = imageResourceID),
        contentDescription = pregunta.descripcionImagen,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .padding(16.dp)
    )
}

@Composable
fun pregunta(pregunta: Pregunta) {

    Box(
        modifier = Modifier
            .fillMaxHeight(0.22f)
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = pregunta.pregunta,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
        )
    }

}


@Composable
fun opciones(pregunta: Pregunta) {

    val opciones = listOf(pregunta.opcionA, pregunta.opcionB, pregunta.opcionC, pregunta.opcionD)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        opciones.forEach { opcion ->
            botonDeOpcion(opcion)
        }
    }
}

@Composable
fun botonDeOpcion(text: String) {
    Button(
        onClick = { /* TODO */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )
        )
    }
}

@Composable
fun botonesAnteriorYSiguiente(pregunta: Pregunta, indice: (Int) -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        // verticalAlignment = Alignment.CenterVertically
    ) {
        //BotonAnterior() {cambiaIndice -> indice (cambiaIndice)}
        //BotonSiguiente() {cambiaIndice -> indice (cambiaIndice)}
        Button(
            onClick = { indice(-1) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Flecha atrás")
            Text(
                text = " Anterior",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        }
        Button(
            onClick = { indice(1) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Siguiente",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Flecha adelante"
            )
        }
    }
}
/*
@Composable
fun BotonAnterior(indice: (Int) -> Unit) {
    Button(
        onClick = { indice (-1) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Flecha atrás")
        Text(
            text = " Anterior",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )
        )
    }

}

@Composable
fun BotonSiguiente(indice: (Int) -> Unit) {
    Button(
        onClick = { indice (1) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Siguiente",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Flecha adelante"
        )
    }
}
*/



