package br.com.cronoanaliseindustrial.model

data class ProductionResult(
    val operador: String,
    val linha: String,
    val quantidadePecas: Int,
    val tempoTotalMillis: Long,
    val taktTimeSegundos: Double
)
