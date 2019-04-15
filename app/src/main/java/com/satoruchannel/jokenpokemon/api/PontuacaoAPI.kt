package com.satoruchannel.jokenpokemon.api

import com.satoruchannel.jokenpokemon.entity.Pontuacao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface PontuacaoAPI {
    @GET("/jokenpokemon/pontuacao/")
    fun pesquisar(): Call<Array<Pontuacao>>

    @POST("/jokenpokemon/pontuacao/")
    fun inserir(): Call<Pontuacao>
}