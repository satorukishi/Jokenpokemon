package com.satoruchannel.jokenpokemon

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.satoruchannel.jokenpokemon.entity.Computer
import com.satoruchannel.jokenpokemon.enum.PokemonType
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : JokenpoAppCompatActivity() {

    private var com = Computer()
    private lateinit var computers: List<Computer>
    private var pontuacao: Int = 0
    private var fezJogada = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        computers = com.getAllEnemies()

        ivBulbasaur.setOnClickListener {
            executarJogada(ivBulbasaur, PokemonType.GRASS)
        }
        ivSquirtle.setOnClickListener {
            executarJogada(ivSquirtle, PokemonType.WATER)
        }
        ivCharmander.setOnClickListener {
            executarJogada(ivCharmander, PokemonType.FIRE)
        }
    }

    override fun onStart() {
        super.onStart()

        iniciarJogada()
    }

    private fun iniciarJogada() {
        fezJogada = false

        // Computador
        ivCom.setImageResource(R.drawable.pokemon_card)
        val iInimigo = Random.nextInt(computers.count())
        com = computers[iInimigo]
        tvCom.text = com.nome

        atualizarPontuacao(0)
    }

    private fun atualizarPontuacao(novaPontuacao: Int) {
        if (novaPontuacao == 0) {
            pontuacao = novaPontuacao
        } else {
            pontuacao += novaPontuacao
        }

        tvScore.text = getString(R.string.score) + " " + pontuacao
    }

    fun executarJogada(ivPokemonEscolhido: ImageView, tipoEscolhido: PokemonType) {
        if (!fezJogada) {
            fezJogada = true
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
                        pokemonFainted("BULBASAUR")
                    } else if (com.tipoPokemon == PokemonType.GRASS) {
                        pokemonNotVeryEffective()
                    } else {
                        pokemonVeryEffective()
                    }
                }
                PokemonType.WATER -> {
                    ivPokemon.setImageResource(R.drawable.squirtle)
                    if (com.tipoPokemon == PokemonType.GRASS) {
                        pokemonFainted("SQUIRTLE")
                    } else if (com.tipoPokemon == PokemonType.WATER) {
                        pokemonNotVeryEffective()
                    } else {
                        pokemonVeryEffective()
                    }
                }
                PokemonType.FIRE -> {
                    ivPokemon.setImageResource(R.drawable.charmander)
                    if (com.tipoPokemon == PokemonType.WATER) {
                        pokemonFainted("CHARMANDER")
                    } else if (com.tipoPokemon == PokemonType.FIRE) {
                        pokemonNotVeryEffective()
                    } else {
                        pokemonVeryEffective()
                    }
                }
            }

        }
    }

    private fun pokemonVeryEffective() {
        Toast.makeText(this, R.string.very_effective, Toast.LENGTH_SHORT).show()
        atualizarPontuacao(2)
    }

    private fun pokemonNotVeryEffective() {
        Toast.makeText(this, R.string.not_very_effective, Toast.LENGTH_SHORT).show()
        atualizarPontuacao(1)

    }

    private fun pokemonFainted(nome: String) {
        Toast.makeText(this, nome + " " + getString(R.string.pokemon_fainted), Toast.LENGTH_SHORT).show()
    }
}