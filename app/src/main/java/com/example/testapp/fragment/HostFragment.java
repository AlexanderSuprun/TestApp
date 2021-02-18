package com.example.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.testapp.R;
import com.example.testapp.utils.OnMessageSendClickListener;

/**
 * HostFragment intended to contain other fragments.
 */
public class HostFragment extends Fragment implements OnMessageSendClickListener {

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
        replaceWithFirstFragment();
    }

    public void replaceWithFirstFragment() {
        if (getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT) == null) {
            firstFragment = new FirstFragment();
            replaceFragment(firstFragment, TAG_FIRST_FRAGMENT);
        } else {
            replaceFragment((FirstFragment) getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT),
                    TAG_FIRST_FRAGMENT);
        }
    }

    public void replaceWithSecondFragment() {
        if (getChildFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT) == null) {
            secondFragment = new SecondFragment();
            replaceFragment(secondFragment, TAG_SECOND_FRAGMENT);
        } else {
            replaceFragment((SecondFragment) getChildFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT),
                    TAG_SECOND_FRAGMENT);
        }
    }

    @Override
    public void sendMessageToFirstFragment(String message) {
        replaceFragment(FirstFragment.newInstance(message), TAG_FIRST_FRAGMENT);
    }

    @Override
    public void sendMessageToSecondFragment(String message) {
        replaceFragment(SecondFragment.newInstance(message), TAG_SECOND_FRAGMENT);
    }

    private void replaceFragment(Fragment fragment, String fragmentTag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment_host_container, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();
    }
}