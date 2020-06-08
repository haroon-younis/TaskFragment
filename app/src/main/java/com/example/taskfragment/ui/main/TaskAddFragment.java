package com.example.taskfragment.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.taskfragment.R;
import com.example.taskfragment.data.Task;
import com.example.taskfragment.data.TaskRepository;

public class TaskAddFragment extends Fragment {

    private static final String LOG_TAG = TaskAddFragment.class.getSimpleName();

    private Task mTask = new Task();
    private final TaskRepository sTaskRepository = TaskRepository.getInstance();
    private MainViewModel mViewModel;

    public static TaskAddFragment newInstance() {
        return new TaskAddFragment();
    }

    public TaskAddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_add_task, container, false);
        Log.d( LOG_TAG, "onCreateView");

        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        final View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        updateUI(view);
        return view;

    }

    private EditText mEditTextTitle, mEditTextDescription;
    private ImageView mTodoImage;

    private void updateUI(View view) {

        mEditTextTitle = view.findViewById(R.id.editTextTitle);
        mEditTextDescription = view.findViewById(R.id.editTextDescription);
        mTodoImage = view.findViewById(R.id.todoImage);
        mTodoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        Button mButtonAddTask = view.findViewById(R.id.buttonAddTask);
        mButtonAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mTask.setTitle(mEditTextTitle.getText().toString());
                mTask.setDescription(mEditTextDescription.getText().toString());


                sTaskRepository.addTask(mTask);
                mViewModel.setTask(mTask);

                TaskFragment taskFragment = TaskFragment.newInstance();
                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, taskFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


}
