package com.example.testapp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;

import com.example.testapp.R;


public class AboutFragment extends DialogFragment {

    public static final String TAG_TITLE = "com.example.testapp.TITLE";

    private AppCompatEditText editTextFeedback;
    private AppCompatButton buttonSend;

    public AboutFragment() {
        //Required empty public constructor
    }

    public static AboutFragment newInstance(String title) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(TAG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextFeedback = view.findViewById(R.id.edit_text_fragment_about_feedback);
        buttonSend = view.findViewById(R.id.button_fragment_about_send);

        if (getArguments() != null && getDialog() != null) {
            getDialog().setTitle(getArguments().getString(TAG_TITLE,
                    getString(R.string.title_about)));
        } else if (getDialog() != null) {
            getDialog().setTitle(getString(R.string.title_about));
            editTextFeedback.requestFocus();
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });
    }

    private void sendFeedback() {
        if (editTextFeedback.getText() != null && !TextUtils.isEmpty(editTextFeedback.getText())) {

            if (getActivity() instanceof OnFeedbackSendListener) {
                OnFeedbackSendListener listener = (OnFeedbackSendListener) getActivity();
                listener.onFeedbackSend(editTextFeedback.getText().toString());
                dismiss();
            }

        } else {
            Toast.makeText(getActivity(), "Enter something to send", Toast.LENGTH_SHORT).show();
            editTextFeedback.requestFocus();
        }
    }


    public interface OnFeedbackSendListener {
        void onFeedbackSend(String message);
    }
}
