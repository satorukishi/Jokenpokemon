package com.satoruchannel.jokenpokemon.entity

class PontuacaoResponse {
    var pontuacoes: Array<Pontuacao>? = null
    var erro: Throwable? = null

    companion object {
        val TAG_ID = "id"
        val TAG_NOME = "nome"
        val TAG_PONTOS = "pontos"
    }

    constructor(pontuacoes: Array<Pontuacao>?) {
        this.pontuacoes = pontuacoes
        this.erro = null
    }

    constructor()

    constructor(erro: Throwable) {
        this.erro = erro
        this.pontuacoes = null
    }
}
