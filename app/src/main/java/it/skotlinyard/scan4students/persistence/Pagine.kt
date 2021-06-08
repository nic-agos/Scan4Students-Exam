package it.skotlinyard.scan4students.persistence

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.PrimaryKey

@Entity/*(
        foreignKeys = [ForeignKey(
        entity = Quaderni::class,
        parentColumns = arrayOf("index"),
        childColumns = arrayOf("quaderno"),
        onDelete = NO_ACTION)]
        )*/

data class Pagine(
            @PrimaryKey(autoGenerate = true) var index: Int,
            var quaderno: Int,
            var numPagina: Int,
            var path: String) {
        constructor(notebook: Int, pageNumber: Int, path: String) :
                this(0, 0,0,"") {
                    quaderno = notebook
                    numPagina = pageNumber
                    this.path = path
    }
}