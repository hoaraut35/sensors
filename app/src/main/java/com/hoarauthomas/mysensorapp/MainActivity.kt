package com.hoarauthomas.mysensorapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    var mySensorManager: SensorManager? = null
    private var textLight: TextView? = null
    private var textProximity: TextView? = null
    var myProximitySensor: Sensor? = null
    var myLightSensor: Sensor? = null


    override fun onStart() {
        super.onStart()

        if (myProximitySensor != null) {
            mySensorManager?.registerListener(
                this, myProximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL
            );
        }
        if (myLightSensor != null) {
            mySensorManager?.registerListener(
                this, myLightSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            );
        }

    }

    override fun onStop() {
        super.onStop()
        mySensorManager?.unregisterListener(this)
    }


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




        textLight = findViewById(R.id.label_light)
        textProximity = findViewById<TextView>(R.id.label_proximity)

        myLightSensor = mySensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        myProximitySensor = mySensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (myLightSensor == null) {
            textLight!!.text = resources.getString(R.string.error_no_sensor)
        }

        if (myProximitySensor == null) {
            textProximity!!.text = resources.getString(R.string.error_no_sensor)
        }


    }

    override fun onSensorChanged(p0: SensorEvent?) {

        val sensorType: Int = p0?.sensor!!.type
        val currentValue = p0.values[0]

        when (sensorType) {
            Sensor.TYPE_LIGHT -> {
                textLight!!.text = resources.getString(R.string.label_light, currentValue)
            }
            Sensor.TYPE_PROXIMITY -> {
                textProximity!!.text = resources.getString(R.string.label_proximity, currentValue)
            }
            else -> {}
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // TODO("Not yet implemented")
    }
}