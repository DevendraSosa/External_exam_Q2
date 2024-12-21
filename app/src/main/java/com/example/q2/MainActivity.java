package com.example.q2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat switchSound, switchVibration, switchLed, switchBanners, switchContent, switchLockScreen;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchSound = findViewById(R.id.switch_sound);
        switchVibration = findViewById(R.id.switch_vibration);
        switchLed = findViewById(R.id.switch_led);
        switchBanners = findViewById(R.id.switch_banners);
        switchContent = findViewById(R.id.switch_content);
        switchLockScreen = findViewById(R.id.switch_lock_screen);
        saveButton = findViewById(R.id.save_button);

        loadPreferences();


        saveButton.setOnClickListener(v -> {
            showConfirmationSheet();
        });
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("NotificationPrefs", MODE_PRIVATE);
        switchSound.setChecked(sharedPreferences.getBoolean("sound", true));
        switchVibration.setChecked(sharedPreferences.getBoolean("vibration", true));
        switchLed.setChecked(sharedPreferences.getBoolean("led", true));
        switchBanners.setChecked(sharedPreferences.getBoolean("banners", true));
        switchContent.setChecked(sharedPreferences.getBoolean("content", true));
        switchLockScreen.setChecked(sharedPreferences.getBoolean("lock_screen", true));
    }

    private void savePreferences() {
        boolean isSoundOn = switchSound.isChecked();
        boolean isVibrationOn = switchVibration.isChecked();
        boolean isLedOn = switchLed.isChecked();
        boolean isBannersOn = switchBanners.isChecked();
        boolean isContentOn = switchContent.isChecked();
        boolean isLockScreenOn = switchLockScreen.isChecked();

        SharedPreferences sharedPreferences = getSharedPreferences("NotificationPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("sound", isSoundOn);
        editor.putBoolean("vibration", isVibrationOn);
        editor.putBoolean("led", isLedOn);
        editor.putBoolean("banners", isBannersOn);
        editor.putBoolean("content", isContentOn);
        editor.putBoolean("lock_screen", isLockScreenOn);
        editor.apply();

        Toast.makeText(this, "Preferences Saved", Toast.LENGTH_SHORT).show();
    }

    private void showConfirmationSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_confirmation, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        bottomSheetView.findViewById(R.id.confirm_button).setOnClickListener(v -> {
            savePreferences();
            bottomSheetDialog.dismiss();
        });

        bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }
}
