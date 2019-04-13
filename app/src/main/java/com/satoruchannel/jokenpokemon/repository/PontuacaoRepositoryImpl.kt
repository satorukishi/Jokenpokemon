package com.satoruchannel.jokenpokemon.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.satoruchannel.jokenpokemon.api.PontuacaoAPI
import com.satoruchannel.jokenpokemon.entity.Pontuacao
import com.satoruchannel.jokenpokemon.entity.PontuacaoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PontuacaoRepositoryImpl : PontuacaoRepository {
    private val pontuacaoAPI: PontuacaoAPI

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://gamestjd.herokuapp.com/")
            .build()

        pontuacaoAPI = retrofit.create(PontuacaoAPI::class.java)
    }

    override fun buscarRanking(): LiveData<PontuacaoResponse> {
        // MutableLiveData<PontuacaoResponse>() observa o PontuacaoResponse
        val liveData = MutableLiveData<PontuacaoResponse>()
        pontuacaoAPI.pesquisar()
            .enqueue(object : Callback<Pontuacao> {
                override fun onResponse(call: Call<Pontuacao>?, response: Response<Pontuacao>?) {
                    liveData.value = PontuacaoResponse(response?.body())
                }

                override fun onFailure(call: Call<Pontuacao>?, t: Throwable?) {
                    liveData.value = PontuacaoResponse(t!!)
                }
            })

        return liveData
    }

    override fun inserirPontuacao(pontuacao: Pontuacao): LiveData<PontuacaoResponse> {
        // MutableLiveData<PontuacaoResponse>() observa o PontuacaoResponse
        val liveData = MutableLiveData<PontuacaoResponse>()
        pontuacaoAPI.inserir()
            .enqueue(object : Callback<Pontuacao> {
                override fun onResponse(call: Call<Pontuacao>?, response: Response<Pontuacao>?) {
                    liveData.value = PontuacaoResponse(response?.body())
                }

                override fun onFailure(call: Call<Pontuacao>?, t: Throwable?) {
                    liveData.value = PontuacaoResponse(t!!)
                }
            })

        return liveData
    }

}
