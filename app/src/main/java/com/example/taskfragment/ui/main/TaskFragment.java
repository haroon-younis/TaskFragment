package com.example.taskfragment.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskfragment.R;
import com.example.taskfragment.data.Task;
import com.example.taskfragment.data.TaskRepository;

public class TaskFragment extends Fragment {

    private static final String LOG_TAG = TaskFragment.class.getSimpleName();

    private Task mTask = new Task();
    private final TaskRepository sTaskRepository = TaskRepository.getInstance();
    private MainViewModel mViewModel;

    private RecyclerView mRecyclerView;

    private final FragmentActivity activity = getActivity();

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        Log.d( LOG_TAG, "onCreateView");

        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        if (mViewModel.getTask() == null){
            mTask = sTaskRepository.getFirstTask();
        } else {
            mTask = mViewModel.getTask();
        }

        final View view = inflater.inflate(R.layout.fragment_task, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        TaskAdapter mAdapter = new TaskAdapter(sTaskRepository.mTasks);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnTaskClickListener(new TaskAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                mTask = sTaskRepository.mTasks.get(position);
                mViewModel.setTask(mTask);

                TaskDetailFragment taskDetailFragment = TaskDetailFragment.newInstance();
                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, taskDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void onDeleteClicked(int position) {
                mTask = sTaskRepository.mTasks.get(position);
                mTask = sTaskRepository.delete(mTask);
                doSubmit();
            }
        });
    }

    private void doSubmit() {

        Log.d(LOG_TAG, "doSubmit() returning Status: " + mTask.getStatus() );

        mViewModel.setTask(mTask);

        TaskFragment taskFragment = TaskFragment.newInstance();
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, taskFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        Log.d( LOG_TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of((getActivity())).get(MainViewModel.class);
    }

    public void addTodo(){
        TaskAddFragment taskAddFragment = TaskAddFragment.newInstance();
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, taskAddFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // for menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.addTodo){
            addTodo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
