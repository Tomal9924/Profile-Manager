package com.example.tomal.finalprojectservice;

import android.app.Service;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.graphics.Color;

import android.os.Bundle;
import android.util.EventLog;
import android.widget.Toast;

import java.util.EventObject;

public class MyService extends Service implements SensorEventListener{


    Intent intent;
    private Sensor myProximitySensor;
    private  Sensor myAccelerometerSensor;
    SensorManager myProximityManager,myAccelerometerManager;
    AudioManager aMan;
    int volume=20;
    int vol=0;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {

        //Create Manager
        try
        {
            myProximityManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            myAccelerometerManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            aMan= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            //Cretae Accelerometer

            myProximitySensor=myProximityManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            myAccelerometerSensor=myAccelerometerManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            myProximityManager.registerListener( this,myProximitySensor,myProximityManager.SENSOR_DELAY_NORMAL);
            myAccelerometerManager.registerListener( this,myAccelerometerSensor,myAccelerometerManager.SENSOR_DELAY_NORMAL);

            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();

        }

        catch (Exception ex){

            Toast.makeText(this, "Error Did not Created !!!!!", Toast.LENGTH_SHORT).show();
        }


        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myAccelerometerManager.unregisterListener(this);
        myProximityManager.unregisterListener(this);

        Toast.makeText(this, "Destroyed", Toast.LENGTH_SHORT).show();
        }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        startService(new Intent(getBaseContext(), MyService.class));
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try
        {

            Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();


        }

        catch(Exception ex){
            Toast.makeText(this, "Error Not Startted", Toast.LENGTH_SHORT).show();
        }
        //return super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;
        int sensorType=event.sensor.getType();
        float proximityValue=event.values[0];
        float x=event.values[0];
        float y=event.values[1];
        float z=event.values[2];

        try {

            /*switch (sensorType) {*/

                if(event.sensor.getType()==Sensor.TYPE_PROXIMITY)
                {
                    if(aMan.getRingerMode()!=AudioManager.RINGER_MODE_SILENT)
                    {
                        if(proximityValue==0.0f)
                        {
                            aMan.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                            //Toast.makeText(this, "Silent", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                else if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
                {
                    if(aMan.getRingerMode()!=AudioManager.RINGER_MODE_NORMAL)
                    {
                        if(x<0.0f && y<1.0f)
                        {
                            aMan.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                            //Toast.makeText(this, "Silent", Toast.LENGTH_SHORT).show();

                        }
                    }


                }

                /* case Sensor.TYPE_PROXIMITY:
                 *//*if(event.sensor.getType() == Sensor.TYPE_PROXIMITY) {*//*
                    //aMan.setRingerMode(3);


                        if (proximityValue == 0.0f) {


                            //aMan.setStreamVolume(AudioManager.STREAM_MUSIC,aMan.getStreamMaxVolume(AudioManager.STREAM_MUSIC),AudioManager.FLAG_SHOW_UI);
                            // aMan.setRingerMode(AudioManager.STREAM_RING);
                           *//* for (int i = 0; i < aMan.getStreamMaxVolume(AudioManager.STREAM_RING); i++)
                            {*//*

                 *//*aMan.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                                aMan.adjustVolume(AudioManager.STREAM_MUSIC, AudioManager.FLAG_SHOW_UI);*//*
                            aMan.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                            //aMan.setStreamVolume(AudioManager.ADJUST_RAISE,aMan.getStreamMaxVolume(AudioManager.STREAM_MUSIC),AudioManager.FLAG_SHOW_UI);
                            break;
                            //}



                    }
                    //}
                    //}

                case Sensor.TYPE_ACCELEROMETER:



                        if (event.values[0] < 0.0f && event.values[1] < 1.0f) {

                            //Down on a table
                            //aMan.setStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.RINGER_MODE_SILENT,AudioManager.FLAG_SHOW_UI);
                            aMan.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                            //aMan.setRingerMode(AudioManager.FLAG_SHOW_UI);
                            //aMan.setStreamVolume(AudioManager.RINGER_MODE_SILENT,AudioManager.STREAM_VOICE_CALL,AudioManager.FLAG_SHOW_UI);
                            break;
                        }
                    if (event.values[0] > 1.0f && event.values[1] < 1.0f) {

                        //Down on a table
                        //aMan.setStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.RINGER_MODE_SILENT,AudioManager.FLAG_SHOW_UI);
                        aMan.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        //aMan.setRingerMode(AudioManager.FLAG_SHOW_UI);
                        //aMan.setStreamVolume(AudioManager.RINGER_MODE_SILENT,AudioManager.STREAM_VOICE_CALL,AudioManager.FLAG_SHOW_UI);
                        break;
                    }
                        //aMan.getRingerMode(6);
                    *//*if((event.values[0]<1.0f || event.values[0]<0.0f ) && event.values[1]>1.0f )
                    {
                        //In Hand
                        //aMan.setStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.RINGER_MODE_NORMAL,AudioManager.FLAG_SHOW_UI);
                        aMan.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        //aMan.setRingerMode(AudioManager.FLAG_SHOW_UI);
                        //aMan.setStreamVolume(AudioManager.RINGER_MODE_VIBRATE,AudioManager.ADJUST_UNMUTE,AudioManager.FLAG_SHOW_UI);
                        break;
                    }*//*



            }




            *//*if (event.values[1] > 2)

            {
                *//**//*  aMan.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);*//**//*
                aMan.setStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.RINGER_MODE_NORMAL,AudioManager.FLAG_SHOW_UI);

            }
            if(myProximitySensor.)
            {

                aMan.setStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.RINGER_MODE_SILENT,AudioManager.FLAG_SHOW_UI);
            }*//*


             if (event.values[1] <1) {

                aMan.setStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.RINGER_MODE_SILENT,AudioManager.FLAG_SHOW_UI);
            }
            if(event.values[0]<myProximitySensor.getMaximumRange())
            {
                aMan.setStreamVolume(AudioManager.STREAM_RING, volume, AudioManager.FLAG_SHOW_UI);
            }*/

            //}
        }
        catch (Exception ex)
            {


        }





    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
