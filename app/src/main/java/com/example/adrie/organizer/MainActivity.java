package com.example.adrie.organizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @BindViews({R.id.txtField_email, R.id.txtField_name, R.id.txtField_studentNumber})
    List<EditText> mProfileViews;

    FileManagement mFileManagement = new FileManagement(this, "Profile");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        if (mFileManagement.verifyProfileFileExist() == true) {
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.bt_register)
    public void createProfile(View view) {

        if (mProfileViews.get(1).getText().toString().equals("") || mProfileViews.get(0).getText().toString().equals("") || mProfileViews.get(2).getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "important", Toast.LENGTH_LONG).show();
        } else {

            Profile profile = new Profile(mProfileViews.get(1).getText().toString(), mProfileViews.get(2).getText().toString(), mProfileViews.get(0).getText().toString());
            String profileInfo = "1" + "\n" + profile.getName() + "\n" + profile.getEmail() + "\n" + profile.getStudentNumber();
            mFileManagement.writeFile(profileInfo);
        }
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }


}

