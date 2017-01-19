package com.webdesarrollador.servicestutorial4;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Creamos una instancia de nuestro servicio
    Service4 mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Enlazamos al Servicio
        Intent intent = new Intent(this, Service4.class);
        bindService(intent, mConnection, this.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Nos desenlazamos del servicio, y el servicio se detendra
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void getTime(View v) {
        if (mBound) {

            //Llamamos al método getHora del servicio Service4, y con setText le pasamos la hora al TextView
            String tiempo = mService.getHora();
            TextView hora= (TextView)findViewById(R.id.hora);
            hora.setText(tiempo);
        }
    }
    //Al darle al boton de parar servicio, este se desligará y se detendrá
    public void stopService(View v) {
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
    //Creamos una interface ServiceConection para enlazar el servicio con el objeto mService
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

            //Cuando se crea una conexión con el servicio
            Service4.MiBinder binder = (Service4.MiBinder) service;
            mService = binder.getService();
            mBound = true;
            Log.d("estado","iniciado");
        }

        @Override
        //Cuando termina la conexión con el servicio de forma inesperada. no cuando el cliente se desenlaza
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d("estado","detenido");
            mService=null;
            mBound = false;
        }
    };
}