package ross.timereader;

import android.content.Context;
import android.media.AudioManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Switch;

/**
 * Created by Ross on 07/05/2016.
 */
public class HeadphoneState {
    private Context context;
    private boolean buttonPressed= false;

    public boolean getButtonState (boolean state){
        buttonPressed = state;
        return state;
    }
    public boolean handleHeadphoneState(Context context){
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if (buttonPressed){
            return false;
        } else {
            return am.isWiredHeadsetOn();
        }
    }
}
