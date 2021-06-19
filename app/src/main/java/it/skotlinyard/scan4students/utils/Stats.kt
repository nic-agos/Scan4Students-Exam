package it.skotlinyard.scan4students.utils

import android.content.Context
import android.util.Log
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Quaderni
import java.sql.Date
import java.sql.SQLException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

class Stats(context: Context) {
    private val db = DbScan4Students.getInstance(context)

    fun getStudentNotebooks(user: String): MutableList<Quaderni>{
        var list: MutableList<Quaderni> = mutableListOf<Quaderni>()
        try {
            list = db.quaderniDao().getPublicNotebooksByUser(user)
            Log.v("S4S", "Succesfully download notebook in Stats.getStudentsNotebooks()")
            if (list.isEmpty()){
                Log.v("S4S", "No notebooks of $user found in Stats.getStudentsNotebooks()")
            }
            return list
        }catch(e: SQLException){
            Log.v("S4S", "Entrato in eccezione Stats.getStudentsNotebooks()")
            return list
        }
    }

    fun getStats(quadList: MutableList<Quaderni>): Map<String, Int>{

        var statsMap = mutableMapOf<String, Int>()
        var jan = 0; var feb = 0; var mar = 0; var apr = 0; var may = 0; var jun = 0
        var jul = 0; var aug = 0; var sep = 0; var oct = 0; var nov = 0; var dec = 0

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val currentDate = LocalDate.parse("${LocalDate.now()}")
        Log.v("S4S", "current date: ${currentDate}")

        for (item in quadList){
            var date = LocalDate.parse("${item.dataCaricamento}", formatter)
            Log.v("S4S", "${date.month}")

            if (date.year == currentDate.year){

                when(date.month){
                    Month.JANUARY -> jan += 1
                    Month.FEBRUARY -> feb += 1
                    Month.MARCH -> mar += 1
                    Month.APRIL -> apr += 1
                    Month.MAY -> may += 1
                    Month.JUNE -> jun += 1
                    Month.JULY -> jul += 1
                    Month.AUGUST -> aug += 1
                    Month.SEPTEMBER -> sep += 1
                    Month.OCTOBER -> oct += 1
                    Month.NOVEMBER -> nov += 1
                    Month.DECEMBER -> dec += 1
                }
            }
        }
        statsMap.put("JAN", jan)
        statsMap.put("FEB", feb)
        statsMap.put("MAR", mar)
        statsMap.put("APR", apr)
        statsMap.put("MAY", may)
        statsMap.put("JUN", jun)
        statsMap.put("JUL", jul)
        statsMap.put("AUG", aug)
        statsMap.put("SEP", sep)
        statsMap.put("OCT", oct)
        statsMap.put("NOV", nov)
        statsMap.put("DEC", dec)

        return statsMap
    }
}