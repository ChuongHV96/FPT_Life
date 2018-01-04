package prm3101.group_assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import prm3101.group_assignment.R;
import prm3101.group_assignment.model.Schedule;
import prm3101.group_assignment.ulti.AppUtils;

public class DetailSubjectActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mSubjectName, mRoom, mStartTime, mEndTime, mTeacher;
    private ImageView mBack;
    private final String TAG = "DetailSubjectActivity";
    private AppUtils mUtils = new AppUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);
        Intent intent = getIntent();
        Schedule subjectInfo = (Schedule) intent.getExtras().getSerializable("SUBJECT_INFO");
//        Log.e(TAG, subjectInfo.toString());

        // Load to View
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        mSubjectName = (TextView) findViewById(R.id.subjectName);
        mRoom = (TextView) findViewById(R.id.room);
        mStartTime = (TextView) findViewById(R.id.startTime);
        mEndTime = (TextView) findViewById(R.id.endTime);
        mTeacher = (TextView) findViewById(R.id.teacher);
        mBack = (ImageView) findViewById(R.id.back);
        mSubjectName.setText(subjectInfo.getSubName());
        mRoom.setText(subjectInfo.getClassroomCode());
        mStartTime.setText("From: " + subjectInfo.getStartDate().subSequence(0, 10));
        mEndTime.setText("To: " + subjectInfo.getEndDate().subSequence(0, 10));
        mTeacher.setText(subjectInfo.getUsername());
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
