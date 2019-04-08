package com.satoruchannel.jokenpokemon

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import com.satoruchannel.jokenpokemon.dao.UsuarioDAO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : JokenpoAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        etEmail.setText(intent.getStringExtra(EXTRA_EMAIL))


        btLogar.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val manterLogado = swManterLogado.isChecked

            if (email.equals("")) {
                Toast.makeText(this, R.string.email_empty_phrase, Toast.LENGTH_LONG).show()
            } else if (password.equals("")) {
                Toast.makeText(this, R.string.password_empty_phrase, Toast.LENGTH_LONG).show()
            } else {
                val usuarioDAO = UsuarioDAO()
                val usuario = usuarioDAO.findByEmail(email)

                if (!usuario.password.equals(password)) {
                    Toast.makeText(this, R.string.user_not_found, Toast.LENGTH_LONG).show()
                } else {
                    val editor: SharedPreferences.Editor = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit()
                    if (manterLogado) {
                        editor.putString(KEY_EMAIL, email)
                    } else {
                        editor.putString(KEY_EMAIL, "")
                    }
                    editor.apply()

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(EXTRA_EMAIL, email)
                    startActivity(intent)
                }
            }
        }

        btCriarConta.setOnClickListener {
            val intent = Intent(this, CriarContaActivity::class.java)
            intent.putExtra(EXTRA_EMAIL, etEmail.text.toString())
            startActivity(intent)
        }
    }

}
