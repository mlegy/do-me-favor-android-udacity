
package com.domefavor.android.shared;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.domefavor.android.App;
import com.domefavor.android.R;
import com.domefavor.android.features.favors.favorsFeed.FeedActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.navigation)
    public NavigationView navigation;

    @Nullable
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Inject
    NavigationVM navigationVM;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getApp().getVMComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        if (useNavigationDrawer())
            initNavigationDrawer();
        if (useToolbar())
            setSupportActionBar(toolbar);
    }

    protected boolean useNavigationDrawer() {
        return false;
    }

    protected boolean useToolbar() {
        return false;
    }

    private void initNavigationDrawer() {
        if (navigation != null && drawerLayout != null) {
            navigation.setNavigationItemSelectedListener(
                    menuItem -> {
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.nav_item_feed:
                                FeedActivity.start(this);
                                drawerLayout.closeDrawers();
                                break;
                            case R.id.nam_item_settings:
                                drawerLayout.closeDrawers();
                                break;
                        }
                        return true;
                    });

            View header = navigation.getHeaderView(0);

            TextView name = (TextView) header.findViewById(R.id.name_text_view);
            name.setText(navigationVM.getUserFirstName());

            SimpleDraweeView avatar = (SimpleDraweeView) header.findViewById(R.id.avatar_image);
            avatar.setImageURI(navigationVM.getUserAvatarURL());

            navigation.setItemIconTintList(null);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                    R.string.drawer_open, R.string.drawer_close) {

                @Override
                public void onDrawerClosed(View v) {
                    super.onDrawerClosed(v);
                }

                @Override
                public void onDrawerOpened(View v) {
                    super.onDrawerOpened(v);
                }
            };
            if (drawerLayout != null) {
                drawerLayout.addDrawerListener(actionBarDrawerToggle);
            }
            actionBarDrawerToggle.syncState();
        }
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }

}
