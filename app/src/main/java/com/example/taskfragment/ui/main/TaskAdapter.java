package com.example.taskfragment.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskfragment.R;
import com.example.taskfragment.data.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<Task> mTasks;

    private OnItemClickedListener mListener;

    public TaskAdapter(ArrayList<Task> tasks) {
        mTasks = tasks;
    }

    public void setOnTaskClickListener(OnItemClickedListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mTaskImage, mTaskDelete;
        public TextView mTitle, mDescription;

        public ViewHolder(View itemView, final OnItemClickedListener listener) {
            super(itemView);

            mTaskImage = itemView.findViewById(R.id.taskImage);
            mTitle = itemView.findViewById(R.id.textViewTitle);
            mDescription = itemView.findViewById(R.id.textViewDescription);
            mTaskDelete = itemView.findViewById(R.id.deleteImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){

                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClicked(position);
                        }

                    }
                }
            });

            mTaskDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){

                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClicked(position);
                        }

                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_task, parent, false);
        ViewHolder holder = new ViewHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task currentTask = mTasks.get(position);

        holder.mTaskImage.setImageResource(currentTask.getImageResource());
        holder.mTitle.setText(currentTask.getTitle());
        holder.mDescription.setText(currentTask.getDescription());
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public interface OnItemClickedListener {
        void onItemClicked(int position);
        void onDeleteClicked(int position);
    }
}
