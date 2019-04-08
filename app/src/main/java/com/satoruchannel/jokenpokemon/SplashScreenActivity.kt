package com.satoruchannel.jokenpokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView


class SplashScreenActivity : JokenpoAppCompatActivity() {
    //Tempo que nosso splashscreen ficará visivel
    private val SPLASH_DISPLAY_LENGTH = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        carregar()
    }

    private fun carregar() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()
        //Pegando o nosso objeto criado no layout
        val iv = findViewById<View>(R.id.splash) as ImageView
        iv.clearAnimation()
        iv.startAnimation(anim)

        Handler().postDelayed(Runnable {
            // Após o tempo definido irá executar a próxima tela
            val pref = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
            val intent: Intent
            val email = pref.getString(KEY_EMAIL, "")

            if (email.equals("")) {
                intent = Intent(this, LoginActivity::class.java)
            } else {
                intent = Intent(this, MainActivity::class.java)
                intent.putExtra(EXTRA_EMAIL, email)
            }

            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            this.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }

}