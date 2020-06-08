package com.example.taskfragment.data;

import com.example.taskfragment.R;

import java.util.ArrayList;

public class TaskRepository {

    private static TaskRepository sTodoRepository;

    public final ArrayList<Task> mTasks = new ArrayList<>();

    public static TaskRepository getInstance() {
        if (sTodoRepository == null) {
            sTodoRepository = new TaskRepository();
        }
        return sTodoRepository;
    }

    private TaskRepository(){
        initTestData();
    }

    private void initTestData() {

        for (int i=0; i < 25; i++){
            Task task = new Task(i, R.drawable.ic_android_foreground,"TODO title " + i,"TODO description " + i,
                    "To be set!");
            mTasks.add(task);
        }
    }

    public Task getFirstTask() {
        return mTasks.get(0);
    }

    public Task getNextTask(Task task) {

        int index = mTasks.indexOf(task);
        if (index == -1 || index == mTasks.size()) {
            return task;
        }
        if (index < mTasks.size() - 1) {
            return mTasks.get(index + 1);
        } else {
            return mTasks.get(mTasks.size() - 1);
        }

    }

    public Task getPrevTask(Task task) {

        int index = mTasks.indexOf(task);
        if ((index == -1) || (index == 0)) {
            return task;
        }
        if (index < mTasks.size()) {
            return mTasks.get(index - 1);
        } else {
            return mTasks.get(0);
        }

    }

    public boolean isFirst(Task task) {
        return mTasks.indexOf(task) == 0;
    }

    public boolean isLast(Task task) {
        return mTasks.indexOf(task) >= mTasks.size() - 1;
    }

    public Task delete(Task task) {

        /* delete task and return the next task */
        Task nextTask = new Task(0,R.drawable.ic_android_foreground, "Task title", "Task description", "Task status");
        if ( mTasks.size() == 1 ) {
            mTasks.remove(task);
            mTasks.add(nextTask);
        } else if ( isLast(task) ) {
            nextTask = getFirstTask();
            mTasks.remove(task);
        } else {
            nextTask = getNextTask(task);
            mTasks.remove(task);
        }

        return nextTask;
    }

    public void addTask(Task task) {
        mTasks.add(task);
    }
}
