package com.saqstudio.androidcallrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.saqstudio.androidcallrecorder.Services.recordingService;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggler ;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                toggler = findViewById(R.id.tIdToggleButton ) ;

            }

            public void toggleButtonMethod(View v ) {
                boolean checked = ( ( ToggleButton ) v).isChecked() ;
                if(checked) {
                    startService( new Intent(this , recordingService.class ) ) ;
                    Toast.makeText ( getApplicationContext() , "Call Recoerder is Set to Recording " , Toast.LENGTH_SHORT ).show() ;

                }else{
                    stopService( new Intent(this , recordingService.class ) ) ;
                    Toast.makeText ( getApplicationContext() , "Call Recoerder is being Stopped " , Toast.LENGTH_SHORT ).show() ;

                }
            }
}
