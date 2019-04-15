package com.satoruchannel.jokenpokemon.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.satoruchannel.jokenpokemon.entity.PontuacaoResponse
import com.satoruchannel.jokenpokemon.repository.PontuacaoRepository
import com.satoruchannel.jokenpokemon.repository.PontuacaoRepositoryImpl

class RankingViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val enderecoRepository: PontuacaoRepository
    private val mApiResponse: MediatorLiveData<PontuacaoResponse> = MediatorLiveData()
    val apiResponse: LiveData<PontuacaoResponse>
        get() = mApiResponse

    init {
        enderecoRepository = PontuacaoRepositoryImpl()
    }

    fun pesquisarPontuacao(): LiveData<PontuacaoResponse> {
        isLoading.postValue(true)

        mApiResponse.addSource(enderecoRepository.buscarRanking()) { api ->
            run {
                mApiResponse.value = api
                isLoading.postValue(false)
            }
        }
        return mApiResponse
    }
}
