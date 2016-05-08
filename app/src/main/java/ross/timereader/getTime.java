package ross.timereader;

import java.util.Calendar;

/**
 * Created by Ross on 07/05/2016.
 */
public class getTime {
        String retrieve (){
        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

        String temphour = Integer.toString(hour);
        String tempMin = Integer.toString(minute);
                if (minute <10){
                        tempMin = "0"+ tempMin;
                }
                String time = temphour+":"+tempMin;


        return time;
    }
}
