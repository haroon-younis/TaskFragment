package com.example.taskfragment.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskfragment.R;
import com.example.taskfragment.data.Task;

public class TaskEditFragment extends Fragment {

    private static final String LOG_TAG = TaskEditFragment.class.getSimpleName();

    private Task mTask = new Task();
    private MainViewModel mViewModel;

    private EditText mEditTextTitle, mEditTextDescription;

    public TaskEditFragment() {
        // Required empty public constructor
    }

    public static TaskEditFragment newInstance() {
        return new TaskEditFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        assert getActivity() != null;
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mTask = mViewModel.getTask();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_edit, container, false);
        updateUI(view);
        return view;
    }

    private final View.OnClickListener mEditTaskListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(LOG_TAG, "onClick: Button clicked");

            mTask.setTitle(mEditTextTitle.getText().toString());
            mTask.setDescription(mEditTextDescription.getText().toString());

            mViewModel.setTask(mTask);

            // takes us back to main page
            TaskFragment taskFragment = TaskFragment.newInstance();
            assert getFragmentManager() != null;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, taskFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

    private void updateUI(View view) {
        mEditTextTitle = view.findViewById(R.id.editTextTitle);
        mEditTextTitle.setText(mTask.getTitle());

        mEditTextDescription = view.findViewById(R.id.editTextDescription);
        mEditTextDescription.setText(mTask.getDescription());

        Button mSubmitButton = view.findViewById(R.id.buttonEditTask);
        mSubmitButton.setOnClickListener(mEditTaskListener);
    }
}