package it.skotlinyard.scan4students.model.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Materie(

    @PrimaryKey (autoGenerate = true) var indice: Int,
            var facolta: String,
            var materia: String,
            var prof: String,
            var crediti: Int,
            var anno: Int,
            var semestre: Int){
        constructor(faculty: String, subject: String, professor: String, credits: Int, year: Int, semester: Int)
                :this(0, faculty, subject, professor, credits, year, semester ){

        }
}
