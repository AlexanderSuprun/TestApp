package com.example.testapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.utils.OnFragmentMessageSendListener;

import static com.example.testapp.fragment.FirstFragment.TAG_FIRST_FRAGMENT;


/**
 * Contains simple form. Sends message to {@link FirstFragment}.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    public static final String TAG_SECOND_FRAGMENT = "com.example.testapp.SECOND_FRAGMENT";
    private static final String ARG_TEXT = "com.example.testapp.TEXT";

    private OnFragmentMessageSendListener listener;
    private AppCompatEditText editTextMessage;

    public SecondFragment() {
        // Required empty public constructor
        Log.d("FRAGMENT_TAG", "Second fragment created");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param string Text to send to another fragment.
     * @return A new instance of fragment SecondFragment.
     */
    public static SecondFragment newInstance(String string) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, string);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragmentMessageSendListener) {
            this.listener = (OnFragmentMessageSendListener) getParentFragment();
        } else {
            throw new ClassCastException(requireParentFragment().toString()
                    + " must implement OnFragmentMessageSendListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextMessage = view.findViewById(R.id.edit_text_fragment_second_message);

        if (getArguments() != null) {
            ((AppCompatTextView) view.findViewById(R.id.text_view_fragment_second_show_message))
                    .setText(getArguments().getString(ARG_TEXT));
        }

        view.findViewById(R.id.button_fragment_second_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        if (editTextMessage.getText() != null && !TextUtils.isEmpty(editTextMessage.getText())) {
            listener.sendMessageToFragment(TAG_FIRST_FRAGMENT, editTextMessage.getText().toString());
        } else {
            Toast.makeText(getContext(), getString(R.string.toast_fill_in_all_fields), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}