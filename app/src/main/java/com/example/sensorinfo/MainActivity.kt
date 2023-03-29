package com.example.sensorinfo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.sensorinfo.viewmodels.SensorViewModel

class MainActivity : AppCompatActivity() {
    private val tag = this.javaClass.simpleName
    lateinit var lvSensors: ListView
    lateinit var spnColour: Spinner
    private val viewModel: SensorViewModel by lazy {
        ViewModelProvider(this).get(SensorViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.sensorList.forEach{
            Log.d(tag, it.toString())
        }
        lvSensors = findViewById(R.id.rvSensors)
        val dataFrom = arrayOf("VENDOR","NAME")
        val placeTo = intArrayOf(R.id.tvName, R.id.tvVendor)
        val adapter = SimpleAdapter(this, viewModel.listAsMap, R.layout.list_item, dataFrom, placeTo)
        lvSensors.adapter = adapter

        spnColour = findViewById(R.id.spnColour)
        val spnAdapter = ArrayAdapter.createFromResource(this,
        R.array.coloursArray,
        android.R.layout.simple_list_item_1)
        spnColour.adapter = spnAdapter

        val spnColourListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> lvSensors.setBackgroundColor(Color.BLUE)
                    1 -> lvSensors.setBackgroundColor(Color.YELLOW)
                    2 -> lvSensors.setBackgroundColor(Color.GREEN)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spnColour.onItemSelectedListener = spnColourListener

        lvSensors.setOnItemClickListener{ parent, view, position, id ->
            Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
        }
    }
}