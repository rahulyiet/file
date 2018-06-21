package com.example.rahulyiet.kkkkkkk;

import android.icu.util.Output;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText enterData;
    Button readBtn, writeBtn;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterData = findViewById(R.id.dataEnter);
        readBtn = findViewById(R.id.readBtn);
        writeBtn = findViewById(R.id.writeBtn);
        textView = findViewById(R.id.edit);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = enterData.getText().toString();

                if(TextUtils.isEmpty(data)){
                    enterData.setError("required");
                    return;
                }
                FileOutputStream fos;

                try {

                    fos = openFileOutput("userData", MODE_PRIVATE);
                    fos.write(data.getBytes());

                    fos.close();
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }

                readBtn.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });


         readBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 BufferedReader  input =null;
                 File file = null;

                 try{
                     file = getBaseContext().getFileStreamPath("userData");
                     input =new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                     String line;
                     StringBuffer buffer = new StringBuffer();

                     while ((line = input.readLine()) !=null){
                         buffer.append(line);
                         input.close();
                         textView.setText(line);
                     }
                 }catch (FileNotFoundException e){
                     e.printStackTrace();
                 }

                 catch (Exception e){
                     e.printStackTrace();
                 }
             }
         });



    }
}
