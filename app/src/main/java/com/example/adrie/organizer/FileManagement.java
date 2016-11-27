package com.example.adrie.organizer;


import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by adrie on 27/11/2016.
 */

public class FileManagement {

    private Context mContext;
    private String mFileName;


    public FileManagement(Context context, String fileName) {
        this.mContext = context;
        this.mFileName = fileName;

    }

    public void writeFile(String information) {
        try {
            FileOutputStream fileOutputStream = mContext.openFileOutput(mFileName, mContext.MODE_PRIVATE);
            fileOutputStream.write(information.getBytes());
            fileOutputStream.close();

            Toast.makeText(mContext, "Profile Saved", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyProfileFileExist() {


        FileInputStream fileInputStream;
        try {
            fileInputStream = mContext.openFileInput(mFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String verifyProfile = bufferedReader.readLine();
            if (verifyProfile.equals("1")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Profile readFileProfile() {

        FileInputStream fileInputStream;

        try {
            fileInputStream = mContext.openFileInput(mFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.readLine().toString();
            Profile profile = new Profile(bufferedReader.readLine().toString(),bufferedReader.readLine().toString(),bufferedReader.readLine().toString());
            return profile;
        } catch (IOException e) {
            e.printStackTrace();
        }

     return null;
    }
}


