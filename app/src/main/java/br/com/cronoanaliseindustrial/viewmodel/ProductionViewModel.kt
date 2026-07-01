package br.com.cronoanaliseindustrial.viewmodel

import androidx.lifecycle.ViewModel
import br.com.cronoanaliseindustrial.model.ProductionResult

class ProductionViewModel : ViewModel() {

    var operador: String = ""
        private set

    var linha: String = ""
        private set

    var quantidadePecas: Int = 0
        private set

    private var inicioMillis: Long = 0L

    var producaoIniciada: Boolean = false
        private set

    fun iniciarProducao(operadorDigitado: String, linhaDigitada: String) {
        operador = operadorDigitado.trim()
        linha = linhaDigitada.trim()
        quantidadePecas = 0
        inicioMillis = System.currentTimeMillis()
        producaoIniciada = true
    }

    fun registrarPeca(): Int {
        if (producaoIniciada) {
            quantidadePecas++
        }
        return quantidadePecas
    }

    fun finalizarProducao(): ProductionResult {
        val tempoTotalMillis = if (producaoIniciada) {
            System.currentTimeMillis() - inicioMillis
        } else {
            0L
        }

        val taktTimeSegundos = if (quantidadePecas > 0) {
            (tempoTotalMillis / 1000.0) / quantidadePecas
        } else {
            0.0
        }

        producaoIniciada = false

        return ProductionResult(
            operador = operador,
            linha = linha,
            quantidadePecas = quantidadePecas,
            tempoTotalMillis = tempoTotalMillis,
            taktTimeSegundos = taktTimeSegundos
        )
    }
}
