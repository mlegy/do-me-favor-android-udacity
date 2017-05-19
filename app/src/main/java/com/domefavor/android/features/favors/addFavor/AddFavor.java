
package com.domefavor.android.features.favors.addFavor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.domefavor.android.R;

public class AddFavor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public static void start(Activity callerActivity) {
        callerActivity.startActivity(new Intent(callerActivity, AddFavor.class));
    }
}
