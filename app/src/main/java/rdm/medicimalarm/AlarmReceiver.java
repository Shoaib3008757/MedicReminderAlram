package rdm.medicimalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by User-10 on 10-Nov-17.
 */

public class AlarmReceiver extends BroadcastReceiver{

    static Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("TAG", "Allarm Is running");


        int alarmId = intent.getExtras().getInt("alarmId");
        Log.e("TAG", "alarmId: " + alarmId);


        intent = new Intent();
        intent.setClass(context, WhenAllarmTrigered.class); //Test is a dummy class name where to redirect
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("alarmId", alarmId);
        context.startActivity(intent);



        //MainActivity.getTextView2().setText("Enough Rest. Do Work Now!");
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();
    }
}
