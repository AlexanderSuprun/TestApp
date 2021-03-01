package com.example.testapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.utils.Consts;
import com.example.testapp.utils.OnFragmentMessageSendListener;

import java.util.Objects;

import static com.example.testapp.fragment.FirstFragment.TAG_FIRST_FRAGMENT;
import static com.example.testapp.fragment.SecondFragment.TAG_SECOND_FRAGMENT;


/**
 * HostFragment intended to contain other fragments.
 * Serves as a container for {@link FirstFragment} and {@link SecondFragment}.
 */
public class HostFragment extends Fragment implements OnFragmentMessageSendListener {

    public static final String TAG_HOST_FRAGMENT = "com.example.testapp.HOST_FRAGMENT";

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

        if (savedInstanceState == null) {
            if (requireActivity().getPreferences(Context.MODE_PRIVATE)
                    .getString(Consts.TAG_SAVED_FRAGMENT, Consts.NO_SAVED_FRAGMENT)
                    .equals(TAG_SECOND_FRAGMENT)) {
                getChildFragmentManager().beginTransaction()
                        .add(R.id.fl_fragment_host_container, new SecondFragment(), TAG_SECOND_FRAGMENT)
                        .commit();
            } else {
                getChildFragmentManager().beginTransaction()
                        .add(R.id.fl_fragment_host_container, new FirstFragment(), TAG_FIRST_FRAGMENT)
                        .commit();
            }
        }
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void onDetach() {
        if (getChildFragmentManager().getFragments().get(0) instanceof FirstFragment) {
            requireActivity()
                    .getPreferences(Context.MODE_PRIVATE)
                    .edit()
                    .putString(Consts.TAG_SAVED_FRAGMENT, TAG_FIRST_FRAGMENT)
                    .commit();
        } else if (getChildFragmentManager().getFragments().get(0) instanceof SecondFragment) {
            requireActivity()
                    .getPreferences(Context.MODE_PRIVATE)
                    .edit()
                    .putString(Consts.TAG_SAVED_FRAGMENT, TAG_SECOND_FRAGMENT)
                    .commit();
        }
        super.onDetach();
    }

    public void replaceWithFragment(String tag) {
        if (tag.equals(TAG_FIRST_FRAGMENT)) {
            if (getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT) == null) {
                replaceFragment(new FirstFragment(), TAG_FIRST_FRAGMENT, false);
            } else if (!getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT).isVisible()) {
                replaceFragment(getChildFragmentManager().findFragmentByTag(TAG_FIRST_FRAGMENT),
                        TAG_FIRST_FRAGMENT, false);
            }
        } else if (tag.equals(TAG_SECOND_FRAGMENT)) {
            if (getChildFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT) == null) {
                replaceFragment(new SecondFragment(), TAG_SECOND_FRAGMENT, false);
            } else if (!getChildFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT).isVisible()) {
                replaceFragment(getChildFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT),
                        TAG_SECOND_FRAGMENT, false);
            }
        }
    }

    @Override
    public void sendMessageToFragment(String fragmentTag, String message) {
        if (fragmentTag.equals(TAG_FIRST_FRAGMENT)) {
            replaceFragment(FirstFragment.newInstance(message), TAG_FIRST_FRAGMENT, true);
        } else if (fragmentTag.equals(TAG_SECOND_FRAGMENT)) {
            replaceFragment(SecondFragment.newInstance(message), TAG_SECOND_FRAGMENT, true);
        }
    }

    private void replaceFragment(Fragment fragment, String fragmentTag, boolean addToBackStack) {
        if (addToBackStack) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_fragment_host_container, fragment, fragmentTag)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_fragment_host_container, fragment, fragmentTag)
                    .commit();
        }
    }
}