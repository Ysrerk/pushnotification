package com.example.pushnotifikasyon2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.onesignal.OneSignal;

import Models.Result;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {


    String kullanicivv,kodvv;
    EditText kullaniciadim;
    EditText kodm;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tanimla();
        degerlerial();


    }

    public void tanimla(){
        kullaniciadim=findViewById(R.id.kullaniciadi2);
        kodm=findViewById(R.id.kod2);
        button=findViewById(R.id.aktifet);

    }
    public void  degerlerial(){

        Bundle intent=getIntent().getExtras();
        kullanicivv=intent.getString("kullaniciadi");

        kullaniciadim.setText(kullanicivv);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                kodvv=kodm.getText().toString();
                Log.i("bakalim",""+kodvv);
                istek2(kullanicivv,kodvv);
            }

        });

    }
    public void istek2(String kullanicivv,String kodvv){


                ManagerAll managerAll=new ManagerAll();
                Call<Result>call=managerAll.getaktiff(kullanicivv,kodvv);
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {

                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                        kullaniciadim.setText("");
                        kodm.setText("");

                    }
                });




    }
}