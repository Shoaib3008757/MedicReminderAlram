package rdm.medicimalarm.DaysFragment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import rdm.medicimalarm.AlarmReceiver;
import rdm.medicimalarm.R;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by User-10 on 10-Nov-17.
 */

public class DayOne extends Fragment {

    ImageView im_plus, im_minus;
    ImageView im_plus_aftermoon, im_minus_aftermoon;
    RelativeLayout rl_expand_for_morning, rl_expand_for_aftermoon;
    RelativeLayout rl_plus_minus, rl_plus_minus_aftermoon;

    AlarmManager alarmManager;
    private PendingIntent pendingIntentForMorning;
    private PendingIntent pendingIntentForAftermoon;
    private PendingIntent pendingIntentForEvening;
    private PendingIntent pendingIntentForNight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_dayone, container, false);


        im_plus = (ImageView) view.findViewById(R.id.im_plus);
        im_minus = (ImageView) view.findViewById(R.id.im_minus);
        im_plus_aftermoon = (ImageView) view.findViewById(R.id.im_plus_aftermoon);
        im_minus_aftermoon = (ImageView) view.findViewById(R.id.im_minus_aftermoon);
        im_minus = (ImageView) view.findViewById(R.id.im_minus);
        rl_expand_for_morning = (RelativeLayout) view.findViewById(R.id.rl_expand_for_morning);
        rl_expand_for_aftermoon = (RelativeLayout) view.findViewById(R.id.rl_expand_for_aftermoon);
        rl_plus_minus = (RelativeLayout) view.findViewById(R.id.rl_plus_minus);
        rl_plus_minus_aftermoon = (RelativeLayout) view.findViewById(R.id.rl_plus_minus_aftermoon);



        alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        Intent morningIntent = new Intent(getActivity(), AlarmReceiver.class);
        morningIntent.putExtra("alarmId", 0);
        pendingIntentForMorning = PendingIntent.getBroadcast(getActivity(), 0, morningIntent, 0);
        pendingIntentForAftermoon = PendingIntent.getBroadcast(getActivity(), 1, morningIntent, 0);
        pendingIntentForEvening = PendingIntent.getBroadcast(getActivity(), 2, morningIntent, 0);
        pendingIntentForNight = PendingIntent.getBroadcast(getActivity(), 3, morningIntent, 0);


        plusClickListenerForMorning();
        plusClickListenerForAftermoon();
        setAllarmForMorning();
        setAllarmForAftermoon();

        return view;
    }

    public void plusClickListenerForMorning(){

        rl_plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rl_expand_for_aftermoon.getVisibility()==View.VISIBLE){
                    rl_expand_for_aftermoon.setVisibility(View.GONE);

                    im_plus_aftermoon.setVisibility(View.VISIBLE);
                    im_minus_aftermoon.setVisibility(View.GONE);
                }

                if (rl_expand_for_morning.getVisibility()==View.VISIBLE){

                    im_plus.setVisibility(View.VISIBLE);
                    im_minus.setVisibility(View.GONE);
                    rl_expand_for_morning.setVisibility(View.GONE);
                }
               else if (rl_expand_for_morning.getVisibility()==View.GONE){

                    im_plus.setVisibility(View.GONE);
                    im_minus.setVisibility(View.VISIBLE);
                    rl_expand_for_morning.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    //

    public void plusClickListenerForAftermoon(){

        rl_plus_minus_aftermoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rl_expand_for_morning.getVisibility()==View.VISIBLE){
                    rl_expand_for_morning.setVisibility(View.GONE);
                    im_plus.setVisibility(View.VISIBLE);
                    im_minus.setVisibility(View.GONE);
                }

                if (rl_expand_for_aftermoon.getVisibility()==View.VISIBLE){

                    im_plus_aftermoon.setVisibility(View.VISIBLE);
                    im_minus_aftermoon.setVisibility(View.GONE);
                    rl_expand_for_aftermoon.setVisibility(View.GONE);
                }
                else if (rl_expand_for_aftermoon.getVisibility()==View.GONE){

                    im_plus_aftermoon.setVisibility(View.GONE);
                    im_minus_aftermoon.setVisibility(View.VISIBLE);
                    rl_expand_for_aftermoon.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    //

    public void setAllarmForMorning(){

        rl_expand_for_morning.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("TAG", selectedHour + ":" + selectedMinute);
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);

                        startAlarmForMorning(selectedHour, selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();



            }
        });
    }

    //

    public void setAllarmForAftermoon(){

        rl_expand_for_aftermoon.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("TAG", selectedHour + ":" + selectedMinute);
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);

                        startAlarmForAftermoon(selectedHour, selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();



            }
        });
    }

    //

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startAlarmForMorning(int timeHour, int timeMinute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntentForMorning);

    }

    //

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startAlarmForAftermoon(int timeHour, int timeMinute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntentForAftermoon);

    }

    //

}