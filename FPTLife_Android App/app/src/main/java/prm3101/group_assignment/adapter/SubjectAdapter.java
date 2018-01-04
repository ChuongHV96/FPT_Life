package prm3101.group_assignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.DetailSubjectActivity;
import prm3101.group_assignment.model.Schedule;

/**
 * Created by ASUS on 25/10/2017.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Schedule> mData;
    private LayoutInflater mLayoutInflater;

    public SubjectAdapter(Context mContext, ArrayList<Schedule> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_subject_in_day, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Schedule course = mData.get(position);
        //Pending set time from slot -- Load from API or Write function
        switch(course.getId()){
            case 1:
                holder.startTime.setText("07:00");
                holder.endTime.setText("08:30");
//                holder.itemView.setBackgroundColor(Color.LTGRAY);
                break;
            case 2:
                holder.startTime.setText("08:45");
                holder.endTime.setText("10:15");
//                holder.itemView.setBackgroundColor(Color.LTGRAY);
                break;
            case 3:
                holder.startTime.setText("10:30");
                holder.endTime.setText("12:00");
                break;
            case 4:
                holder.startTime.setText("12:30");
                holder.endTime.setText("14:00");
                break;
            case 5:
                holder.startTime.setText("14:15");
                holder.endTime.setText("15:45");
                break;
            case 6:
                holder.startTime.setText("16:00");
                holder.endTime.setText("17:30");
                break;
        }


//        Calendar calendar = Calendar.getInstance();
//        Date today = calendar.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
//        String currentTime = dateFormat.format(today);
//        Log.e("Subject", currentTime);



        holder.subjectName.setText(course.getSubName());
        holder.room.setText(course.getClassroomCode());
        holder.teacher.setText(course.getUsername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSubjectActivity.class);
                intent.putExtra("SUBJECT_INFO", (Serializable) course);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView startTime;
        TextView endTime;
        TextView subjectName;
        TextView room;
        TextView teacher;

        public ViewHolder(View itemView) {
            super(itemView);
            startTime = (TextView) itemView.findViewById(R.id.startTime);
            endTime = (TextView) itemView.findViewById(R.id.endTime);
            subjectName = (TextView) itemView.findViewById(R.id.subjectName);
            room = (TextView) itemView.findViewById(R.id.room);
            teacher = (TextView) itemView.findViewById(R.id.teacher);
        }

        @Override
        public void onClick(View v) {
            // To do
        }
    }
}
