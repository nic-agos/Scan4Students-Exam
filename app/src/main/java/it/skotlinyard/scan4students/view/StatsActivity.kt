package it.skotlinyard.scan4students.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.databinding.ActivityStatsBinding
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Universita
import it.skotlinyard.scan4students.utils.Session
import it.skotlinyard.scan4students.utils.Stats
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.Map as Map
import kotlin.properties.Delegates as Delegates


class StatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding
    private val stats = Stats(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(getString(R.string.statistiche))


        //Chart creation
        val barChart = binding.BarCharGraph

        //Formatting X-axis


        var reportQuaderni : Map<String , Int>? by Delegates.observable(null){ property, oldValue, newValue ->

            val xAxisLabels = newValue!!.keys
            barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)

            //Populating DataBase
            val listaQuaderni: MutableList<BarEntry> = mutableListOf()
            var pos = 0.0f
            newValue.forEach { k, v ->
                listaQuaderni.add(BarEntry(pos, v.toFloat()))
                pos += 1.0f
            }


            //Chart Creation
            val barDataSet = BarDataSet(listaQuaderni, "Quaderni")
            val barData = BarData(barDataSet)
            barChart.data = barData


            //Chart Appearance
            barChart.setFitBars(true)


            val xAxis: XAxis = barChart.getXAxis()
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
            xAxis.setCenterAxisLabels(false)
            xAxis.setDrawGridLines(false)

            

        }


        val myCoroutineScope= CoroutineScope(Dispatchers.IO)
        myCoroutineScope.launch(Dispatchers.IO) {
            val utente = intent.getStringExtra("user").toString()
            reportQuaderni = stats.getStats(stats.getStudentNotebooks(utente))
        }
    }


}