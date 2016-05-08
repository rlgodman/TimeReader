package ross.timereader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Ross on 07/05/2016.
 */
public class MediaButtonIntentReceiver extends BroadcastReceiver {
    private TextToSpeech toSpeech;
    private Context context;
    private String temp;
    public MediaButtonIntentReceiver(){
        super();
    }
    public MediaButtonIntentReceiver(Context context, String toSpeak) {
        super();
        this.context=context;
        temp = toSpeak;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TEST", "pls");
        String intentAction = intent.getAction();
        if (!Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {
            return;
        }
        KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        if (event == null) {
            return;
        }
        int action = event.getAction();
        if (action == KeyEvent.ACTION_DOWN) {
            toSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    toSpeech.setLanguage(Locale.UK);
                }
            });
            toSpeech.speak(temp,TextToSpeech.QUEUE_FLUSH, null, "2");
            Toast.makeText(context, "BUTTON PRESSED!", Toast.LENGTH_SHORT).show();
        }
        abortBroadcast();
    }
}