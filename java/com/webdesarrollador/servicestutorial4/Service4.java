package com.webdesarrollador.servicestutorial4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Service4 extends Service {

    // Binder que retornaremos a los clientes
    private final IBinder mBinder = new MiBinder();

    //Clase que extiende de "Binder", cuyo método getService
    // devuelve un instancia del servicio al componente que enlaza
    public class MiBinder extends Binder {
        Service4 getService() {

            return Service4.this;
        }
    }

    @Override
    //Cuando un componente se conecta al servicio, devolvemos el objeto IBinder
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //Método para coger la hora
    public String getHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        return currentTime;

    }
    public void onDestroy(){
        Log.d("estado","destroy");
    }
}