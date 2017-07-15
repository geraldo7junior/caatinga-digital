package com.memoria;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.caatinga.R;
import com.caatingadigital.MainActivity;
import com.forca.HangActivity;
import com.imgquiz.MainMenuActivity;
import com.quiz.HomeActivity;

public class ScoreboardActivity extends Activity {

	public static final int MENU1 = Menu.FIRST;
	public static final int MENU2 = Menu.FIRST + 1;
	public static final int MENU3 = Menu.FIRST + 3;
	public static final int MENU4 = Menu.FIRST + 4;
	public static final int MENU6 = Menu.FIRST + 6;

	public static boolean DEBUG = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG) {
			Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
	}

	@Override
	protected void onStart() {
		super.onStart();

		setContentView(R.layout.memory_scoreboard);

		int score = getIntent().getIntExtra(
				"com.memoria.memorygameactivity.SCORE", 99);

		TextView t = (TextView) findViewById(R.id.scoreboard_text);
		t.setText(String.format(getString(R.string.scoreboard_text), score));

		Button startButton = (Button) findViewById(R.id.play_again);
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startGame();
			}
		});

	}

	private void startGame() {
		SharedPreferences settings = getSharedPreferences("memory", 0);
		SharedPreferences.Editor prefeditor = settings.edit();
		prefeditor.putBoolean("novo_jogo", true);
		prefeditor.commit();
		Intent launchGame = new Intent(this, MemoryGameActivity.class);
		startActivity(launchGame);
		ScoreboardActivity.this.finish();
	}

	public boolean onCreateOptionsMenu(Menu options) {
		options.add(0, MENU1, 0, "Quiz");
		options.add(0, MENU2, 0, "Home");
		options.add(0, MENU3, 0, "ImgQuiz");
		options.add(0, MENU4, 0, "Forca");
		options.add(0, MENU6, 0, "Informações");

		return super.onCreateOptionsMenu(options);
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case MENU1:
			Intent mudarDeTela = new Intent(this, HomeActivity.class);
			startActivity(mudarDeTela);
			ScoreboardActivity.this.finish();
			return true;

		case MENU2:
			Intent mudarDeTela3 = new Intent(this, MainActivity.class);
			startActivity(mudarDeTela3);
			ScoreboardActivity.this.finish();
			return true;

		case MENU3:
			Intent mudarDeTela2 = new Intent(this, MainMenuActivity.class);
			startActivity(mudarDeTela2);
			ScoreboardActivity.this.finish();
			return true;

		case MENU4:
			Intent mudarDeTela4 = new Intent(this, HangActivity.class);
			startActivity(mudarDeTela4);
			ScoreboardActivity.this.finish();
			return true;

		case MENU6:
			siteCaatinga();
			ScoreboardActivity.this.finish();
			return true;
		}
		return false;
	}

	void siteCaatinga() {
		String end = "http://mobileufrpe.com.br/caatingadigital";
		Uri uri = Uri.parse(end);
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);
	}

}
