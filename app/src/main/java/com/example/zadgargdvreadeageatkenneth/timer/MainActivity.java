package com.example.zadgargdvreadeageatkenneth.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            long millis = System.currentTimeMillis() - startTime;
            //int minute
            int time_lenght = 1*60*1000;
            int Rmillis = time_lenght - (int)millis;
            Rmillis = Rmillis %1000;
            //int minute
            int seconds = (int) ((time_lenght - millis) / 1000);
            int minutes = seconds / 60;

            seconds = seconds % 60;
            if((int)(time_lenght - millis) >=0){
            timerTextView.setText(String.format("%d:%02d:%03d", minutes, seconds, Rmillis));

            timerHandler.postDelayed(this, 1);}else{timerTextView.setText(String.format("%d:%02d:%03d", 00, 00, 0000));}
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        Button b = (Button) findViewById(R.id.button);
        b.setText("start");
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("cont");
                } else if(b.getText().equals("start")){
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("stop");
                }else{timerHandler.post(timerRunnable);
                    b.setText("stop");}
            }
        });

        Button bb = (Button) findViewById(R.id.restart);
        bb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View q){
                Button bb = (Button) q;
                timerHandler.removeCallbacks(timerRunnable);
                Button b = (Button) findViewById(R.id.button);
                b.setText("start");
                timerTextView.setText(String.format("%d:%02d:%03d", 40, 00,0000 ));
            }

        });
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.button);
        b.setText("start");
    }
}
