package rdm.medicimalarm.DaysFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import rdm.medicimalarm.AlarmReceiver;
import rdm.medicimalarm.R;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by User-10 on 10-Nov-17.
 */

public class DayTwo extends Fragment {
    ImageView im_plus, im_minus;
    ImageView im_plus_aftermoon, im_minus_aftermoon;
    ImageView im_plus_evining, im_minus_evining;
    ImageView im_plus_night, im_minus_night;
    RelativeLayout rl_expand_for_morning, rl_expand_for_aftermoon, rl_expand_for_evining, rl_expand_for_night;
    RelativeLayout rl_plus_minus, rl_plus_minus_aftermoon, rl_plus_minus_evining, rl_plus_minus_night;

    RelativeLayout floating_circule;

    AlarmManager alarmManager;
    private PendingIntent pendingIntentForMorning;
    private PendingIntent pendingIntentForAftermoon;
    private PendingIntent pendingIntentForEvening;
    private PendingIntent pendingIntentForNight;

    ArrayList<String> listDays;
    ArrayList<HashMap<String, String>> yearmontyear;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_daytwo, container, false);

        im_plus = (ImageView) view.findViewById(R.id.im_plus);
        im_minus = (ImageView) view.findViewById(R.id.im_minus);
        im_plus_aftermoon = (ImageView) view.findViewById(R.id.im_plus_aftermoon);
        im_minus_aftermoon = (ImageView) view.findViewById(R.id.im_minus_aftermoon);

        rl_expand_for_morning = (RelativeLayout) view.findViewById(R.id.rl_expand_for_morning);
        rl_expand_for_aftermoon = (RelativeLayout) view.findViewById(R.id.rl_expand_for_aftermoon);
        rl_plus_minus = (RelativeLayout) view.findViewById(R.id.rl_plus_minus);
        rl_plus_minus_aftermoon = (RelativeLayout) view.findViewById(R.id.rl_plus_minus_aftermoon);

        im_plus_evining = (ImageView) view.findViewById(R.id.im_plus_evning);
        im_minus_evining = (ImageView) view.findViewById(R.id.im_minus_evning);
        im_plus_night = (ImageView) view.findViewById(R.id.im_plus_night);
        im_minus_night = (ImageView) view.findViewById(R.id.im_minus_night);
        rl_expand_for_evining = (RelativeLayout) view.findViewById(R.id.rl_expand_for_evening);
        rl_expand_for_night = (RelativeLayout) view.findViewById(R.id.rl_expand_for_night);
        rl_plus_minus_evining = (RelativeLayout) view.findViewById(R.id.rl_plus_minus_evening);
        rl_plus_minus_night = (RelativeLayout) view.findViewById(R.id.rl_plus_minus_night);

        floating_circule = (RelativeLayout) view.findViewById(R.id.rl_floating);


        listDays = new ArrayList<>();
        yearmontyear = new ArrayList<>();


        alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        Intent morningIntent = new Intent(getActivity(), AlarmReceiver.class);
        morningIntent.putExtra("alarmId", 0);
        pendingIntentForMorning = PendingIntent.getBroadcast(getActivity(), 0, morningIntent, 0);
        pendingIntentForAftermoon = PendingIntent.getBroadcast(getActivity(), 1, morningIntent, 0);
        pendingIntentForEvening = PendingIntent.getBroadcast(getActivity(), 2, morningIntent, 0);
        pendingIntentForNight = PendingIntent.getBroadcast(getActivity(), 3, morningIntent, 0);


        plusClickListenerForMorning();
        plusClickListenerForAftermoon();
        plusClickListenerForEvining();
        plusClickListenerForNight();
        setAllarmForMorning();
        setAllarmForAftermoon();
        setAllarmForEvening();
        setAllarmForNight();
        settingDynamicAlarm();

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
                if (rl_expand_for_evining.getVisibility()==View.VISIBLE){
                    rl_expand_for_evining.setVisibility(View.GONE);
                    im_plus_evining.setVisibility(View.VISIBLE);
                    im_minus_evining.setVisibility(View.GONE);
                }

                if (rl_expand_for_night.getVisibility()==View.VISIBLE){
                    rl_expand_for_night.setVisibility(View.GONE);
                    im_plus_night.setVisibility(View.VISIBLE);
                    im_minus_night.setVisibility(View.GONE);
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

                if (rl_expand_for_evining.getVisibility()==View.VISIBLE){
                    rl_expand_for_evining.setVisibility(View.GONE);
                    im_plus_evining.setVisibility(View.VISIBLE);
                    im_minus_evining.setVisibility(View.GONE);
                }

                if (rl_expand_for_night.getVisibility()==View.VISIBLE){
                    rl_expand_for_night.setVisibility(View.GONE);
                    im_plus_night.setVisibility(View.VISIBLE);
                    im_minus_night.setVisibility(View.GONE);
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

    public void plusClickListenerForEvining(){

        rl_plus_minus_evining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rl_expand_for_morning.getVisibility()==View.VISIBLE){
                    rl_expand_for_morning.setVisibility(View.GONE);
                    im_plus.setVisibility(View.VISIBLE);
                    im_minus.setVisibility(View.GONE);
                }
                if (rl_expand_for_aftermoon.getVisibility()==View.VISIBLE){
                    rl_expand_for_aftermoon.setVisibility(View.GONE);
                    im_plus_aftermoon.setVisibility(View.VISIBLE);
                    im_minus_aftermoon.setVisibility(View.GONE);
                }

                if (rl_expand_for_night.getVisibility()==View.VISIBLE){
                    rl_expand_for_night.setVisibility(View.GONE);
                    im_plus_night.setVisibility(View.VISIBLE);
                    im_minus_night.setVisibility(View.GONE);
                }

                if (rl_expand_for_evining.getVisibility()==View.VISIBLE){

                    im_plus_evining.setVisibility(View.VISIBLE);
                    im_minus_evining.setVisibility(View.GONE);
                    rl_expand_for_evining.setVisibility(View.GONE);
                }
                else if (rl_expand_for_evining.getVisibility()==View.GONE){

                    im_plus_evining.setVisibility(View.GONE);
                    im_minus_evining.setVisibility(View.VISIBLE);
                    rl_expand_for_evining.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    //
    public void plusClickListenerForNight(){

        rl_plus_minus_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (rl_expand_for_morning.getVisibility()==View.VISIBLE){
                    rl_expand_for_morning.setVisibility(View.GONE);
                    im_plus.setVisibility(View.VISIBLE);
                    im_minus.setVisibility(View.GONE);
                }
                if (rl_expand_for_aftermoon.getVisibility()==View.VISIBLE){
                    rl_expand_for_aftermoon.setVisibility(View.GONE);
                    im_plus_aftermoon.setVisibility(View.VISIBLE);
                    im_minus_aftermoon.setVisibility(View.GONE);
                }


                if (rl_expand_for_evining.getVisibility()==View.VISIBLE){
                    rl_expand_for_evining.setVisibility(View.GONE);
                    im_plus_evining.setVisibility(View.VISIBLE);
                    im_minus_evining.setVisibility(View.GONE);
                }


                if (rl_expand_for_night.getVisibility()==View.VISIBLE){

                    im_plus_night.setVisibility(View.VISIBLE);
                    im_minus_night.setVisibility(View.GONE);
                    rl_expand_for_night.setVisibility(View.GONE);
                }
                else if (rl_expand_for_night.getVisibility()==View.GONE){

                    im_plus_night.setVisibility(View.GONE);
                    im_minus_night.setVisibility(View.VISIBLE);
                    rl_expand_for_night.setVisibility(View.VISIBLE);
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

                SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
                SimpleDateFormat yearFormate = new SimpleDateFormat("yyyy");
                SimpleDateFormat monthFormate = new SimpleDateFormat("MM");
                SimpleDateFormat dayformate = new SimpleDateFormat("dd");

                Calendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DATE, 1);
                String year = yearFormate.format(calendar.getTime());
                Log.e("TAG", "Year: " + year);
                String month = monthFormate.format(calendar.getTime());
                Log.e("TAG", "Mont: " + month);
                String currentday = dayformate.format(calendar.getTime());
                Log.e("TAG", "Day: " + currentday);


                final int claYear = Integer.parseInt(year);
                final int clamonth = Integer.parseInt(month);
                final int claday = Integer.parseInt(currentday);

                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("TAG", selectedHour + ":" + selectedMinute);
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);


                   /*     if((selectedHour <= (mcurrentTime.get(Calendar.HOUR_OF_DAY)))&&
                                (selectedMinute <= (mcurrentTime.get(Calendar.MINUTE)))){
                            Toast.makeText(getActivity(), "You have selected past time",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else*/ if(selectedHour>5 && selectedHour<12) { //setting range of time for morning
                            startAlarmForMorning(selectedHour, selectedMinute, claYear, clamonth, claday);
                        }

                        else {
                            Toast.makeText(getActivity(), "Out Of Range...", Toast.LENGTH_SHORT).show();
                        }


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

                SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
                SimpleDateFormat yearFormate = new SimpleDateFormat("yyyy");
                SimpleDateFormat monthFormate = new SimpleDateFormat("MM");
                SimpleDateFormat dayformate = new SimpleDateFormat("dd");

                Calendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DATE, 1);
                String year = yearFormate.format(calendar.getTime());
                Log.e("TAG", "Year: " + year);
                String month = monthFormate.format(calendar.getTime());
                Log.e("TAG", "Mont: " + month);
                String currentday = dayformate.format(calendar.getTime());
                Log.e("TAG", "Day: " + currentday);


                final int claYear = Integer.parseInt(year);
                final int clamonth = Integer.parseInt(month);
                final int claday = Integer.parseInt(currentday);

                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;


                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("TAG", selectedHour + ":" + selectedMinute);
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);

                      /*  if((selectedHour <= (mcurrentTime.get(Calendar.HOUR_OF_DAY)))&&
                                (selectedMinute <= (mcurrentTime.get(Calendar.MINUTE)))){
                            Toast.makeText(getActivity(), "You have selected past time",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else*/ if(selectedHour>=12 && selectedHour<18) { //setting range of time for aftermoon

                            startAlarmForAftermoon(selectedHour, selectedMinute, claYear, clamonth, claday);
                        }

                        else {
                            Toast.makeText(getActivity(), "Out Of Range...", Toast.LENGTH_SHORT).show();
                        }




                    }
                }, hour, minute, false);//Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();



            }
        });
    }
    //

    public void setAllarmForEvening(){

        rl_expand_for_evining.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
                SimpleDateFormat yearFormate = new SimpleDateFormat("yyyy");
                SimpleDateFormat monthFormate = new SimpleDateFormat("MM");
                SimpleDateFormat dayformate = new SimpleDateFormat("dd");

                Calendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DATE, 1);
                String year = yearFormate.format(calendar.getTime());
                Log.e("TAG", "Year: " + year);
                String month = monthFormate.format(calendar.getTime());
                Log.e("TAG", "Mont: " + month);
                String currentday = dayformate.format(calendar.getTime());
                Log.e("TAG", "Day: " + currentday);


                final int claYear = Integer.parseInt(year);
                final int clamonth = Integer.parseInt(month);
                final int claday = Integer.parseInt(currentday);

                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;


                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("TAG", selectedHour + ":" + selectedMinute);
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);

                       /* if((selectedHour <= (mcurrentTime.get(Calendar.HOUR_OF_DAY)))&&
                                (selectedMinute <= (mcurrentTime.get(Calendar.MINUTE)))){
                            Toast.makeText(getActivity(), "You have selected past time",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else*/ if(selectedHour>=18 && selectedHour<21) { //setting range of time for evining

                            startAlarmForEvining(selectedHour, selectedMinute, claYear, clamonth, claday);
                        }

                        else {
                            Toast.makeText(getActivity(), "Out Of Range...", Toast.LENGTH_SHORT).show();
                        }




                    }
                }, hour, minute, false);//Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();



            }
        });
    }

    //

    public void setAllarmForNight(){

        rl_expand_for_night.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                final SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
                SimpleDateFormat yearFormate = new SimpleDateFormat("yyyy");
                SimpleDateFormat monthFormate = new SimpleDateFormat("MM");
                SimpleDateFormat dayformate = new SimpleDateFormat("dd");

                Calendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DATE, 1);
                String year = yearFormate.format(calendar.getTime());
                Log.e("TAG", "Year: " + year);
                String month = monthFormate.format(calendar.getTime());
                Log.e("TAG", "Mont: " + month);
                String currentday = dayformate.format(calendar.getTime());
                Log.e("TAG", "Day: " + currentday);


                final int claYear = Integer.parseInt(year);
                final int clamonth = Integer.parseInt(month);
                final int claday = Integer.parseInt(currentday);

                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;


                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("TAG", selectedHour + ":" + selectedMinute);
                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);

                       /* if((selectedHour <= (mcurrentTime.get(Calendar.HOUR_OF_DAY)))&&
                                (selectedMinute <= (mcurrentTime.get(Calendar.MINUTE)))){
                            Toast.makeText(getActivity(), "You have selected past time",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else*/
                        if(selectedHour>=21) { //setting range of time for night

                            if (selectedHour>=21 && selectedHour<24){
                                startAlarmForNight(selectedHour, selectedMinute, claYear, clamonth, claday);
                            }
                        }
                        else if (selectedHour>=0 && selectedHour<6){
                            startAlarmForNight(selectedHour, selectedMinute, claYear, clamonth, claday);
                        }

                        else {
                            Toast.makeText(getActivity(), "Out Of Range...", Toast.LENGTH_SHORT).show();
                        }




                    }
                }, hour, minute, false);//Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();



            }
        });
    }

    //


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startAlarmForMorning(int timeHour, int timeMinute, int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Log.e("TAG", "Year: " + year);
        Log.e("TAG", "month: " + month);
        Log.e("TAG", "DAY: " + day);
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        Log.e("TAG", "TimeHousr: " + timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntentForMorning);

    }

    //

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startAlarmForAftermoon(int timeHour, int timeMinute, int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Log.e("TAG", "Year: " + year);
        Log.e("TAG", "month: " + month);
        Log.e("TAG", "DAY: " + day);
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        Log.e("TAG", "TimeHousr: " + timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntentForAftermoon);

    }
    //

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startAlarmForEvining(int timeHour, int timeMinute, int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Log.e("TAG", "Year: " + year);
        Log.e("TAG", "month: " + month);
        Log.e("TAG", "DAY: " + day);
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        Log.e("TAG", "TimeHousr: " + timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntentForEvening);

    }
    //

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startAlarmForNight(int timeHour, int timeMinute, int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Log.e("TAG", "Year: " + year);
        Log.e("TAG", "month: " + month);
        Log.e("TAG", "DAY: " + day);
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        Log.e("TAG", "TimeHousr: " + timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntentForNight);

    }
    //

    public void settingDynamicAlarm(){

        floating_circule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
                SimpleDateFormat yearFormate = new SimpleDateFormat("yyyy");
                SimpleDateFormat monthFormate = new SimpleDateFormat("MM");
                SimpleDateFormat dayformate = new SimpleDateFormat("dd");

                Calendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DATE, 1);
                String year = yearFormate.format(calendar.getTime());
                Log.e("TAG", "Year: " + year);
                String month = monthFormate.format(calendar.getTime());
                Log.e("TAG", "Mont: " + month);
                String currentday = dayformate.format(calendar.getTime());
                Log.e("TAG", "Day: " + currentday);


                final int claYear = Integer.parseInt(year);
                final int clamonth = Integer.parseInt(month);
                final int claday = Integer.parseInt(currentday);

                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;


                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.e("TAG", selectedHour + ":" + selectedMinute);




                        // eReminderTime.setText( selectedHour + ":" + selectedMinute);

                       /* if((selectedHour <= (mcurrentTime.get(Calendar.HOUR_OF_DAY)))&&
                                (selectedMinute <= (mcurrentTime.get(Calendar.MINUTE)))){
                            Toast.makeText(getActivity(), "You have selected past time",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else*/

                        if(selectedHour>5 && selectedHour<12) { //setting range of time for morning
                            startAlarmForMorning(selectedHour, selectedMinute, claYear, clamonth, claday);
                        }
                        else if(selectedHour>=12 && selectedHour<18) { //setting range of time for aftermoon

                            startAlarmForAftermoon(selectedHour, selectedMinute, claYear, clamonth, claday);
                        }

                        else if(selectedHour>=18 && selectedHour<21) { //setting range of time for evining

                            startAlarmForEvining(selectedHour, selectedMinute, claYear, clamonth, claday);
                        }

                        else if(selectedHour>=21) { //setting range of time for night

                            if (selectedHour>=21 && selectedHour<24){
                                startAlarmForNight(selectedHour, selectedMinute, claYear, clamonth, claday);
                            }
                        }
                        else if (selectedHour>=0 && selectedHour<6){
                            startAlarmForNight(selectedHour, selectedMinute, claYear, clamonth, claday);
                        }

                        else {
                            Toast.makeText(getActivity(), "Out Of Range...", Toast.LENGTH_SHORT).show();
                        }




                    }
                }, hour, minute, false);//Yes 24 hour time

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }


}