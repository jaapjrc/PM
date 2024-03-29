package com.example.calculadorapro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    var operacionActual = ""

    var primerNumero: Double = Double.NaN
    var segundoNumero: Double = Double.NaN

    lateinit var tvTemp: TextView
    lateinit var tvResult: TextView

    lateinit var formatoDecimal: DecimalFormat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formatoDecimal = DecimalFormat("#.##########")
        tvTemp = findViewById(R.id.tvTemp)
        tvResult = findViewById(R.id.tvResult)
    }

    fun cambiarOperador(b: View) {
        if (tvTemp.text.isNotEmpty() || primerNumero.toString() != "NaN") {

            calcular()
            val boton: Button = b as Button
            operacionActual = boton.text.toString().trim()

        }
        if (tvTemp.text.toString().isEmpty()) {
            tvTemp.text = tvResult.text
        }

        tvResult.text = formatoDecimal.format(primerNumero) + operacionActual
        tvTemp.text = ""
    }

    fun raizCuadrada(b: View) {
        try {
            if (primerNumero.toString() != "NaN") {
                primerNumero = sqrt(primerNumero)
                tvTemp.text = formatoDecimal.format(primerNumero).toString()
                tvResult.text = formatoDecimal.format(primerNumero).toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun calcular() {
        try {
            if (primerNumero.toString() != "NaN") {
                if (tvTemp.text.toString().isEmpty()) {
                    tvTemp.text = tvResult.text.toString()
                }
                segundoNumero = tvTemp.text.toString().toDouble()
                tvTemp.text = ""

                when (operacionActual) {
                    "+" -> primerNumero = (primerNumero + segundoNumero)
                    "-" -> primerNumero = (primerNumero - segundoNumero)
                    "*" -> primerNumero = (primerNumero * segundoNumero)
                    "/" -> primerNumero = (primerNumero / segundoNumero)
                    "^" -> primerNumero = Math.pow(primerNumero, segundoNumero)
                }
            } else {
                primerNumero = tvTemp.text.toString().toDouble()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun seleccionarNumero(b: View) {
        val boton: Button = b as Button
        if (boton.text.toString() == ".") {
            if (!tvTemp.text.toString().contains(".")) {
                if (tvTemp.text.isEmpty()) {
                    tvTemp.text = "0" + boton.text.toString()
                } else {
                    tvTemp.text = tvTemp.text.toString() + boton.text.toString()
                }
            }
        } else {
            tvTemp.text = tvTemp.text.toString() + boton.text.toString()
        }
    }

    fun masMenos(b: View) {
        if (!tvTemp.text.toString().contains("-")) {
            tvTemp.text = "-" + tvTemp.text.toString()
        } else if (tvTemp.text.toString().contains("-")) {
            tvTemp.text = tvTemp.text.toString().replace("-", "")
        }
    }

    fun igual(b: View) {
        calcular()
        tvResult.text = formatoDecimal.format(primerNumero)
        operacionActual = ""
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
                tvResult.text = ""
            }
        } else if (boton.text.toString().trim() == "C") {
            primerNumero = Double.NaN
            segundoNumero = Double.NaN
            tvTemp.text = ""
            tvResult.text = ""
        }

    }

}

