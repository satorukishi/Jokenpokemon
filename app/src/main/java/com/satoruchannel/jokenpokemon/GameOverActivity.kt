package com.satoruchannel.jokenpokemon

import android.content.Intent
import android.os.Bundle
import com.satoruchannel.jokenpokemon.dao.UsuarioDAO
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : JokenpoAppCompatActivity() {
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        email = intent.getStringExtra(EXTRA_EMAIL)
        val usuario = UsuarioDAO().findByEmail(email)

        val let = usuario.nickname?.let {
            tvTrainerName.text = getString(R.string.trainer_name) + " " + it
            enviarPontuacao(it)
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

    }
}