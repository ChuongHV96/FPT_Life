package prm3101.group_assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import prm3101.group_assignment.R;
import prm3101.group_assignment.model.Task;

public class DetailTaskActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Task taskInfo;
    private ImageView mBack;
    private TextView mTitle, mTaskType, mTab, mDueDate, mDetail, mSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        Intent intent = getIntent();
        taskInfo = (Task) intent.getExtras().getSerializable("TASK_INFO");
        mTitle = (TextView) findViewById(R.id.subjectName);
        mTitle.setText(taskInfo.getTitle());
        mTaskType = (TextView) findViewById(R.id.subjectTime);
        switch(taskInfo.getTaskType()){
            case 1:
                mTaskType.setText(taskInfo.getSubCode() + " Quiz");
                break;
            case 2:
                mTaskType.setText(taskInfo.getSubCode() + " Assignment");
                break;
            case 3:
                mTaskType.setText(taskInfo.getSubCode() + " Exam");
                break;
        }
        mTab = (TextView) findViewById(R.id.title);
        mTab.setText("Detail");
        mDueDate = (TextView) findViewById(R.id.dueDate);

        //Set due date
        String start_dt = (String) taskInfo.getDueDate().subSequence(0, 10);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Date date = null;
        try {
            date = (Date)formatter.parse(start_dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("EEEE, dd MMMM");
        String finalString = newFormat.format(date);
        mDueDate.setText("Due: " + finalString);

        mDetail = (TextView) findViewById(R.id.detail);
        mDetail.setText(taskInfo.getDetail());
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSubject = (TextView) findViewById(R.id.className);
        mSubject.setText(taskInfo.getSubCode() + ": " + taskInfo.getSubName());
    }
}
