package com.satoruchannel.jokenpokemon.dao

import com.activeandroid.query.Select
import com.satoruchannel.jokenpokemon.model.Usuario

class UsuarioDAO {
    fun findByEmail(email: String): Usuario {
        var usuario = Usuario()
        try {
            usuario = Select().from(Usuario::class.java)
                .where("email = ?", email)
                .executeSingle()
        } catch (ex: Exception) {

        } finally {
            return usuario
        }
    }
}
