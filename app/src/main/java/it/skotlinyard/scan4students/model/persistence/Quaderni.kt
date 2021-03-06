package it.skotlinyard.scan4students.model.persistence

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
                entity = Studenti::class,
                parentColumns = arrayOf("username"),
                childColumns = arrayOf("studente"),
                onDelete = CASCADE),
            ForeignKey(
                entity = Materie::class,
                parentColumns = arrayOf("indice"),
                childColumns = arrayOf("materia"),
                onDelete = CASCADE)
            ]
        )

data class Quaderni(
        @PrimaryKey (autoGenerate = true) var indice: Int,
        var titolo: String,
        var materia: Int,
        var numeroPagine: Int,
        var visibilita: String,
        var lingua: String,
        var studente: String,
        var dataCaricamento: String) {
        constructor(title: String, subject: Int, pageNumber: Int, visibility: String, language: String, student: String, uploadDate: String):
                this(0, title,subject,pageNumber,visibility,language,student,uploadDate)
}





