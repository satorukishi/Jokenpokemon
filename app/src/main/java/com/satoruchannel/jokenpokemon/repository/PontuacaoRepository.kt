package com.satoruchannel.jokenpokemon.repository

import android.arch.lifecycle.LiveData
import com.satoruchannel.jokenpokemon.entity.Pontuacao
import com.satoruchannel.jokenpokemon.entity.PontuacaoResponse

interface PontuacaoRepository {
    fun buscarRanking(): LiveData<PontuacaoResponse>
    fun inserirPontuacao(pontuacao: Pontuacao): LiveData<PontuacaoResponse>
}