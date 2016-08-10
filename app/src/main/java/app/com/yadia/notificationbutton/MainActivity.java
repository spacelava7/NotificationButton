package app.com.yadia.notificationbutton;

import android.app.Activity;
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

               // runExpandedNotification();
                //runUpdatableNotification();
                runSpecialNotifications();
                //runNotification();
                Log.e(LOG_TAG, "OnCLickListener running");
            }
        });

    }

    /**
     * Build a simple notification with a Pending Intent which leads to an activity in the app
     */
    public void runNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.circle)
                .setContentTitle("Notification!")
                .setContentText("Click the notification to see Doge");

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
        int mId = 1;
        mNotificationManager.notify(mId ,mBuilder.build());
    }

    /**
     * Builds a notification with an expanded layout
     */
    public void runExpandedNotification(){
        Log.i(LOG_TAG, "Expanded layout notification running");

        int numMessages = 1;

        /* Invoking the default notification service */
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(R.drawable.circle);

   /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++numMessages);

   /* Add Big View Specific Configuration */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = new String("This is first line....");
        events[1] = new String("This is second line...");
        events[2] = new String("This is third line...");
        events[3] = new String("This is 4th line...");
        events[4] = new String("This is 5th line...");
        events[5] = new String("This is 6th line...");

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Big Title Details:");

        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);

   /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, NotificationResultActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationResultActivity.class);

   /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationID = 1;

   /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(notificationID, mBuilder.build());
    }

    public void runUpdatableNotification(){
        Log.e(LOG_TAG, "Updatable notification running");

        int numMessages = 0;

       // for (int i = 0; i < )

        int notifyID = 1 ;  //notification ID

        NotificationManager mNotifcationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.circle)
                .setTicker(getString(R.string.notificationTicker))
                .setContentTitle("New Notification Message")
                .setContentText("You have a new message");

        //Increase the notification number every time a new notification arrives
        mBuilder.setNumber(++numMessages);

        mNotifcationManager.notify(notifyID, mBuilder.build());
    }

    public void runSpecialNotifications(){
        Log.i(LOG_TAG, "Running the special Notification");

        int notifyID = 1;
        String title = "Notification Result";
        String notiText = "New Result Activity";

        Intent resultIntent = new Intent(this, ResultActivity.class);
        //send data to NotificationResult class
        resultIntent.putExtra("title", title);
        resultIntent.putExtra("text", notiText);

        Intent actionIntent = new Intent(MainActivity.this, ActionActivity.class);

        TaskStackBuilder taskBuilder = TaskStackBuilder.create(this);
        taskBuilder.addParentStack(ResultActivity.class);
        //add intent to the stack
        taskBuilder.addNextIntent(resultIntent);


        //Open ResultActivity.java Activity
        PendingIntent pendingIntent = taskBuilder.getPendingIntent(12, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0 , actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT ); // req code is any code you want to use

        //Build the notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.circle)
                .setContentTitle(getString(R.string.notificationTitle))
                .setContentText("Notification message here!");

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.addAction(R.drawable.ic_info_black_24dp, "Open", actionPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifyID, mBuilder.build());

    }
}
