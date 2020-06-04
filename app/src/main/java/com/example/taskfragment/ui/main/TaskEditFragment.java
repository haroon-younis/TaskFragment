package com.example.taskfragment.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskfragment.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskEditFragment extends Fragment {

    private static final String LOG_TAG = TaskEditFragment.class.getSimpleName();

    public TaskEditFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TaskEditFragment newInstance() {
        return new TaskEditFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_edit, container, false);
    }
}
