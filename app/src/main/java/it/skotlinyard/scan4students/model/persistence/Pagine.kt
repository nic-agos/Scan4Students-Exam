package it.skotlinyard.scan4students.model.persistence

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
        foreignKeys = [ForeignKey(
            entity = Quaderni::class,
            parentColumns = arrayOf("indice"),
            childColumns = arrayOf("quaderno"),
            onDelete = CASCADE)
            ]
        )

data class Pagine(
            @PrimaryKey(autoGenerate = true) var indice: Int,
            var quaderno: Int,
            var numPagina: Int,
            var path: String) {
        constructor(notebook: Int, pageNumber: Int, path: String) :
                this(0, notebook,pageNumber,path) {

    }
}