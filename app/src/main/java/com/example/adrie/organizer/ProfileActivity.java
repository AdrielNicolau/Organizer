package com.example.adrie.organizer;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProfileActivity extends Activity {
    @BindViews({R.id.txtView_profileEmail, R.id.txtView_profileName, R.id.txtView_profileStudentNumber})
    List<TextView> mProfileViews;

    @BindViews({R.id.txtField_profileEditEmail, R.id.txtField_profileEditName, R.id.txtField_profileEditStudntNum})
    List<EditText> mProfileEditFields;

    @BindView(R.id.bt_saveChangesEditProfile)
    Button mSaveButton;

    @BindView(R.id.bt_profileEdit)

    Button mEditButton;

    Profile mProfile = new Profile();
    FileManagement mFileManagement = new FileManagement(this, "Profile");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setProfileEditFieldsVisibility(false);
        mSaveButton.setVisibility(View.INVISIBLE);
        fillProfileFields(1);

    }

    public void fillProfileFields(int verify) {

        mProfile = mFileManagement.readFileProfile();
        if (verify == 1) {

            mProfileViews.get(1).setText(mProfile.getName());

            mProfileViews.get(0).setText(mProfile.getStudentNumber());

            mProfileViews.get(2).setText(mProfile.getEmail());

        } else {

            mProfileEditFields.get(1).setText(mProfile.getName());

            mProfileEditFields.get(0).setText(mProfile.getStudentNumber());

            mProfileEditFields.get(2).setText(mProfile.getEmail());

        }


    }


    public void setProfileViewsVisibility(boolean v) {
        int i = 0;
        if (v == true) {
            while (i < mProfileViews.size()) {
                mProfileViews.get(i).setVisibility(View.VISIBLE);
                i++;
            }
        } else {
            while (i < mProfileViews.size()) {
                mProfileViews.get(i).setVisibility(View.INVISIBLE);
                i++;
            }
        }
    }

    public void setProfileEditFieldsVisibility(boolean v) {
        int i = 0;
        if (v == true) {
            while (i < mProfileEditFields.size()) {
                mProfileEditFields.get(i).setVisibility(View.VISIBLE);
                i++;
            }
        } else {
            while (i < mProfileEditFields.size()) {
                mProfileEditFields.get(i).setVisibility(View.INVISIBLE);
                i++;
            }
        }
    }

    @OnClick(R.id.bt_profileEdit)
    public void editProfile(View v) {
        setProfileViewsVisibility(false);
        setProfileEditFieldsVisibility(true);
        fillProfileFields(0);
        mSaveButton.setVisibility(v.VISIBLE);
        mEditButton.setVisibility(v.INVISIBLE);
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

                    if (mProfileEditFields.get(1).getText().toString().equals("") || mProfileEditFields.get(0).getText().toString().equals("") || mProfileEditFields.get(2).getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "TRY AGAIN", Toast.LENGTH_LONG).show();
                    } else {
                        String profileInfo = "1" + "\n" + mProfileEditFields.get(1).getText().toString() + "\n" + mProfileEditFields.get(0).getText().toString() + "\n" + mProfileEditFields.get(2).getText().toString();
                        mFileManagement.writeFile(profileInfo);
                    }
                    setProfileEditFieldsVisibility(false);
                    setProfileViewsVisibility(true);
                    mSaveButton.setVisibility(View.INVISIBLE);
                    mEditButton.setVisibility(View.VISIBLE);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(getApplicationContext(), "TEST 2222", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

}
