package rdm.medicimalarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   private Button btReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        startReminderClass();


    }

    private void init(){

        btReminder = (Button) findViewById(R.id.bt_set_reminider);
    }

    private void startReminderClass(){

        btReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reminderActivity = new Intent(MainActivity.this, SetReminder.class);
                startActivity(reminderActivity);


            }
        });
    }
}
