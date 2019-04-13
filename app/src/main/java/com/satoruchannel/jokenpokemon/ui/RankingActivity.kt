package com.satoruchannel.jokenpokemon.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.satoruchannel.jokenpokemon.JokenpoAppCompatActivity
import com.satoruchannel.jokenpokemon.R
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : JokenpoAppCompatActivity() {

    lateinit var rankingViewModel: RankingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        rankingViewModel = ViewModelProviders.of(this).get(RankingViewModel::class.java)

        rankingViewModel.pesquisarPontuacao()

        rankingViewModel.apiResponse.observe(this, Observer { api ->
            if (api?.erro == null) {
                Log.i("TAG", "SUCESSO")
                tvPontuacao.text = "ID: ${api!!.pontuacao!!.id}\n" +
                        "Nome: ${api!!.pontuacao!!.nome}\n" +
                        "Pontuação: ${api!!.pontuacao!!.pontos}"
            } else {
                Log.i("TAG", "ERRO${api.erro}")
            }
        })

        rankingViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading!!) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }
        })
    }
}