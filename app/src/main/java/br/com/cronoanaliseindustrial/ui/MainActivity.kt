package br.com.cronoanaliseindustrial.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.cronoanaliseindustrial.R
import br.com.cronoanaliseindustrial.databinding.ActivityMainBinding
import br.com.cronoanaliseindustrial.viewmodel.ProductionViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIniciar.setOnClickListener {
            iniciarProducao()
        }

        binding.buttonRegistrar.setOnClickListener {
            val total = viewModel.registrarPeca()
            binding.textPecas.text = getString(R.string.pecas_formatado, total)
        }

        binding.buttonFinalizar.setOnClickListener {
            finalizarProducao()
        }
    }

    private fun iniciarProducao() {
        val operador = binding.editOperador.text.toString()
        val linha = binding.editLinha.text.toString()

        if (operador.isBlank() || linha.isBlank()) {
            Toast.makeText(this, "Preencha operador e linha de produção.", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.iniciarProducao(operador, linha)

        binding.textPecas.text = getString(R.string.pecas_formatado, 0)
        binding.editOperador.isEnabled = false
        binding.editLinha.isEnabled = false
        binding.buttonIniciar.isEnabled = false
        binding.buttonRegistrar.isEnabled = true
        binding.buttonRegistrar.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        binding.buttonRegistrar.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(this, R.color.industrial_primary)
        )
    }

    private fun finalizarProducao() {
        if (!viewModel.producaoIniciada) {
            Toast.makeText(this, "Inicie a produção antes de finalizar.", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = viewModel.finalizarProducao()

        val intent = Intent(this, ReportActiviity::class.java).apply {
            putExtra(EXTRA_OPERADOR, resultado.operador)
            putExtra(EXTRA_LINHA, resultado.linha)
            putExtra(EXTRA_QUANTIDADE_PECAS, resultado.quantidadePecas)
            putExtra(EXTRA_TEMPO_TOTAL_MILLIS, resultado.tempoTotalMillis)
            putExtra(EXTRA_TAKT_TIME_SEGUNDOS, resultado.taktTimeSegundos)
        }

        startActivity(intent)
    }

    companion object {
        const val EXTRA_OPERADOR = "extra_operador"
        const val EXTRA_LINHA = "extra_linha"
        const val EXTRA_QUANTIDADE_PECAS = "extra_quantidade_pecas"
        const val EXTRA_TEMPO_TOTAL_MILLIS = "extra_tempo_total_millis"
        const val EXTRA_TAKT_TIME_SEGUNDOS = "extra_takt_time_segundos"
    }
}
