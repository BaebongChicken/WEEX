package com.kjstudio.weex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kjstudio.weex.Fragments.FindGroupFragment;
import com.kjstudio.weex.Fragments.FindMemberFragment;
import com.kjstudio.weex.Fragments.MessageFragment;
import com.kjstudio.weex.Fragments.MissionsFragment;
import com.kjstudio.weex.Fragments.MoreFragment;
import com.kjstudio.weex.Fragments.MyFriendsFragment;
import com.kjstudio.weex.Fragments.MyWeexFragment;
import com.kjstudio.weex.R;
import com.kjstudio.weex.dataClass.UserData;
import com.kjstudio.weex.utils.BackPressCloseHandler;
import com.kjstudio.weex.utils.ContextUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;
    private DrawerLayout dlDrawer;
    private ListView lvNavList;
    LinearLayout drawLayout;
    private String[] navItems = {"Messages", "Mission", "#WeExercise", "Members", "My Friends", "My WeExercise", "More"};
    public ImageButton toggleBtn;
    public TextView mTitleTextView;
    Button addBtn;
    ImageButton searchBtn;
    ImageButton searchDetailBtn;
    FrameLayout userProfileLayout;
    ImageView profileImg;


    ArrayList<Fragment> fragList = new ArrayList<Fragment>();
    Fragment currentFragment;

    LinearLayout fragment_mainContainer0;
    LinearLayout fragment_mainContainer1;
    LinearLayout fragment_mainContainer2;
    LinearLayout fragment_mainContainer3;
    LinearLayout fragment_mainContainer4;
    LinearLayout fragment_mainContainer5;
    LinearLayout fragment_mainContainer6;
    LinearLayout currentLayout;
    private Toolbar myawesometoolbar;
    private TextView userNameTxt;
    private ListView lvactivitymainnavlist;
    private DrawerLayout dlactivitymaindrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(MainActivity.this).build();
        ImageLoader.getInstance().init(config);
        setCustomActionbar();
        bindViews();
        setupEvents();
        setValues();

        addAllFrag();
    }


    void addAllFrag()
    {

        fragList.add(new MessageFragment());
        fragList.add(new MissionsFragment());
        fragList.add(new FindGroupFragment());
        fragList.add(new FindMemberFragment());
        fragList.add(new MyFriendsFragment());
        fragList.add(new MyWeexFragment());
        fragList.add(new MoreFragment());

        final FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();

        ft.add(R.id.fragment_mainContainer0, fragList.get(0));
        ft.add(R.id.fragment_mainContainer1, fragList.get(1));
        ft.add(R.id.fragment_mainContainer2, fragList.get(2));
        ft.add(R.id.fragment_mainContainer3, fragList.get(3));
        ft.add(R.id.fragment_mainContainer4, fragList.get(4));
        ft.add(R.id.fragment_mainContainer5, fragList.get(5));
        ft.add(R.id.fragment_mainContainer6, fragList.get(6));

        currentFragment = fragList.get(1);
        currentLayout = fragment_mainContainer1;
        ft.commit();

    }

    public void setCustomActionbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);


        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }

    public void setupEvents() {
        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dlDrawer.isDrawerOpen(drawLayout)) {
                    dlDrawer.closeDrawer(drawLayout);
                } else {
                    dlDrawer.openDrawer(drawLayout);
                }
            }
        });

        userProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(mIntent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent mIntent = new Intent(MainActivity.this, UserProfileActivity.class);
//                startActivity(mIntent);
            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    public void setValues() {
        lvNavList.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());
        mTitleTextView.setText(R.string.app_name);
        toggleBtn.setVisibility(View.VISIBLE);

        UserData myData = ContextUtil.getUserData(MainActivity.this);

        ImageLoader.getInstance().displayImage(myData.profilePhoto, profileImg);
        userNameTxt.setText(myData.name);

//        profileImg


    }

    public void bindViews() {
        drawLayout = (LinearLayout) findViewById(R.id.drawLayout);
        dlDrawer = (DrawerLayout) findViewById(R.id.dl_activity_main_drawer);
        lvNavList = (ListView) findViewById(R.id.lv_activity_main_nav_list);
        userProfileLayout = (FrameLayout) findViewById(R.id.userProfileLayout);
        toggleBtn = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.toggleBtn);
        mTitleTextView = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.titleTxt);
        addBtn = (Button) getSupportActionBar().getCustomView().findViewById(R.id.addBtn);
        searchBtn = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.searchBtn);
        searchDetailBtn = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.searchDetailBtn);


        fragment_mainContainer0 = (LinearLayout) findViewById(R.id.fragment_mainContainer0);
        fragment_mainContainer1 = (LinearLayout) findViewById(R.id.fragment_mainContainer1);
        fragment_mainContainer2 = (LinearLayout) findViewById(R.id.fragment_mainContainer2);
        fragment_mainContainer3 = (LinearLayout) findViewById(R.id.fragment_mainContainer3);
        fragment_mainContainer4 = (LinearLayout) findViewById(R.id.fragment_mainContainer4);
        fragment_mainContainer5 = (LinearLayout) findViewById(R.id.fragment_mainContainer5);
        fragment_mainContainer6 = (LinearLayout) findViewById(R.id.fragment_mainContainer6);


        this.dlactivitymaindrawer = (DrawerLayout) findViewById(R.id.dl_activity_main_drawer);
        this.drawLayout = (LinearLayout) findViewById(R.id.drawLayout);
        this.lvactivitymainnavlist = (ListView) findViewById(R.id.lv_activity_main_nav_list);
        this.userProfileLayout = (FrameLayout) findViewById(R.id.userProfileLayout);
        profileImg = (ImageView) findViewById(R.id.profileImg);
        this.userNameTxt = (TextView) findViewById(R.id.userNameTxt);
        this.myawesometoolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
    }

    @Override
    public void onBackPressed() {

        if (dlDrawer.isDrawerOpen(drawLayout)) {
            dlDrawer.closeDrawer(drawLayout);
        } else {
            backPressCloseHandler.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position,
                                long id) {
            if (position == 0) {

                currentLayout.setVisibility(View.GONE);
                fragment_mainContainer0.setVisibility(View.VISIBLE);
                currentLayout = fragment_mainContainer0;
                addBtn.setVisibility(View.GONE);
                searchBtn.setVisibility(View.GONE);
                searchDetailBtn.setVisibility(View.GONE);

            }
            else if (position == 1) {

                currentLayout.setVisibility(View.GONE);
                fragment_mainContainer1.setVisibility(View.VISIBLE);
                currentLayout = fragment_mainContainer1;
                addBtn.setVisibility(View.GONE);
                searchBtn.setVisibility(View.GONE);
                searchDetailBtn.setVisibility(View.GONE);

            }
            else if (position == 2) {

                ((FindGroupFragment) fragList.get(2)).showLocationActivity();
                currentLayout.setVisibility(View.GONE);
                fragment_mainContainer2.setVisibility(View.VISIBLE);
                currentLayout = fragment_mainContainer2;
                addBtn.setVisibility(View.VISIBLE);
                searchBtn.setVisibility(View.VISIBLE);
                searchDetailBtn.setVisibility(View.VISIBLE);

                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this, SelectGroupTypeActivity.class);
                        startActivity(intent);
                    }
                });

            }
            else if (position == 3) {

                currentLayout.setVisibility(View.GONE);
                fragment_mainContainer3.setVisibility(View.VISIBLE);
                currentLayout = fragment_mainContainer3;
                addBtn.setVisibility(View.GONE);
                searchBtn.setVisibility(View.VISIBLE);
                searchDetailBtn.setVisibility(View.VISIBLE);
            }
            else if (position == 4) {

                currentLayout.setVisibility(View.GONE);
                fragment_mainContainer4.setVisibility(View.VISIBLE);
                currentLayout = fragment_mainContainer4;
                addBtn.setVisibility(View.GONE);
                searchBtn.setVisibility(View.VISIBLE);
                searchDetailBtn.setVisibility(View.VISIBLE);
            }
            else if (position == 5) {

                currentLayout.setVisibility(View.GONE);
                fragment_mainContainer5.setVisibility(View.VISIBLE);
                currentLayout = fragment_mainContainer5;
                addBtn.setVisibility(View.GONE);
                searchBtn.setVisibility(View.VISIBLE);
                searchDetailBtn.setVisibility(View.VISIBLE);
            }
            else if (position == 6) {

                currentLayout.setVisibility(View.GONE);
                fragment_mainContainer6.setVisibility(View.VISIBLE);
                currentLayout = fragment_mainContainer6;
                addBtn.setVisibility(View.GONE);
                searchBtn.setVisibility(View.VISIBLE);
                searchDetailBtn.setVisibility(View.VISIBLE);
            }


            dlDrawer.closeDrawer(drawLayout);
        }
    }
}
