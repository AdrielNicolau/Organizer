package com.example.adrie.organizer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class ProfileActivity extends Activity {
    @BindViews({R.id.txtView_profileEmail, R.id.txtView_profileName, R.id.txtView_profileStudentNumber})
    List<TextView> profileViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);


      fillProfileFields();
    }

    public void fillProfileFields(){
        FileInputStream fileInputStream;
        try {
            fileInputStream = openFileInput("Profile");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            int i=0;
            bufferedReader.readLine().toString();
            while (i<4){

                if(i==1){
                    profileViews.get(1).setText(bufferedReader.readLine().toString());
              }else if(i==2){
                   profileViews.get(0).setText(bufferedReader.readLine().toString());
                }else if(i==3){
                   profileViews.get(2).setText(bufferedReader.readLine().toString());
               }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
