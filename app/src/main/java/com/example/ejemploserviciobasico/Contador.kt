package com.example.ejemploserviciobasico

import android.app.IntentService
import android.content.Intent
import android.content.Context


/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class Contador : IntentService("Contador") {
    companion object {
        val PROGRESO = "com.example.ejemploserviciobasico.PROGRESO"
        val FIN = "com.example.ejemploserviciobasico.FIN"
        }

    override fun onHandleIntent(intent: Intent?) {

        var inter = intent?.getIntExtra("iteraciones", 0)


        for (i in 0..inter!!) {
            tarea()
            var bcIntent = Intent()
            bcIntent.setAction(PROGRESO)
            bcIntent.putExtra("progreso", i * 10)
            sendBroadcast(bcIntent)
        }
        var bcIntent = Intent()
        bcIntent.setAction(FIN)
        sendBroadcast(bcIntent)

    }


    fun tarea() {
        Thread.sleep(6000)
    }


}