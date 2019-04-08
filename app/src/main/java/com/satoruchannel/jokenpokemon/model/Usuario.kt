package com.satoruchannel.jokenpokemon.model

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

@Table(name = "usuario")
class Usuario : Model() {
    @Column(name = "email", index = true)
    var email: String? = null
    @Column(name = "username")
    var username: String? = null
    @Column(name = "nickname")
    var nickname: String? = null
    @Column(name = "gender")
    var gender: Int? = null
    @Column(name = "password")
    var password: String? = null
}
