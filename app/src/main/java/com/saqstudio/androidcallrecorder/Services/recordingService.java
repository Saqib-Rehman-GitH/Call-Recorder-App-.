package com.saqstudio.androidcallrecorder.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.Date;

public class recordingService extends Service {

    public MediaRecorder recorder ;
    private boolean recordingStarted ;
    private File file ;
    String path = "/sdcard/alarms/" ;


            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }

            public int onStartCommand(Intent intent , int flags , int startId) {
                file = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_ALARMS ) ;
                Date date = new Date() ;
                CharSequence  sdf = DateFormat.format( "MM-dd-yy-hh-mm-ss " ,date.getTime() ) ;

                recorder = new MediaRecorder() ;
                recorder.setAudioSource ( MediaRecorder.AudioSource.VOICE_CALL ) ;
                recorder.setOutputFormat( MediaRecorder .OutputFormat.THREE_GPP );
                recorder.setOutputFile( file.getAbsolutePath() ) ;
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB ) ;

                TelephonyManager manager = (TelephonyManager ) getApplicationContext().getSystemService ( getApplicationContext().TELEPHONY_SERVICE  ) ;

                manager.listen( new PhoneStateListener(){
                    @Override
                    public void onCallStateChanged(int state, String phoneNumber) {
                        super.onCallStateChanged(state, phoneNumber);
                        if(TelephonyManager.CALL_STATE_IDLE == state && recorder ==null ){
                            recorder.stop();
                            recorder.reset() ;
                            recorder.release() ;
                            recordingStarted =false ;
                            stopSelf();
                        }
                        else if (TelephonyManager .CALL_STATE_OFFHOOK ==state ){
                            recorder.start() ;
                            recordingStarted = true ;
//
//
                        }
//
                    }
                } ,PhoneStateListener.LISTEN_CALL_STATE
                );
                return START_STICKY ;
//
//
//                return super.onStartCommand ( intent , flags , startId ) ;
//
            }
}
