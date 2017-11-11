package rdm.medicimalarm;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.TabLayout;
import android.view.MotionEvent;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SetReminder extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager view_pager;
    ArrayList<String> listDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);


        init();
        gettingNextDaysOfWeek();
        addintTabs();
        addingFragmentPages();




    }

    private void init(){

        tabLayout = (TabLayout) findViewById(R.id.tb_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        listDays = new ArrayList<>();

    }

    private void gettingNextDaysOfWeek(){

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
        for (int i = 0; i < 7; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i);
            String day = sdf.format(calendar.getTime());
            Log.e("TAG", "DAYAS " + day);
            listDays.add(day);
        }

    }

    private void addintTabs(){
        //adding tabs
        tabLayout.addTab(tabLayout.newTab().setText(listDays.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(listDays.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(listDays.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(listDays.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(listDays.get(4)));
        tabLayout.addTab(tabLayout.newTab().setText(listDays.get(5)));
        tabLayout.addTab(tabLayout.newTab().setText(listDays.get(6)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    private void addingFragmentPages(){

        //setting adapter to viewpager
        final PageFragmentAdapter adapter = new PageFragmentAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        view_pager.setAdapter(adapter);

        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        view_pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                view_pager.setCurrentItem(tab.getPosition());

                int position = tab.getPosition();
                if (position==0){


                }
                if (position==1){

                    // Toast.makeText(MainActivity.this, "Seller", Toast.LENGTH_SHORT).show();
                }
                if (position==2){

                    //Toast.makeText(MainActivity.this, "Most Viewed", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
