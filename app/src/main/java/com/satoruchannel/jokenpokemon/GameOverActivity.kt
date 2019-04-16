package com.satoruchannel.jokenpokemon

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.satoruchannel.jokenpokemon.dao.UsuarioDAO
import com.satoruchannel.jokenpokemon.entity.Pontuacao
import com.satoruchannel.jokenpokemon.ui.RankingViewModel
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : JokenpoAppCompatActivity() {
    lateinit var rankingViewModel: RankingViewModel
    private var email = ""
    private var pontuacao: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        rankingViewModel = ViewModelProviders.of(this).get(RankingViewModel::class.java)

        email = intent.getStringExtra(EXTRA_EMAIL)
        pontuacao = intent.getIntExtra(EXTRA_SCORE, 0)
        val usuario = UsuarioDAO().findByEmail(email)

        usuario.nickname?.let {
            tvTrainerName.text = getString(R.string.trainer_name) + " " + it + "\n" +
                    getString(R.string.score) + " " + pontuacao
            if (pontuacao > 0) {
                enviarPontuacao(it)
            }
        }

        btPlayAgain.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(EXTRA_EMAIL, email)
            startActivity(intent)
        }
        btMainMenu.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_EMAIL, email)
            startActivity(intent)
        }
    }

    private fun enviarPontuacao(nickname: String) {
        val p = Pontuacao("", nickname, pontuacao)
        rankingViewModel.enviarPontuacao(p)
    }
}