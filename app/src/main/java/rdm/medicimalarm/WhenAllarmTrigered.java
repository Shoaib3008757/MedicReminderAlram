package rdm.medicimalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WhenAllarmTrigered extends AppCompatActivity {

    Button bt_taked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_when_allarm_trigered);

        bt_taked = (Button) findViewById(R.id.bt_taked);

        stopAllarm();


    }

    private void stopAllarm(){

        bt_taked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                int alarmId = intent.getExtras().getInt("alarmId");
                Log.e("TAG", "Alarm Alarm: " + alarmId);


                    Intent morningIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    // morningIntent.putExtra("stop", 1101);
                    PendingIntent pendingIntentForMorning = PendingIntent.getBroadcast(getApplicationContext(), alarmId, morningIntent, 0);

                    ((AlarmManager) getSystemService(ALARM_SERVICE)).cancel(pendingIntentForMorning);

                    if (AlarmReceiver.ringtone.isPlaying()) {

                        AlarmReceiver.ringtone.stop();
                        finish();
                    }



            }
        });
    }
}
