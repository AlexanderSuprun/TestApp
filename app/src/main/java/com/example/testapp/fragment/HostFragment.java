package com.example.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.utils.OnFragmentMessageSendListener;


/**
 * HostFragment intended to contain other fragments.
 */
public class HostFragment extends Fragment implements OnFragmentMessageSendListener {

    private static final String TAG_FIRST_FRAGMENT = "com.example.testapp.FIRST_FRAGMENT";
    private static final String TAG_SECOND_FRAGMENT = "com.example.testapp.SECOND_FRAGMENT";

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
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fl_fragment_host_container, new FirstFragment(), TAG_FIRST_FRAGMENT)
                    .commit();
        } else {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fl_fragment_host_container,
                            (FirstFragment) getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT))
                    .commit();
        }
    }

    public void replaceWithFirstFragment() {
        if (getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT) == null) {
            replaceFragment(new FirstFragment(), TAG_FIRST_FRAGMENT);
        } else {
            replaceFragment((FirstFragment) getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT),
                    TAG_FIRST_FRAGMENT);
        }
    }

    public void replaceWithSecondFragment() {
        if (getChildFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT) == null) {
            replaceFragment(new SecondFragment(), TAG_SECOND_FRAGMENT);
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
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment_host_container, fragment, fragmentTag)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }
}