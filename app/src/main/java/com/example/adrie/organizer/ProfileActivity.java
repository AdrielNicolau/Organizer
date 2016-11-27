package com.example.adrie.organizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.visible;

public class ProfileActivity extends Activity {
    @BindViews({R.id.txtView_profileEmail, R.id.txtView_profileName, R.id.txtView_profileStudentNumber})
    List<TextView> profileViews;
    @BindViews({R.id.txtField_profileEditEmail, R.id.txtField_profileEditName, R.id.txtField_profileEditStudntNum})
    List<EditText> profileEditFields;
    @BindView(R.id.bt_saveChangesEditProfile)
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setProfileEditFieldsVisibility(false);
        saveButton.setVisibility(View.INVISIBLE);
        fillProfileFields(1);
    }

    public void fillProfileFields(int verify) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = openFileInput("Profile");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            int i = 0;
            bufferedReader.readLine().toString();
            if (verify == 1) {
                while (i < 4) {

                    if (i == 1) {
                        profileViews.get(1).setText(bufferedReader.readLine().toString());
                    } else if (i == 2) {
                        profileViews.get(0).setText(bufferedReader.readLine().toString());
                    } else if (i == 3) {
                        profileViews.get(2).setText(bufferedReader.readLine().toString());
                    }
                    i++;
                }
            } else {
                while (i < 4) {

                    if (i == 1) {
                        profileEditFields.get(1).setText(bufferedReader.readLine().toString());
                    } else if (i == 2) {
                        profileEditFields.get(0).setText(bufferedReader.readLine().toString());
                    } else if (i == 3) {
                        profileEditFields.get(2).setText(bufferedReader.readLine().toString());
                    }
                    i++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProfileViewsVisibility(boolean v) {
        int i = 0;
        if (v == true) {
            while (i < profileViews.size()) {
                profileViews.get(i).setVisibility(View.VISIBLE);
                i++;
            }
        } else {
            while (i < profileViews.size()) {
                profileViews.get(i).setVisibility(View.INVISIBLE);
                i++;
            }
        }
    }

    public void setProfileEditFieldsVisibility(boolean v) {
        int i = 0;
        if (v == true) {
            while (i < profileEditFields.size()) {
                profileEditFields.get(i).setVisibility(View.VISIBLE);
                i++;
            }
        } else {
            while (i < profileEditFields.size()) {
                profileEditFields.get(i).setVisibility(View.INVISIBLE);
                i++;
            }
        }

    }

    @OnClick(R.id.bt_profileEdit)
    public void editProfile(View v) {
        setProfileViewsVisibility(false);
        setProfileEditFieldsVisibility(true);
        fillProfileFields(0);
        saveButton.setVisibility(View.VISIBLE);


    }

    @OnClick(R.id.bt_saveChangesEditProfile)
    public void saveProfileChanges(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    String fileName = "Profile";
                    if (profileEditFields.get(1).getText().toString().equals("") || profileEditFields.get(0).getText().toString().equals("") || profileEditFields.get(2).getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "TRY AGAIN", Toast.LENGTH_LONG).show();
                    } else {
                        String profileInfo = "1" + "\n" + profileEditFields.get(1).getText().toString() + "\n" + profileEditFields.get(0).getText().toString() + "\n" + profileEditFields.get(2).getText().toString();
                        try {
                            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                            fileOutputStream.write(profileInfo.getBytes());
                            fileOutputStream.close();

                            Toast.makeText(getApplicationContext(), "Profile Created", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(getApplicationContext(), "TEST 2222", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

}
