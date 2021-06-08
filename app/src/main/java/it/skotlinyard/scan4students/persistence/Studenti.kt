package it.skotlinyard.scan4students.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Studenti(

        @PrimaryKey var username: String,
        var password: String,
        var nome: String,
        var cognome: String,
        var dataDiNascita: String,
        var sesso: String) {

        constructor(username: String, password: String, nome: String, cognome: String, dataDiNascita: String) : this("","","","","","") {
                this.username = username
                this.password = password
                this.nome = nome
                this.cognome = cognome
                this.dataDiNascita = dataDiNascita
                this.sesso = ""
        }

}

