package com.example.smarthome.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.smarthome.R;
import com.ksxkq.materialpreference.SimpleOnPreferenceCallback;
import com.ksxkq.materialpreference.widget.PreferenceContainer;
import com.ksxkq.materialpreference.widget.ScreenPreference;

import static android.app.Activity.RESULT_OK;

//设置界面
public class SettingFragment extends Fragment {

    private View view;

    private static final int REQUEST_CODE_FOR_SUMMARY = 0;
    PreferenceContainer mContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_settings,container,false);
        mContainer = (PreferenceContainer) view.findViewById(R.id.container);
        mContainer
                .addCategoryPreference("cate_switch", "Switch")
                .addSwitchPreference("switch_main", "Switch", true)
                .addCheckBoxPreference("checkbox_main", "CheckBox", true)
                .addCategoryPreference("cate_seekbar", "Seekbar")
                .addSeekbarPreference("seekbar_transparency", "Transparency", 0, 100)
                .addCategoryPreference("cate_screen", "Screen")
                .addScreenPreference("setting", "Setting")
                .addCategoryPreference("cate_list", "List")
                .addListPreference("list_sensitivity", "Sensitivity", R.array.sensitivity_names, R.array.sensitivity_names);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_SUMMARY && resultCode == RESULT_OK) {
            String key = data.getStringExtra("key");
            String summary = data.getStringExtra("summary");
            final ScreenPreference preference = mContainer.getPreference(key);
            if (preference != null) {
                preference.setSummary(summary);
            }
        }
    }
}
