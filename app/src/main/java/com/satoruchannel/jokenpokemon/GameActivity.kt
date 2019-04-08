package com.satoruchannel.jokenpokemon

import android.os.Bundle
import android.widget.ImageView
import com.satoruchannel.jokenpokemon.entity.Computer
import com.satoruchannel.jokenpokemon.enum.PokemonType
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : JokenpoAppCompatActivity() {

    private var com = Computer()
    private lateinit var computers: List<Computer>
    private var pontuacao: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        computers = com.getAllEnemies()

        iniciarJogada()

        ivBulbasaur.setOnClickListener {
            executarJogada(ivBulbasaur, PokemonType.GRASS)
        }
    }

    private fun iniciarJogada() {
        // Computador
        ivCom.setImageResource(R.drawable.pokemon_card)
        com = computers[Random.nextInt(computers.size - 1)]
        tvCom.setText(com.nome)
    }

    fun executarJogada(ivPokemonEscolhido: ImageView, tipoEscolhido: PokemonType) {
        // Exibe o Pokémon escolhido pelo computador
        when (com.tipoPokemon) {
            PokemonType.GRASS -> ivCom.setImageResource(R.drawable.bulbasaur)
            PokemonType.WATER -> ivCom.setImageResource(R.drawable.squirtle)
            PokemonType.FIRE -> ivCom.setImageResource(R.drawable.charmander)
        }

        ivPokemonEscolhido.setImageResource(R.drawable.grass_water_fire)
        when (tipoEscolhido) {
            PokemonType.GRASS -> {
                ivPokemon.setImageResource(R.drawable.bulbasaur)
                if (com.tipoPokemon == PokemonType.FIRE) {

                }
            }
            PokemonType.WATER -> ivPokemon.setImageResource(R.drawable.squirtle)
            PokemonType.FIRE -> ivPokemon.setImageResource(R.drawable.charmander)
        }
    }
}