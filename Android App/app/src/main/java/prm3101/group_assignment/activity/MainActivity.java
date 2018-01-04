package prm3101.group_assignment.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import prm3101.group_assignment.R;
import prm3101.group_assignment.fragment.BasicFragment;
import prm3101.group_assignment.fragment.NewsFragment;
import prm3101.group_assignment.fragment.ProfileFragment;
import prm3101.group_assignment.fragment.TasksFragment;
import prm3101.group_assignment.model.Instructor;
import prm3101.group_assignment.model.Schedule;
import prm3101.group_assignment.model.Student;
import prm3101.group_assignment.model.Task;
import prm3101.group_assignment.ulti.APIService;
import prm3101.group_assignment.ulti.APIUtils;
import prm3101.group_assignment.ulti.AppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private APIService mService;
    private AppUtils utils = new AppUtils();
    private int userRole, size;
    private List<Schedule> mSchedules, mSchedulesToday;
    private List<Student> studentInfo;
    private List<Instructor> teacherInfo;
    private List<Task> mTaskList;

    private NotificationCompat.Builder notBuilder;
    private static final int MY_NOTIFICATION_ID = 12345;
    private static final int MY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        mToolbar.setTitle("FPT Life");
        setSupportActionBar(mToolbar);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new NewsFragment()).commit();

        //Call Api
        mService = APIUtils.getAPIService();

        //Get USER_INFO
        Intent intent = getIntent();
        userRole = (int) intent.getExtras().getSerializable("ROLE");
        if (userRole == 0) {
            studentInfo = (List<Student>) intent.getExtras().getSerializable("USER_INFO");
            //Call API Today
            mService.getStudentScheduleByDay(studentInfo.get(0).getId(), utils.getToday())
                    .enqueue(new Callback<List<Schedule>>() {
                        @Override
                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                            if (response.isSuccessful()) {
                                mSchedules = response.body();
//                        Log.e("MainActivity - Student", mSchedules.toString());
                            } else {
                                int statusCode = response.code();
                                // handle request errors depending on status code
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
//                    Log.e("MainActivity - Student", t.toString());
                            mSchedules = new ArrayList<>();
                        }
                    });
            //Call API Tomorrow
            mService.getStudentScheduleByDay(studentInfo.get(0).getId(), utils.getTommorrow_v2())
                    .enqueue(new Callback<List<Schedule>>() {
                        @Override
                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                            if (response.isSuccessful()) {
                                size = response.body().size();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
                            size = 0;
                        }
                    });
        } else {
            teacherInfo = (List<Instructor>) intent.getExtras().getSerializable("USER_INFO");
            mService.getTeacherScheduleByDay(teacherInfo.get(0).getId(), utils.getToday())
                    .enqueue(new Callback<List<Schedule>>() {
                        @Override
                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                            if (response.isSuccessful()) {
                                mSchedules = response.body();
//                        Log.e("MainActivity - Teacher", mSchedules.toString());
                            } else {
                                int statusCode = response.code();
                                // handle request errors depending on status code
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
//                    Log.e("MainActivity - Teacher", t.toString());
                            mSchedules = new ArrayList<>();
                        }
                    });
            //Call API Tomorrow
            mService.getTeacherScheduleByDay(teacherInfo.get(0).getId(), utils.getTommorrow_v2())
                    .enqueue(new Callback<List<Schedule>>() {
                        @Override
                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                            if (response.isSuccessful()) {
                                size = response.body().size();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
                            size = 0;
                        }
                    });
        }
        setNavigationViewListner();

        //Push Notification
        this.notBuilder = new NotificationCompat.Builder(this);
        this.notBuilder.setSmallIcon(R.drawable.ic_white);
        this.notBuilder.setTicker("This is a ticker")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        Log.e(TAG, String.valueOf(Calendar.getInstance().getTimeInMillis()));
        this.notBuilder.setWhen(Calendar.getInstance().getTimeInMillis() + 300000);
        this.notBuilder.setContentTitle("Next class in 15 minutes");
        this.notBuilder.setContentText("Room: 201");
        // To-do -- Move to fragment when click notification
//        Intent intent2 = new Intent(this, ScheduleActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, MY_REQUEST_CODE,
//                intent2, PendingIntent.FLAG_UPDATE_CURRENT);
//        this.notBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationService =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = notBuilder.build();
        notificationService.notify(MY_NOTIFICATION_ID, notification);
        this.notBuilder.setAutoCancel(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (item.getItemId()) {
            case R.id.New: {
                transaction.replace(R.id.content, new NewsFragment()).commit();
                mToolbar.setTitle("FPT Life");
                break;
            }
            case R.id.Dashboard: {
                if (userRole == 0) {
                    mService.getStudentScheduleByDay(studentInfo.get(0).getId(), utils.getToday())
                            .enqueue(new Callback<List<Schedule>>() {
                                @Override
                                public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                                    mSchedulesToday = response.body();
                                    BasicFragment basicFragment = BasicFragment.newInstance(mSchedulesToday, size,
                                            studentInfo.get(0).getId());
                                    transaction.replace(R.id.content, basicFragment).commit();
                                }

                                @Override
                                public void onFailure(Call<List<Schedule>> call, Throwable t) {
                                    mSchedulesToday = new ArrayList<>();
                                }
                            });
                } else {
                    mService.getTeacherScheduleByDay(teacherInfo.get(0).getId(), utils.getToday())
                            .enqueue(new Callback<List<Schedule>>() {
                                @Override
                                public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                                    mSchedulesToday = response.body();
                                    BasicFragment basicFragment = BasicFragment.newInstance(mSchedulesToday, size,
                                            teacherInfo.get(0).getId());
                                    transaction.replace(R.id.content, basicFragment).commit();
                                }

                                @Override
                                public void onFailure(Call<List<Schedule>> call, Throwable t) {
                                    mSchedulesToday = new ArrayList<>();
                                }
                            });
                }
                mToolbar.setTitle("Dashboard");
                break;
            }
            case R.id.Schedule: {
                final Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                if (userRole == 0) {
                    //Call week API
                    mService.getStudentScheduleByWeek(studentInfo.get(0).getId(), utils.getStartDayOfWeek(), utils.getEndDayOfWeek())
                            .enqueue(new Callback<List<Schedule>>() {
                                @Override
                                public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                                    mSchedules = response.body();
                                    intent.putExtra("ROLE", (Serializable) 0);
                                    intent.putExtra("WEEK_SCHEDULE", (Serializable) mSchedules);
                                    intent.putExtra("USER_INFO", (Serializable) studentInfo);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<List<Schedule>> call, Throwable t) {

                                }
                            });
                } else {
                    //Call week API
                    mService.getTeacherScheduleByWeek(teacherInfo.get(0).getId(), utils.getStartDayOfWeek(), utils.getEndDayOfWeek())
                            .enqueue(new Callback<List<Schedule>>() {
                                @Override
                                public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                                    mSchedules = response.body();
                                    intent.putExtra("ROLE", (Serializable) 1);
                                    intent.putExtra("WEEK_SCHEDULE", (Serializable) mSchedules);
                                    intent.putExtra("USER_INFO", (Serializable) teacherInfo);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<List<Schedule>> call, Throwable t) {

                                }
                            });
                }
                mToolbar.setTitle("Schedule");
                break;
            }
            case R.id.Tasks: {
                if (userRole == 0) {
                    mService.getAllTaskOfStudent(studentInfo.get(0).getId())
                            .enqueue(new Callback<List<Task>>() {
                                @Override
                                public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                                    mTaskList = response.body();
                                    TasksFragment tasksFragment = TasksFragment.newInstance((ArrayList<Task>) mTaskList, studentInfo.get(0).getId());
                                    transaction.replace(R.id.content, tasksFragment).commit();
                                }

                                @Override
                                public void onFailure(Call<List<Task>> call, Throwable t) {
                                    mTaskList = new ArrayList<>();
                                }
                            });
                } else {
                    //To do for Instructor
                }
                mToolbar.setTitle("Tasks | 2017 Fall");
                break;
            }

            case R.id.Me: {
                if (userRole == 0) {
                    List<Instructor> tmp = new ArrayList<>();
                    ProfileFragment profileFragment = ProfileFragment.newInstance(studentInfo, tmp);
                    transaction.replace(R.id.content, profileFragment).commit();
                } else {
                    List<Student> tmp = new ArrayList<>();
                    ProfileFragment profileFragment = ProfileFragment.newInstance(tmp, teacherInfo);
                    transaction.replace(R.id.content, profileFragment).commit();
                }
                mToolbar.setTitle("Me");
                break;
            }
            default:
                break;
        }

        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

}
