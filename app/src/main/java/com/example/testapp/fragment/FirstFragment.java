package com.example.testapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.utils.OnFragmentMessageSendListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    private static final String ARG_TEXT = "com.example.testapp.TEXT";

    private OnFragmentMessageSendListener listener;
    private AppCompatEditText editTextMessage;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param string Text to send to another fragment.
     * @return A new instance of fragment FirstFragment.
     */
    public static FirstFragment newInstance(String string) {
        FirstFragment fragment = new FirstFragment();
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
            throw new ClassCastException(getParentFragment().toString()
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
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextMessage = view.findViewById(R.id.edit_text_fragment_first_message);

        if (getArguments() != null) {
            editTextMessage.setText(getArguments().getString(ARG_TEXT));
        }

        view.findViewById(R.id.button_fragment_first_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        if (editTextMessage.getText() != null && !TextUtils.isEmpty(editTextMessage.getText())) {
            listener.sendMessageToSecondFragment(editTextMessage.getText().toString());
        } else {
            Toast.makeText(getContext(), "Fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

}