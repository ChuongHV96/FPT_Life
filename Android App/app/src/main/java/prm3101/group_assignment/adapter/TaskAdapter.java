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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.DetailTaskActivity;
import prm3101.group_assignment.model.Task;

/**
 * Created by ASUS on 25/10/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Task> mData;
    private LayoutInflater mLayoutInflater;

    public TaskAdapter(Context mContext, ArrayList<Task> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_tasks, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Task task = mData.get(position);
        holder.taskTitle.setText(task.getTitle());
        switch(task.getTaskType()){
            case 1:
                holder.taskType.setText(task.getSubCode() + " Quiz");
                break;
            case 2:
                holder.taskType.setText(task.getSubCode() + " Assignment");
                break;
            case 3:
                holder.taskType.setText(task.getSubCode() + " Exam");
                break;
        }


        String start_dt = (String) task.getDueDate().subSequence(0, 10);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Date date = null;
        try {
            date = (Date)formatter.parse(start_dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("dd MMM");
        String finalString = newFormat.format(date);
        holder.dueDate.setText(finalString);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailTaskActivity.class);
                intent.putExtra("TASK_INFO", (Serializable) task);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView taskTitle;
        TextView taskType;
        TextView dueDate;

        public ViewHolder(View itemView) {
            super(itemView);
            taskTitle = (TextView) itemView.findViewById(R.id.taskTitle);
            taskType = (TextView) itemView.findViewById(R.id.taskType);
            dueDate = (TextView) itemView.findViewById(R.id.dueDate);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
