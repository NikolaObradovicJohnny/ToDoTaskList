package eu.execom.todolistgrouptwo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

import eu.execom.todolistgrouptwo.R;
import eu.execom.todolistgrouptwo.adapter.TaskAdapter;
import eu.execom.todolistgrouptwo.api.RestApi;
import eu.execom.todolistgrouptwo.database.wrapper.TaskDAOWrapper;
import eu.execom.todolistgrouptwo.database.wrapper.UserDAOWrapper;
import eu.execom.todolistgrouptwo.model.Task;
import eu.execom.todolistgrouptwo.model.User;
import eu.execom.todolistgrouptwo.preference.UserPreferences_;

/**
 * Home {@link AppCompatActivity Activity} for navigation and listing all tasks.
 */
@EActivity(R.layout.activity_home)
@OptionsMenu(R.menu.drawer_menu)
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Used for logging purposes.
     */
    private static final String TAG = HomeActivity.class.getSimpleName();

    /**
     * Used for identifying results from different activities.
     */
    protected static final int ADD_TASK_REQUEST_CODE = 42;
    protected static final int LOGIN_REQUEST_CODE = 420; // BLAZE IT

    /**
     * Tasks are kept in this list during a user session.
     */
    private List<Task> tasks;

    private User user;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    /**
     * {@link FloatingActionButton FloatingActionButton} for starting the
     * {@link AddTaskActivity AddTaskActivity}.
     */
    @ViewById
    FloatingActionButton addTask;

    /**
     * {@link ListView ListView} for displaying the tasks.
     */
    @ViewById
    ListView listView;

    /**
     * {@link TaskAdapter Adapter} for providing data to the {@link ListView listView}.
     */
    @Bean
    TaskAdapter adapter;

    @Bean
    UserDAOWrapper userDAOWrapper;

    @Bean
    TaskDAOWrapper taskDAOWrapper;

    @Pref
    UserPreferences_ userPreferences;

    @RestService
    RestApi restApi;


    @AfterViews
    @Background
    void checkUser() {
//        if (!userPreferences.userId().exists()) {
//            LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
//            return;
//        }
        if (!userPreferences.accessToken().exists()) {
            LoginActivity_.intent(this).startForResult(LOGIN_REQUEST_CODE);
            return;
        }

//        user = userDAOWrapper.findById(userPreferences.userId().get());
//        tasks = taskDAOWrapper.findByUser(user);
        tasks = restApi.getAllTasks();

        initData();
    }

    /**
     * Loads tasks from the {@link android.content.SharedPreferences SharedPreferences}
     * and sets the adapter.
     */
    @UiThread
    void initData() {
        setSupportActionBar(toolbar);

        listView.setAdapter(adapter);
        adapter.setTasks(tasks);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView =(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "ON CLICK TOOLBAR NAVIGATION",Toast.LENGTH_LONG).show();

            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    // button show
                    addTask.show();
                } else {
                    // button hide
                    addTask.hide();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomeActivity.this,"Klik na task",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), TaskActivity_.class);
//                startActivity(intent);
//                openTaskActivity();
            }
        });

    }

    /**
     * Called when the {@link FloatingActionButton FloatingActionButton} is clicked.
     */
    @Click
    void addTask() {
        Log.i(TAG, "Add task clicked!");
        AddTaskActivity_.intent(this).startForResult(ADD_TASK_REQUEST_CODE);
    }

    void openTaskActivity() {
        Log.i(TAG, "Open task activity!");
        TaskActivity_.intent(this).startForResult(0);
    }

    /**
     * Called when the {@link AddTaskActivity AddTaskActivity} finishes.
     *
     * @param resultCode Indicates whether the activity was successful.
     * @param task         The new task.
     */
    @OnActivityResult(ADD_TASK_REQUEST_CODE)
    @Background
    void onResult(int resultCode, @OnActivityResult.Extra String task) {
        if (resultCode == RESULT_OK) {
//            Toast.makeText(this, task, Toast.LENGTH_SHORT).show();
            final Gson gson = new Gson();
            final Task newTask = gson.fromJson(task, Task.class);


            try {
                final Task newNewTask = taskDAOWrapper.create(newTask);
                onTaskCreated(newNewTask);
            } catch (Exception e){
                Log.e(TAG,e.getMessage(),e);
            }

//            taskDAOWrapper.create(newTask);
            restApi.createTask(newTask);
        }
    }

    @UiThread
    void onTaskCreated(Task task) {
        tasks.add(task);
        adapter.addTask(task);
    }

    @OnActivityResult(LOGIN_REQUEST_CODE)
    void onLogin(int resultCode, @OnActivityResult.Extra("token") String token) {
        if (resultCode == RESULT_OK) {
//            userPreferences.userId().put(id);
            userPreferences.accessToken().put(token);
            checkUser();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
//                Intent intentHome = new Intent(this,RestaurantsActivity.class);
//                startActivity(intentHome);
                Toast.makeText(HomeActivity.this,"About",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_refresh:
                Toast.makeText(HomeActivity.this,"Refresh",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_settings:
//                Intent intent = new Intent(this, SettingsActivity.class);

//                startActivity(intent);
                Toast.makeText(HomeActivity.this,"Settings",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_logout:
//                Intent intent = new Intent(this, SettingsActivity.class);
//
//                restApi.logout();
//                startActivity(intent);
                Toast.makeText(HomeActivity.this,"Logout",Toast.LENGTH_LONG).show();
//                LoginActivity_.intent(this).start();
                break;
        }

        DrawerLayout dl = (DrawerLayout) findViewById(R.id.activity_home);
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        }
        return false;
    }

}
