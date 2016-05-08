package ross.timereader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Ross on 07/05/2016.
 */
public class ScreenReceiver extends BroadcastReceiver {
    private Context context;
    private String temp;
    private TextToSpeech toSpeech;
    private getTime gt;
    private HeadphoneState hs;
    private boolean buttonState;
    public static boolean wasScreenOn = true;

    public ScreenReceiver (Context context){
        super();
        this.context = context;

    }
    public void getButtonState (boolean fromMain){
        buttonState = fromMain;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            hs = new HeadphoneState();
            Boolean plugged = hs.handleHeadphoneState(context);
            if(plugged==true) {
                toSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        toSpeech.setLanguage(Locale.UK);
                        if (status == TextToSpeech.SUCCESS) {
                            gt = new getTime();
                            temp = gt.retrieve();
                            toSpeech.speak(temp, TextToSpeech.QUEUE_FLUSH, null, "3");
                        }
                    }
                });
            }
            wasScreenOn = true;
        }
    }

}
