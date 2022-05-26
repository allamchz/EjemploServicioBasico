package com.example.ejemploserviciobasico

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


lateinit var textView : TextView
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var filter = IntentFilter()
        filter.addAction(Contador.PROGRESO)
        filter.addAction(Contador.FIN)

       var  progreso = Progreso()
        registerReceiver(progreso, filter)

        textView  = findViewById(R.id.texto)

        var boton  = findViewById<Button>(R.id.boton)

        boton.setOnClickListener{
            val servicio = Intent(this, Contador::class.java)
            servicio.putExtra("iteraciones", 10)
            startService(servicio)
        }

    }


    class Progreso : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p1?.getAction() == Contador.PROGRESO) {
                val valor: Int = p1.getIntExtra("progreso", 0)
                textView.setText("" + valor)
            }
            if (p1?.getAction() == Contador.FIN) {
                textView.setText("Proceso Finalizado")
            }
        }
    }
}