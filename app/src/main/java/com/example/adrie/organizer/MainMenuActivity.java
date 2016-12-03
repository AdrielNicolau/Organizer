package com.example.adrie.organizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.bt_showTimetable)
    public void showTimetableMenu(View view) {
        // Intent intent = new Intent(this, "poraqui o nome da activida a abrir".class);
        //startActivity(intent);
    }

    @OnClick(R.id.bt_editTimetable)
    public void editTimetableMenu(View view) {
        // Intent intent = new Intent(this, "poraqui o nome da activida a abrir".class);
        //startActivity(intent);
    }

    @OnClick(R.id.bt_sendTimetable)
    public void sendTimetableMenu(View view) {
        // Intent intent = new Intent(this, "poraqui o nome da activida a abrir".class);
        //startActivity(intent);
    }

    @OnClick(R.id.bt_profile)
    public void ProfileMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_LONG).show();
    }


}
