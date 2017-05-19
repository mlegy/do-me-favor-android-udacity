
package com.domefavor.android.features.favors.favorsFeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import com.domefavor.android.App;
import com.domefavor.android.R;
import com.domefavor.android.features.favors.addFavor.AddFavor;
import com.domefavor.android.features.favors.updateFavor.FavorDetails;
import com.domefavor.android.shared.BaseActivity;

import javax.inject.Inject;

public class FeedActivity extends BaseActivity implements FeedActivityFragment.Callback {

    @Inject
    FavorsFeedVM favorsFeedVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApp().getVMComponent().inject(this);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> AddFavor.start(this));
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, FeedActivity.class));
    }

    @Override
    protected boolean useToolbar() {
        return true;
    }

    @Override
    protected boolean useNavigationDrawer() {
        return true;
    }

    @Override
    public void onItemSelected(String favorId, float distance) {
        FavorDetails.start(this, favorId, distance);

    }
}
