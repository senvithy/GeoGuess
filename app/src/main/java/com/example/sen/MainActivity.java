package com.example.sen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID="simplified_coding";
    private static final String CHANNEL_NAME="simplified Coding";
    private static final String CHANNEL_DESC="Simplified Coding Notify";
    String name;
    EditText nameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameInput=(EditText) findViewById(R.id.nameInput);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        findViewById(R.id.buttonNotify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= nameInput.getText().toString();
                displayNotification();
                openActivity2();

            }
        });

    }
    public void openActivity2(){
       Intent intent=new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

    private void displayNotification(){


        NotificationCompat.Builder mBuilder=
                new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_favorite_black_24dp)
                    .setContentTitle("Wassup")
                    .setContentText(name)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                ;

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());

    }
}
