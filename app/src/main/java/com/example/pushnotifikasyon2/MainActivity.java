package com.example.pushnotifikasyon2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.onesignal.OneSignal;

import Models.Result;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String ONESIGNAL_APP_ID = "ed921bd1-5554-44c3-8b7c-e02a4dea5bf2";
    EditText kullaniciadi,sifre,email;
    Button kayit;
    String kullaniciadiv,sifrev,emailv,kodv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        tanimlama();
        istek();
        //gecisyap();
    }
    public void tanimlama(){
        kullaniciadi=findViewById(R.id.kullaniciadi);
        sifre=findViewById(R.id.sifre);
        email=findViewById(R.id.mail);
        kayit=findViewById(R.id.kayit);



    }

    public void istek(){
        kayit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ManagerAll managerAll=new ManagerAll();
                kullaniciadiv=kullaniciadi.getText().toString();
                sifrev=sifre.getText().toString();
                emailv=email.getText().toString();
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("kullaniciadi",kullaniciadiv);
                startActivity(intent);

                Call<Result> call=managerAll.getresultt(kullaniciadiv,sifrev,emailv);
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {




                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {



                    }
                });
            }






        });

    }
    public void gecisyap(){
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("kullaniciadi",kullaniciadiv);

                startActivity(intent);
            }
        });

    }
}