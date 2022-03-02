package com.hoarauthomas.mysensorapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var mySensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mySensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        //create a list
        val myListOfSensor = mutableListOf<Sensor>()

        //add to the list
        myListOfSensor.addAll(mySensorManager!!.getSensorList(Sensor.TYPE_ALL))

        //cretae a string
        val myString = StringBuilder()

        //binding the text
        val text = findViewById<TextView>(R.id.sensor_list)

        //iterate all sensor
        for (currentSensor in myListOfSensor) {
            myString.append(currentSensor.name).append(System.getProperty("line.separator"))

        }

        //update the textview with result
        text.text = myString
    }
}