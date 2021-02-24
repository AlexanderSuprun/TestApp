package com.example.testapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.testapp.R;
import com.example.testapp.utils.Consts;
import com.google.android.material.snackbar.Snackbar;


public class SettingsFragment extends PreferenceFragmentCompat {


    public static final String TAG_SETTINGS_FRAGMENT = "com.example.testapp.TAG_SETTINGS_FRAGMENT";

    private SwitchPreference switchPushPreference;
    private CheckBoxPreference checkBoxUpdatePreference;
    private ListPreference listLanguagePreference;
    private boolean pushPreferenceSavedState = false;
    private boolean updatePreferenceSavedState = false;
    private String languagePreferenceSavedState;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pushPreferenceSavedState = getPreferenceManager()
                .getSharedPreferences()
                .getBoolean(getString(R.string.preference_key_push_notifications), false);
        updatePreferenceSavedState = getPreferenceManager()
                .getSharedPreferences()
                .getBoolean(getString(R.string.preference_key_auto_updates), false);
        languagePreferenceSavedState = getPreferenceManager()
                .getSharedPreferences()
                .getString(getString(R.string.preference_key_language), getString(R.string.language_not_set));

        switchPushPreference = getPreferenceScreen().findPreference(getString(R.string.preference_key_push_notifications));
        checkBoxUpdatePreference = getPreferenceScreen().findPreference(getString(R.string.preference_key_auto_updates));
        listLanguagePreference = getPreferenceScreen().findPreference(getString(R.string.preference_key_language));
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getKey().equals(getString(R.string.preference_key_push_notifications)) ||
                preference.getKey().equals(getString(R.string.preference_key_auto_updates)) ||
                preference.getKey().equals(getString(R.string.preference_key_language))) {
            if (getView() != null) {
                Snackbar.make(getView(), R.string.snackbar_settings_changed, Snackbar.LENGTH_LONG)
                        .setAction(R.string.snackbar_action_undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switchPushPreference.setChecked(pushPreferenceSavedState);
                                checkBoxUpdatePreference.setChecked(updatePreferenceSavedState);
                                listLanguagePreference.setValue(languagePreferenceSavedState);
                            }
                        }).show();
                return true;
            }
        }
        return false;
    }

    public Intent getResultIntent() {
        return new Intent()
                .putExtra(Consts.EXTRA_PUSH_SETTING, switchPushPreference.isChecked())
                .putExtra(Consts.EXTRA_UPDATE_SETTING, checkBoxUpdatePreference.isChecked())
                .putExtra(Consts.EXTRA_LANGUAGE_SETTING, listLanguagePreference.getValue());
    }
}
