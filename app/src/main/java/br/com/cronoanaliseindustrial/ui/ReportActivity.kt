package br.com.cronoanaliseindustrial.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.cronoanaliseindustrial.databinding.ActivityReportBinding
import java.util.Locale
import kotlin.math.abs

class ReportActiviity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrarRelatorio()

        binding.buttonNovaProducao.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun mostrarRelatorio() {
        val operador = intent.getStringExtra(MainActivity.EXTRA_OPERADOR).orEmpty()
        val linha = intent.getStringExtra(MainActivity.EXTRA_LINHA).orEmpty()
        val quantidade = abs(intent.getIntExtra(MainActivity.EXTRA_QUANTIDADE_PECAS, 0))
        val tempoTotalMillis = intent.getLongExtra(MainActivity.EXTRA_TEMPO_TOTAL_MILLIS, 0L)
        val taktTimeSegundos = intent.getDoubleExtra(MainActivity.EXTRA_TAKT_TIME_SEGUNDOS, 0.0)

        val totalSegundos = tempoTotalMillis / 1000
        val minutos = totalSegundos / 60
        val segundos = totalSegundos % 60

        binding.textRelatorio.text = """
            Operador: ${operador.uppercase(Locale.getDefault())}
            Linha: ${linha.uppercase(Locale.getDefault())}
            Peças aprovadas: $quantidade
            Tempo total: ${minutos}min ${segundos}s
            Takt time: ${String.format(Locale.getDefault(), "%.2f", taktTimeSegundos)} segundos/peça
        """.trimIndent()
    }
}
