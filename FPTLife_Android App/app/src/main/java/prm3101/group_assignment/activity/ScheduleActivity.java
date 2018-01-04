package prm3101.group_assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.stacktips.view.CalendarListener;
import com.stacktips.view.CustomCalendarView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.SubjectAdapter;
import prm3101.group_assignment.model.Instructor;
import prm3101.group_assignment.model.Schedule;
import prm3101.group_assignment.model.Student;
import prm3101.group_assignment.ulti.APIService;
import prm3101.group_assignment.ulti.APIUtils;
import prm3101.group_assignment.ulti.AppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {

    private final String TAG = "ScheduleActivity";
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;

    private Toolbar mToolbar;
    private WeekView mWeekView;
    private TextView mDate, today;
    private CustomCalendarView calendarView;
    private APIService mService;
    private RecyclerView recyclerView;
    private ArrayList<Schedule> mSchedulesOfDay, mSchedulesOfWeek;
    private AppUtils utils = new AppUtils();
    private MonthLoader.MonthChangeListener mMonthChangeListener;
    private int userRole;
    private List<Student> studentInfo;
    private List<Instructor> teacherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        final AppUtils utils = new AppUtils();
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        mDate = (TextView) findViewById(R.id.dateInfo);
        mWeekView = (WeekView) findViewById(R.id.weekView);
        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);
        today = (TextView) findViewById(R.id.today);
        recyclerView = (RecyclerView) findViewById(R.id.todaySchedule);
        mService = APIUtils.getAPIService();

        //Get Week Schedule
        final Intent intent = getIntent();
        userRole = (int) intent.getExtras().getSerializable("ROLE");
        mSchedulesOfWeek = (ArrayList<Schedule>) intent.getExtras().getSerializable("WEEK_SCHEDULE");
        Log.e(TAG, mSchedulesOfWeek.toString());

        //Implement week view
        mMonthChangeListener = new MonthLoader.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
                for (int i = 0; i < mSchedulesOfWeek.size(); i++) {
                    Calendar startTime = Calendar.getInstance();
                    String date = (String) mSchedulesOfWeek.get(i).getDate().subSequence(0, 10);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        startTime.setTime(dateFormat.parse(date));
                        WeekViewEvent event = new WeekViewEvent();
                        switch (mSchedulesOfWeek.get(i).getId()) {
                            case 1:
                                startTime.set(Calendar.HOUR_OF_DAY, 7);
                                startTime.set(Calendar.MINUTE, 0);
                                startTime.set(Calendar.MONTH, newMonth - 1);
                                startTime.set(Calendar.YEAR, newYear);
                                break;
                            case 2:
                                startTime.set(Calendar.HOUR_OF_DAY, 8);
                                startTime.set(Calendar.MINUTE, 45);
                                startTime.set(Calendar.MONTH, newMonth - 1);
                                startTime.set(Calendar.YEAR, newYear);
                                break;
                            case 3:
                                startTime.set(Calendar.HOUR_OF_DAY, 10);
                                startTime.set(Calendar.MINUTE, 30);
                                startTime.set(Calendar.MONTH, newMonth - 1);
                                startTime.set(Calendar.YEAR, newYear);
                                break;
                            case 4:
                                startTime.set(Calendar.HOUR_OF_DAY, 12);
                                startTime.set(Calendar.MINUTE, 30);
                                startTime.set(Calendar.MONTH, newMonth - 1);
                                startTime.set(Calendar.YEAR, newYear);
                                break;
                            case 5:
                                startTime.set(Calendar.HOUR_OF_DAY, 14);
                                startTime.set(Calendar.MINUTE, 15);
                                startTime.set(Calendar.MONTH, newMonth - 1);
                                startTime.set(Calendar.YEAR, newYear);
                                break;
                            case 6:
                                startTime.set(Calendar.HOUR_OF_DAY, 16);
                                startTime.set(Calendar.MINUTE, 0);
                                startTime.set(Calendar.MONTH, newMonth - 1);
                                startTime.set(Calendar.YEAR, newYear);
                                break;
                        }
                        Calendar endTime = (Calendar) startTime.clone();
                        endTime.add(Calendar.HOUR, 1);
                        endTime.add(Calendar.MINUTE, 30);
                        endTime.set(Calendar.MONTH, newMonth - 1);
                        event.setId(i);
                        event.setName(mSchedulesOfWeek.get(i).getSubCode() + ": "
                                + mSchedulesOfWeek.get(i).getSubName());
                        event.setStartTime(startTime);
                        event.setEndTime(endTime);
                        switch (mSchedulesOfWeek.get(i).getId()) {
                            case 1:
                                event.setColor(getResources().getColor(R.color.colorAccent));
                                break;
                            case 2:
                                event.setColor(getResources().getColor(R.color.colorPrimary));
                                break;
                            case 3:
                                event.setColor(getResources().getColor(R.color.colorPrimaryDark));
                                break;
                            case 4:
                                event.setColor(getResources().getColor(R.color.red));
                                break;
                            case 5:
                                event.setColor(getResources().getColor(R.color.blue));
                                break;
                            case 6:
                                event.setColor(getResources().getColor(R.color.colorPrimary_v2));
                                break;
                        }
                        events.add(event);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                mWeekView.goToDate(utils.getCalendarForSchedule());
                return events;
            }
        };
        mWeekView.goToHour(6);
        mWeekView.setNumberOfVisibleDays(7);
        // Lets change some dimensions to best fit the view.
        mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2,
                getResources().getDisplayMetrics()));
        mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10,
                getResources().getDisplayMetrics()));
        mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10,
                getResources().getDisplayMetrics()));
        setupDateTimeInterpreter(true);
        mWeekView.setMonthChangeListener(mMonthChangeListener);


        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setShowOverflowDate(false);
        calendarView.refreshCalendar(currentCalendar);
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(final Date date) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                final SimpleDateFormat dfull = new SimpleDateFormat("EEEE, d MMMM yyyy");
                if (userRole == 0) {
                    Intent intent = getIntent();
                    studentInfo = (List<Student>) intent.getExtras().getSerializable("USER_INFO");
                    // Day API
                    mService.getStudentScheduleByDay(studentInfo.get(0).getId(), df.format(date)).enqueue(new Callback<List<Schedule>>() {
                        @Override
                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                            if (response.isSuccessful()) {
                                mSchedulesOfDay = (ArrayList<Schedule>) response.body();
//                            Log.e("ScheduleActivity---Day1", mSchedulesOfDay.toString());
                                //Load to view
                                today.setText(dfull.format(date));
                                recyclerView.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                SubjectAdapter adapter = new SubjectAdapter(getApplicationContext(), mSchedulesOfDay);
                                recyclerView.setAdapter(adapter);
                            } else {
                                int statusCode = response.code();
                                // handle request errors depending on status code
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
                            today.setText("No Classes");
                            mSchedulesOfDay.clear();
                            SubjectAdapter adapter = new SubjectAdapter(getApplicationContext(), mSchedulesOfDay);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } else {
                    Intent intent = getIntent();
                    teacherInfo = (List<Instructor>) intent.getExtras().getSerializable("USER_INFO");
                    // Day API
                    mService.getTeacherScheduleByDay(teacherInfo.get(0).getId(), df.format(date)).enqueue(new Callback<List<Schedule>>() {
                        @Override
                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                            if (response.isSuccessful()) {
                                mSchedulesOfDay = (ArrayList<Schedule>) response.body();
//                            Log.e("ScheduleActivity---Day1", mSchedulesOfDay.toString());
                                //Load to view
                                today.setText(dfull.format(date));
                                recyclerView.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                SubjectAdapter adapter = new SubjectAdapter(getApplicationContext(), mSchedulesOfDay);
                                recyclerView.setAdapter(adapter);
                            } else {
                                int statusCode = response.code();
                                // handle request errors depending on status code
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
                            today.setText("No Classes");
                            mSchedulesOfDay.clear();
                            SubjectAdapter adapter = new SubjectAdapter(getApplicationContext(), mSchedulesOfDay);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MMM yyyy");
                mDate.setText(df.format(date));
            }
        });

        //spinner
        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.level_array,
                        android.R.layout.simple_dropdown_item_1line);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (parent.getSelectedItemPosition()) {
                    case 0:
                        mDate.setText(utils.getWeek());
                        mWeekView.setVisibility(View.VISIBLE);
                        calendarView.setVisibility(View.GONE);
                        today.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        break;
                    case 1:
                        mDate.setText(utils.getMonth());
                        mWeekView.setVisibility(View.GONE);
                        calendarView.setVisibility(View.VISIBLE);
                        today.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        //Today
                        if (userRole == 0) {
                            studentInfo = (List<Student>) intent.getExtras().getSerializable("USER_INFO");
                            mService.getStudentScheduleByDay(studentInfo.get(0).getId(), utils.getToday()).
                                    enqueue(new Callback<List<Schedule>>() {
                                        @Override
                                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                                            if (response.isSuccessful()) {
                                                mSchedulesOfDay = (ArrayList<Schedule>) response.body();
                                                //Load to view
                                                today.setText(utils.getDayFull());
                                                recyclerView.setHasFixedSize(true);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                                recyclerView.setLayoutManager(linearLayoutManager);
                                                SubjectAdapter adapter = new SubjectAdapter(getApplicationContext(), mSchedulesOfDay);
                                                recyclerView.setAdapter(adapter);
                                            } else {
                                                int statusCode = response.code();
                                                // handle request errors depending on status code
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
                                            today.setText("No Classes");
                                            mSchedulesOfDay = new ArrayList<>();
                                            SubjectAdapter adapter = new SubjectAdapter(getApplicationContext(), mSchedulesOfDay);
                                            recyclerView.setAdapter(adapter);
                                        }
                                    });
                        } else {
                            teacherInfo = (List<Instructor>) intent.getExtras().getSerializable("USER_INFO");
                            mService.getStudentScheduleByDay(teacherInfo.get(0).getId(), utils.getToday()).
                                    enqueue(new Callback<List<Schedule>>() {
                                        @Override
                                        public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                                            if (response.isSuccessful()) {
                                                mSchedulesOfDay = (ArrayList<Schedule>) response.body();
                                                //Load to view
                                                today.setText(utils.getDayFull());
                                                recyclerView.setHasFixedSize(true);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                                recyclerView.setLayoutManager(linearLayoutManager);
                                                SubjectAdapter adapter = new SubjectAdapter(getApplicationContext(), mSchedulesOfDay);
                                                recyclerView.setAdapter(adapter);
                                            } else {
                                                int statusCode = response.code();
                                                // handle request errors depending on status code
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<Schedule>> call, Throwable t) {
                                            today.setText("No Classes");
                                            mSchedulesOfDay.clear();
                                            SubjectAdapter adapter = new SubjectAdapter(getApplicationContext(), mSchedulesOfDay);
                                            recyclerView.setAdapter(adapter);
                                        }
                                    });
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     *
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat("EEE", Locale.getDefault());
                return weekday;
            }

            @Override
            public String interpretTime(int hour) {
                return String.valueOf(hour);
//                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }

}