package com.satoruchannel.jokenpokemon.entity

class PontuacaoResponse {
    var pontuacao: Pontuacao?
    var erro: Throwable?

    constructor(pontuacao: Pontuacao?) {
        this.pontuacao = pontuacao
        this.erro = null
    }

    constructor(erro: Throwable) {
        this.erro = erro
        this.pontuacao = null
    }
}
