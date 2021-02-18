package com.example.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testapp.R;

/**
 * HostFragment intended to contain other fragments.
 */
public class HostFragment extends Fragment {

    private static final String TAG_FIRST_FRAGMENT = "com.example.testapp.FirstFragment";
    private static final String TAG_SECOND_FRAGMENT = "com.example.testapp.SecondFragment";

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;

    public HostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_host, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT) == null) {
            firstFragment = new FirstFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_fragment_host_container, firstFragment, TAG_FIRST_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        } else {
            firstFragment = (FirstFragment) getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT);
        }
    }

    public void replaceWithFirstFragment() {
        if (getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT) == null) {
            firstFragment = new FirstFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_fragment_host_container, firstFragment, TAG_FIRST_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        } else {
            firstFragment = (FirstFragment) getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT);
        }
    }

    public void replaceWithSecondFragment() {
        if (getChildFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT) == null) {
            secondFragment = new SecondFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_fragment_host_container, secondFragment, TAG_SECOND_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        } else {
            secondFragment = (SecondFragment) getChildFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT);
        }
    }
}