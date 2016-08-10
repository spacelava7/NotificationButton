package app.com.yadia.notificationbutton;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notificationbtn = (Button) findViewById(R.id.buttonNotification);

        notificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runNotification();
                Log.e(LOG_TAG, "OnCLickListener running");
            }
        });

    }

    public void runNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.circle)
                .setContentTitle("Notification!")
                .setContentText("The notification Works!");

        //Create intent for an activity in the app
        Intent resultIntent = new Intent(MainActivity.this, NotificationResultActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //adds the back stack for the intent
        stackBuilder.addParentStack(NotificationResultActivity.class);
        //add the intent that starts the next activity
        stackBuilder.addNextIntent(resultIntent);


        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //ID allows you to update the notification later on
        int mId = 0;
        mNotificationManager.notify(mId ,mBuilder.build());
    }
}
