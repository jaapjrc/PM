package com.example.calculadorapro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import java.text.DecimalFormat
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    val SUMA = "+"
    val RESTA = "-"
    val MULTIPLICACION = "*"
    val DIVISION = "/"
    val RAIZ = "√"
    val POTENCIA = "^"

    var operacionActual = ""

    var primerNumero: Double = Double.NaN
    var segundoNumero: Double = Double.NaN

    lateinit var tvTemp: TextView
    lateinit var tvResultado: TextView

    lateinit var formatoDecimal: DecimalFormat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formatoDecimal = DecimalFormat("#.##########")
        tvTemp = findViewById(R.id.tvTemp)
        tvTemp = findViewById(R.id.tvResultado)
    }

    fun cambiarOperador(b: View) {
        if (tvTemp.text.isNotEmpty() || primerNumero.toString() != "NaN"){
        calcular()
        val boton: Button = b as Button
        operacionActual = boton.text.toString().trim()

        tvResultado.text = formatoDecimal.format(primerNumero) + operacionActual
        tvTemp.text = ""
        }
    }

    fun cambiarOperadorRaiz(b: View) {
        if (tvTemp.text.isNotEmpty() || primerNumero.toString() != "NaN"){
            calcular()
            val boton: Button = b as Button
            operacionActual = boton.text.toString().trim()

            tvResultado.text = operacionActual + formatoDecimal.format(primerNumero)
            tvTemp.text = ""
        }
    }

    fun cambiarOperadorPotencia(b: View) {
        if (tvTemp.text.isNotEmpty() || primerNumero.toString() != "NaN"){
            calcular()
            val boton: Button = b as Button
            operacionActual = POTENCIA

            tvResultado.text = formatoDecimal.format(primerNumero) + operacionActual
            tvTemp.text = ""
        }
    }

    fun borrar(b: View) {
        val boton: Button = b as Button
        if (boton.text.toString().trim() == "CE") {
            if (tvTemp.text.toString().isNotEmpty()) {
                var datosActuales: CharSequence = tvTemp.text as CharSequence
                tvTemp.text = datosActuales.subSequence(0, datosActuales.length - 1)
            } else {
                primerNumero = Double.NaN
                segundoNumero = Double.NaN
                tvTemp.text = ""
                tvResultado.text = ""
            }
        } else if (boton.text.toString().trim() == "C") {
            primerNumero = Double.NaN
            segundoNumero = Double.NaN
            tvTemp.text = ""
            tvResultado.text = ""
        }
    }

    fun igual(b: View) {
        calcular()
        tvResultado.text = formatoDecimal.format(primerNumero)
        operacionActual = ""
    }

    fun calcular() {
       try {
           if (primerNumero.toString() != "NaN") {
               if (tvTemp.text.toString().isEmpty()){
                   tvTemp.text = tvResultado.text.toString()
               }
               segundoNumero = tvTemp.text.toString().toDouble()
               tvTemp.text = ""

               when (operacionActual) {
                   SUMA -> primerNumero = (primerNumero + segundoNumero)
                   RESTA -> primerNumero = (primerNumero - segundoNumero)
                   MULTIPLICACION -> primerNumero = (primerNumero * segundoNumero)
                   DIVISION -> primerNumero = (primerNumero / segundoNumero)
                   RAIZ -> primerNumero = sqrt(primerNumero)
                   POTENCIA -> primerNumero = Math.pow(primerNumero, segundoNumero)
               }
           } else {
               primerNumero = tvTemp.text.toString().toDouble()
           }
       } catch (e:Exception){

       }
    }

    fun seleccionarNumero(b: View) {
        val boton: Button = b as Button
        if (tvTemp.text.toString() == "0") {
            tvTemp.text = ""
        }
        tvTemp.text = tvTemp.text.toString() + boton.text.toString()
    }
}

