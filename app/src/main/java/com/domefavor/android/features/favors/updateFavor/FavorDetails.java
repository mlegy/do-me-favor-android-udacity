package com.domefavor.android.features.favors.updateFavor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.domefavor.android.R;

public class FavorDetails extends AppCompatActivity {

    public static String FAVOR_ID_KEY = "favor_id";
    public static String FAVOR_DISTANCE_KEY = "favor_distance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public static void start(Context context, String favorId, float distance){
        Intent intent = new Intent(context, FavorDetails.class);
        intent.putExtra(FAVOR_ID_KEY, favorId);
        intent.putExtra(FAVOR_DISTANCE_KEY, distance);
        context.startActivity(intent);
    }

}
