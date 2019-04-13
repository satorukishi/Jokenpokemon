package com.satoruchannel.jokenpokemon

import android.content.Intent
import android.os.Bundle
import com.satoruchannel.jokenpokemon.ui.RankingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : JokenpoAppCompatActivity() {

    private var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = intent.getStringExtra(EXTRA_EMAIL)

        btPlay.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(EXTRA_EMAIL, email)
            startActivity(intent)
        }
        btRanking.setOnClickListener {
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }
        btAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        btExit.setOnClickListener {
            finishAffinity()
        }
    }
}
