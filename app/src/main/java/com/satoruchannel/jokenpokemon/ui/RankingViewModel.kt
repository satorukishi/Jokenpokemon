package com.satoruchannel.jokenpokemon.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.satoruchannel.jokenpokemon.entity.Pontuacao
import com.satoruchannel.jokenpokemon.entity.PontuacaoResponse
import com.satoruchannel.jokenpokemon.repository.PontuacaoRepository
import com.satoruchannel.jokenpokemon.repository.PontuacaoRepositoryImpl

class RankingViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val pontuacaoRepository: PontuacaoRepository
    private val mApiResponse: MediatorLiveData<PontuacaoResponse> = MediatorLiveData()
    val apiResponse: LiveData<PontuacaoResponse>
        get() = mApiResponse

    init {
        pontuacaoRepository = PontuacaoRepositoryImpl()
    }

    fun pesquisarPontuacao(): LiveData<PontuacaoResponse> {
        isLoading.postValue(true)

        mApiResponse.addSource(pontuacaoRepository.buscarRanking()) { api ->
            run {
                mApiResponse.value = api
                isLoading.postValue(false)
            }
        }
        return mApiResponse
    }

    fun enviarPontuacao(pontuacao: Pontuacao): LiveData<PontuacaoResponse> {
        isLoading.postValue(true)

        mApiResponse.addSource(pontuacaoRepository.inserirPontuacao(pontuacao)) { api ->
            run {
                mApiResponse.value = api
                isLoading.postValue(false)
            }
        }
        return mApiResponse
    }
}
