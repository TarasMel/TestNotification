package com.example.taras.testnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFY_ID = 101;

    @BindView(R.id.txt_page_ID)
    TextView txt_page_state;

    @BindView(R.id.btn_plus_ID)
    ImageButton btn_plus_page;
    @BindView(R.id.btn_minus_ID)
    ImageButton btn_minus_page;
    @BindView(R.id.btn_createNotification_ID)
    Button btn_create_notification;

    @OnClick({R.id.btn_plus_ID, R.id.btn_minus_ID, R.id.btn_createNotification_ID})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_plus_ID:
                int i = Integer.parseInt(txt_page_state.getText().toString());
                txt_page_state.setText(Integer.toString(++i));
                break;
            case R.id.btn_minus_ID:
                if (Integer.parseInt(txt_page_state.getText().toString()) != 1){
                    int n = Integer.parseInt(txt_page_state.getText().toString());
                    txt_page_state.setText(Integer.toString(--n));
                }
                break;
            case R.id.btn_createNotification_ID:
                Intent notificationIntent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0,
                        notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                Resources res = this.getResources();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setContentIntent(contentIntent).setSmallIcon(R.drawable.n_2x).setContentTitle
                        ("Eshkere").
                        setContentText(res.getString(R.string.notification_title)+" "+
                                txt_page_state.getText().toString()+" "+res.getString(R.string.page));
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFY_ID, builder.build());
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}
