package it.skotlinyard.scan4students.model.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Studenti(

        @PrimaryKey var username: String,
        var password: String,
        var nome: String,
        var cognome: String,
        var dataDiNascita: String,
        var sesso: String,
        var universita: String
        )

