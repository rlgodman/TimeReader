package ross.timereader;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


import java.sql.SQLOutput;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Switch s1;
    private TextToSpeech t1;
    private String pass= "Error";
    private Boolean toreturn =false;
    getTime gt = new getTime();
    private AudioManager am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.speakBtn);
        s1 = (Switch)findViewById(R.id.switch1);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    HeadphoneState hs = new HeadphoneState();
                    hs.getButtonState(isChecked);
                }
            });


        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver(this);
        registerReceiver(mReceiver, filter);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toSpeak = gt.retrieve();
                pass = toSpeak;
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT);
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "1");
            }
        });

    }
    @Override
    protected void onPause(){
        if (ScreenReceiver.wasScreenOn){
            System.out.println("Screen Turned OFF");
        } else {

        }
        super.onPause();
    }

    @Override
    protected void onResume(){
        if (!ScreenReceiver.wasScreenOn){
            System.out.println("Screen turned ON");
        } else {

        }
        super.onResume();
    }
}

