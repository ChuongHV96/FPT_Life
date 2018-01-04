package prm3101.group_assignment.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import prm3101.group_assignment.R;
import prm3101.group_assignment.model.APIMessage;
import prm3101.group_assignment.model.Subject;
import prm3101.group_assignment.ulti.APIService;
import prm3101.group_assignment.ulti.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTaskActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mDate, mSubject, mTaskType, mSave, mTextHide;
    private EditText mTitle, mDetail;
    private ImageView arrowSubject;
    private APIService mService;
    private List<Subject> mSubjectList;


    private int year, month, day, userId, subjectId, taskType = 0;
    static final int DATE_PICKER_ID = 1111;
    private String subjectCode, dueDate, title, detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mToolbar = (Toolbar) findViewById(R.id.tab);
        mDate = (TextView) findViewById(R.id.date);
        arrowSubject = (ImageView) findViewById(R.id.arrowDown_v1);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get id
        mSubject = (TextView) findViewById(R.id.subject);
        mTaskType = (TextView) findViewById(R.id.taskType);
        mSave = (TextView) findViewById(R.id.toolbar_save);
        mTitle = (EditText) findViewById(R.id.input_title);
        mDetail = (EditText) findViewById(R.id.input_detail);
        mTextHide = (TextView) findViewById(R.id.textHide);
        Intent intent = getIntent();
        userId = (int) intent.getExtras().getSerializable("USER_ID");
        mService = APIUtils.getAPIService();
        mService.getAllSubjectOfStudent(userId).enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                mSubjectList = response.body();
                mSubject.setText(mSubjectList.get(0).getSubCode());
                subjectId = mSubjectList.get(0).getId();
                arrowSubject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddTaskActivity.this);
                        builder.setTitle("Select Subject");
                        String[] subjectList = new String[mSubjectList.size()];
                        for (int i = 0; i < mSubjectList.size(); i++) {
//                            String subject = mSubjectList.get(i).getSubCode() + ": " + mSubjectList.get(i).getSubName();
                            String subject = mSubjectList.get(i).getSubCode();
                            subjectList[i] = subject;
                        }
                        builder.setItems(subjectList, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < mSubjectList.size(); i++) {
                                    if (which == i) {
                                        subjectId = mSubjectList.get(i).getId();
                                        mSubject.setText(mSubjectList.get(i).getSubCode());
                                    }
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {

            }
        });

        // Choose due date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        mDate.setText("Due Date\n" + (new StringBuilder()
                .append(day).append("-").append(month + 1).append("-")
                .append(year).append(" ")));
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.type_array,
                        android.R.layout.simple_dropdown_item_1line);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getSelectedItemPosition()) {
                    case 0:
                        mTaskType.setText("Quiz");
                        taskType = 0;
                        break;
                    case 1:
                        mTaskType.setText("Assignment");
                        taskType = 1;
                        break;
                    case 2:
                        mTaskType.setText("Exam");
                        taskType = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //data for push
//        dueDate = mTextHide.getText().toString();
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                title = mTitle.getText().toString();
            }
        });
        mDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                detail = mDetail.getText().toString();
            }
        });
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> taskInfo = new HashMap<String, String>();
                taskInfo.put("studentId", String.valueOf(userId));
                taskInfo.put("subId", String.valueOf(subjectId));
                taskInfo.put("taskType", String.valueOf(taskType));
                taskInfo.put("dueDate", dueDate);
                taskInfo.put("title", title);
                taskInfo.put("detail", detail);
                mService = APIUtils.getAPIService();
                mService.addTask(taskInfo).enqueue(new Callback<List<APIMessage>>() {
                    @Override
                    public void onResponse(Call<List<APIMessage>> call, Response<List<APIMessage>> response) {
                        Log.e("Add", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<APIMessage>> call, Throwable t) {

                    }
                });
                final Dialog dialog = new Dialog(AddTaskActivity.this); // Context, this, etc.
                dialog.setContentView(R.layout.dialog_fail);
                dialog.findViewById(R.id.dialog_info_2).setVisibility(View.GONE);
                TextView a = (TextView) dialog.findViewById(R.id.dialog_info);
                a.setText("Add task success");
                dialog.setTitle("Add task success");
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
                Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                        finish();
                    }
                });
                Log.e("Add", userId + " - " + subjectId + " - " +
                        taskType + " - " + dueDate + " - " + title + " - " + detail);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            // Show selected date
            mDate.setText("Due Date\n" + (new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year)
                    .append(" ")));
            dueDate = String.valueOf((new StringBuilder().append(year)
                    .append("-").append(month + 1).append("-").append(day)));

//            mTextHide.setText(new StringBuilder().append(year)
//                    .append("-").append(month + 1).append("-").append(day)
//                    .append(" "));

        }
    };

}
