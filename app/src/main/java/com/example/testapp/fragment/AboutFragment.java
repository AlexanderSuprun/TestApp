package com.example.testapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;

import com.example.testapp.R;


public class AboutFragment extends DialogFragment {

    public static final String TAG_TITLE = "com.example.testapp.TITLE";

    private AppCompatEditText editTextFeedback;

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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getString(R.string.title_about);
        View dialogView = getLayoutInflater().inflate(R.layout.fragment_about, null);
        editTextFeedback = dialogView.getRootView().findViewById(R.id.edit_text_fragment_about_feedback);

        if (getArguments() != null) {
            title = getArguments().getString(TAG_TITLE);
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(dialogView)
                .setTitle(title)
                .setMessage(getString(R.string.created_by_alexander_suprun))
                .setPositiveButton(getString(R.string.button_send), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendFeedback();
                    }
                });
        return alertDialogBuilder.create();
    }

    private void sendFeedback() {
        if (editTextFeedback.getText() != null && !TextUtils.isEmpty(editTextFeedback.getText())) {

            if (getActivity() instanceof OnSendFeedbackListener) {
                OnSendFeedbackListener listener = (OnSendFeedbackListener) getActivity();
                listener.onFeedbackSend(editTextFeedback.getText().toString());
                dismiss();
            }

        } else {
            Toast.makeText(getActivity(), getString(R.string.toast_enter_something_to_send), Toast.LENGTH_SHORT).show();
            editTextFeedback.requestFocus();
        }
    }


    public interface OnSendFeedbackListener {
        void onFeedbackSend(String message);
    }
}
