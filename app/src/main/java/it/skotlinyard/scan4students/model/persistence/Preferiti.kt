package it.skotlinyard.scan4students.model.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Preferiti (
    @PrimaryKey (autoGenerate = true) var indice: Int,
    var utente: String,
    var quadPref: Int)