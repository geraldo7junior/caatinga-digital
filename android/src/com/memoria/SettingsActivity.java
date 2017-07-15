package com.memoria;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.caatinga.R;

public class SettingsActivity extends Activity {

	private CheckBox ch_sound_correct, ch_sound_incorrect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memory_settings);

		SharedPreferences settings = getSharedPreferences("memory", 0);

		ch_sound_correct = (CheckBox) findViewById(R.id.ch_sound_correct);
		ch_sound_incorrect = (CheckBox) findViewById(R.id.ch_sound_incorrect);
		ch_sound_correct.setChecked(settings.getBoolean(
				"play_sound_when_correct", true));
		ch_sound_incorrect.setChecked(settings.getBoolean(
				"play_sound_when_incorrect", true));

		Button saveButtonMenu = (Button) findViewById(R.id.save_prefs);
		saveButtonMenu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent launchMenu = new Intent(getBaseContext(),
						MenuActivity.class);
				setPreferencesAndGoTo(launchMenu);
				SettingsActivity.this.finish();
			}

		});

		Button saveButtonGame = (Button) findViewById(R.id.save_game);
		saveButtonGame.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent launchGame = new Intent(getBaseContext(),
						MemoryGameActivity.class);
				setPreferencesAndGoTo(launchGame);
				SettingsActivity.this.finish();
			}
		});

	}

	private void setPreferencesAndGoTo(Intent theIntent) {
		SharedPreferences settings = getSharedPreferences("memory", 0);
		SharedPreferences.Editor prefeditor = settings.edit();
		prefeditor.putBoolean("som_acerto", ch_sound_correct.isChecked());
		prefeditor.putBoolean("som_erro", ch_sound_incorrect.isChecked());
		prefeditor.putBoolean("novo_jogo", false);
		prefeditor.commit();
		startActivity(theIntent);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Button saveButtonGame = (Button) findViewById(R.id.save_game);
		Button saveButtonMenu = (Button) findViewById(R.id.save_prefs);
		SharedPreferences settings = getSharedPreferences("memory", 0);
		if (settings.getString("tela", "") == "Menu") {
			saveButtonGame.setVisibility(View.GONE);
			saveButtonMenu.setVisibility(View.VISIBLE);
		}

		if (settings.getString("tela", "") == "Jogar") {
			saveButtonMenu.setVisibility(View.GONE);
			saveButtonGame.setVisibility(View.VISIBLE);
		}
	}

}
