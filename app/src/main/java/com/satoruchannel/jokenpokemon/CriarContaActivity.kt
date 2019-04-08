package com.satoruchannel.jokenpokemon

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.satoruchannel.jokenpokemon.model.Usuario
import kotlinx.android.synthetic.main.activity_criar_conta.*


class CriarContaActivity : JokenpoAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_conta)

        popularSpinner()
        btCriarConta.setOnClickListener {
            if (isCamposValidados()) {
                val usuario = Usuario()
                usuario.email = etEmail.text.toString()
                usuario.username = etUsername.text.toString()
                usuario.nickname = etNickname.text.toString()
                usuario.gender = spGender.selectedItemPosition
                usuario.password = etPassword.text.toString()
                usuario.save()

                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra(EXTRA_EMAIL, usuario.email)
                startActivity(intent)
            }
        }
    }

    private fun isCamposValidados(): Boolean {
        var retorno = true
        if (etEmail.text.toString().equals("")) {
            Toast.makeText(this, R.string.email_empty_phrase, Toast.LENGTH_SHORT).show()
            retorno = false
        } else if (etUsername.text.toString().equals("")) {
            Toast.makeText(this, R.string.username_empty_phrase, Toast.LENGTH_SHORT).show()
            retorno = false
        } else if (etNickname.text.toString().equals("")) {
            Toast.makeText(this, R.string.nickname_empty_phrase, Toast.LENGTH_SHORT).show()
            retorno = false
        } else if (spGender.selectedItemPosition == 0) {
            Toast.makeText(this, R.string.gender_empty_phrase, Toast.LENGTH_SHORT).show()
            retorno = false
        } else if (etPassword.text.toString().equals("")) {
            Toast.makeText(this, R.string.password_empty_phrase, Toast.LENGTH_SHORT).show()
            retorno = false
        }

        return retorno
    }

    private fun popularSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.genders_array, android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spGender.setAdapter(adapter)
    }


}
