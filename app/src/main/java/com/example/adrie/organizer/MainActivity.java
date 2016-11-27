package com.example.adrie.organizer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindViews({R.id.txtField_email, R.id.txtField_name, R.id.txtField_studentNumber})
    List<EditText> profileViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        verifyProfileFileExist();
    }


    public void createProfile(View view) {
        String fileName = "Profile";
        if (profileViews.get(1).getText().toString().equals("") || profileViews.get(0).getText().toString().equals("") || profileViews.get(2).getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "TRY AGAIN", Toast.LENGTH_LONG).show();
        } else {
            String profileInfo = "1" + "\n" + profileViews.get(1).getText().toString() + "\n" + profileViews.get(0).getText().toString() + "\n" + profileViews.get(2).getText().toString();
            try {
                FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                fileOutputStream.write(profileInfo.getBytes());
                fileOutputStream.close();

                Toast.makeText(getApplicationContext(), "Profile Created", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void verifyProfileFileExist() {


        FileInputStream fileInputStream;
        try {
            fileInputStream = openFileInput("Profile");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //StringBuffer stringBuffer = new StringBuffer();
            String verifyProfile = bufferedReader.readLine();
            if (verifyProfile.equals("1")) {
                Toast.makeText(getApplicationContext(), "Ja EXISTE", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "NOPE", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

