package com.satoruchannel.jokenpokemon.entity

import com.satoruchannel.jokenpokemon.enum.PokemonType

class Computer() {
    constructor(nome: String, tipoPokemon: PokemonType) : this()

    var nome: String = ""
    var tipoPokemon: PokemonType = PokemonType.GRASS

    init {
        this.nome = nome
        this.tipoPokemon = tipoPokemon
    }

    fun getAllEnemies(): List<Computer> {
        val computers = mutableListOf<Computer>()
        computers.add(Computer("Misty", PokemonType.WATER))
        computers.add(Computer("Pryce", PokemonType.WATER))
        computers.add(Computer("Matt", PokemonType.WATER))
        computers.add(Computer("Shelly", PokemonType.WATER))
        computers.add(Computer("Archie", PokemonType.WATER))

        computers.add(Computer("Erika", PokemonType.GRASS))
        computers.add(Computer("Koga", PokemonType.GRASS))
        computers.add(Computer("Bug Catcher", PokemonType.GRASS))
        computers.add(Computer("Burglar", PokemonType.GRASS))
        computers.add(Computer("Youngster", PokemonType.GRASS))

        computers.add(Computer("Blaine", PokemonType.FIRE))
        computers.add(Computer("Firebreather", PokemonType.FIRE))
        computers.add(Computer("Courtney", PokemonType.FIRE))
        computers.add(Computer("Maxie", PokemonType.FIRE))
        computers.add(Computer("Tabitha", PokemonType.FIRE))

        return computers.toList()
    }
}
