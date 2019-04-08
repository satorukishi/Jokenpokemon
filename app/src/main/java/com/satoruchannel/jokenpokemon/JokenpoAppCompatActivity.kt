package com.satoruchannel.jokenpokemon

import android.support.v7.app.AppCompatActivity

abstract class JokenpoAppCompatActivity : AppCompatActivity() {
    //Chave Manter logado Preferences
    protected val KEY_EMAIL = "KEY_EMAIL"

    // SharedPreferences
    protected val EXTRA_EMAIL = "EXTRA_EMAIL"
    protected val SP_NAME = "JOKENPOKEMON"

}
