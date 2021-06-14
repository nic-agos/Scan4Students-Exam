package it.skotlinyard.scan4students.model.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Universita (
        @PrimaryKey (autoGenerate = true) var indice: Int,
        var nome: String,
        var regione: String,
        var acronimo: String) {
        constructor(name: String, region: String, acronim: String):
                this(0, name, region, acronim)
}