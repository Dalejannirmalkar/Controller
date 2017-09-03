package app.nirmlkar.dalejan.controller;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button onReciver, stopreciver;
    private AlphaAnimation buttonClick = new AlphaAnimation(6F, 1F);
    static boolean hello ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
Log.d("yyyyyyy1","hello");
        sharedPreferences = getSharedPreferences("Myshared", getApplicationContext().MODE_PRIVATE);
        Log.d("yyyyyyy2","hello");
        editor = sharedPreferences.edit();
        Log.d("yyyyyyy3","hello");
        String hello1 = sharedPreferences.getString("hello", "");
        Log.d("yyyyyyy",hello1);
        if (hello1.equalsIgnoreCase("true")) {
            hello = true;
            Log.d("yyyyyyy4",hello+"");
        }
        else {
            hello=false;
            Log.d("yyyyyyy5",hello+"");
        }




        onReciver = findViewById(R.id.start);
        stopreciver = findViewById(R.id.stop);

        onReciver.setOnClickListener(this);


        stopreciver.setOnClickListener(this);
        Log.d("yyyyyyy6",hello+"");
        Update(hello);

    }


    public void Update(boolean he) {
        if (he) {
            Log.d("yyyyyyy7",he+"");
onReciver.setBackgroundResource(R.drawable.pressed);
stopreciver.setBackgroundResource(R.drawable.buttoncorner);

        }
        if (!he) {
            Log.d("yyyyyyy8",he+"");
            onReciver.setBackgroundResource(R.drawable.buttoncorner);
            stopreciver.setBackgroundResource(R.drawable.pressed);

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.start:

                if (!hello) {
                    onReciver.setAnimation(buttonClick);
                    stopreciver.setBackgroundResource(R.drawable.buttoncorner);
                    onReciver.setBackgroundResource(R.drawable.pressed);

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), "Reciver Start", duration);
                    toast.show();

                    editor.clear();
                    editor.putString("hello", "true");
                    editor.commit();

                    hello = true;
                    startService(new Intent(getApplicationContext(), SmsService.class));

                } else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), "Reciver already Register", duration);
                    toast.show();
                }
                break;


            case R.id.stop:

                if (hello) {

                    stopreciver.setAnimation(buttonClick);
                    onReciver.setBackgroundResource(R.drawable.buttoncorner);
                    stopreciver.setBackgroundResource(R.drawable.pressed);

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), "Reciver Stop", duration);

                    toast.show();
                    editor.clear();
                    editor.putString("hello","false");
                    editor.commit();

                    hello = false;
                    stopService(new Intent(getApplicationContext(), SmsService.class));

                } else {

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), "Reciver already Stop", duration);
                    toast.show();

                }
                break;


        }
    }
}
