package com.example.projekt1_admin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private  TaskAdapter mTaskAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Fire_Task> tasks,List<String>keys){
        mContext=context;
        mTaskAdapter=new TaskAdapter(tasks,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mTaskAdapter);
    }

    class TaskItemView extends RecyclerView.ViewHolder{
        private TextView mGroup;
        private TextView mTask;
        private Switch mStatus;
        private ImageView mVoters;
        private String key;

        public TaskItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.task_list_item,parent,false));
            mGroup=(TextView)itemView.findViewById(R.id.group_title);
            mTask=(TextView)itemView.findViewById(R.id.task_title);
            mStatus=(Switch)itemView.findViewById(R.id.status_switch);
            mVoters=(ImageView)itemView.findViewById(R.id.voters);
        }
        public void bind (Fire_Task task, String key){
            mGroup.setText(task.getGROUP());
            mTask.setText(task.getTASK());
            String stat=task.getSTATUS();
            if(stat.equals("activ")==true)
            {
                mStatus.setChecked(true);
            }
            else
            {
                mStatus.setChecked(false);
            }
            mVoters.setImageResource(R.drawable.ic_people);
            this.key=key;
        }
    }
    class TaskAdapter extends RecyclerView.Adapter<TaskItemView>{
        private List<Fire_Task> mTaskList;
        private List<String> mKeys;

        public TaskAdapter(List<Fire_Task> mTaskList, List<String> mKeys) {
            this.mTaskList = mTaskList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public TaskItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TaskItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskItemView holder, int position) {
            holder.bind(mTaskList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mTaskList.size();
        }
    }
}
