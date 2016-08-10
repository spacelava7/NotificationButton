package app.com.yadia.notificationbutton;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    String resultTitle = "";
    String resultText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //dismiss the notification
        mNotificationManager.cancel(0);
        //Retrive data from previous activity
        Intent resultIntent = getIntent();
        resultTitle = resultIntent.getStringExtra("title");
        resultText = resultIntent.getStringExtra("text");

        TextView tvTitle = (TextView) findViewById(R.id.result_tv_title);
        TextView tvTextv = (TextView) findViewById(R.id.result_tv_text);

        tvTitle.setText(resultTitle);
        tvTextv.setText(resultText);

    }
}
