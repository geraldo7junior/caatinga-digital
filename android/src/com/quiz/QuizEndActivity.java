package com.quiz;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.caatinga.R;
import com.caatingadigital.MainActivity;
import com.forca.HangActivity;
import com.imgquiz.MainMenuActivity;
import com.memoria.MenuActivity;

public class QuizEndActivity extends Activity {
	public static final int MENU1 = Menu.FIRST;
	public static final int MENU2 = Menu.FIRST + 1;
	public static final int MENU3 = Menu.FIRST + 3;
	public static final int MENU4 = Menu.FIRST + 4;
	public static final int MENU6 = Menu.FIRST + 6;

	private static final String TAG = "QuizEndActivity";

	private int correctAnswers;
	private int incorrectAnswers;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// obtem os valores para as respostas corretas e incorretas
		if (null != savedInstanceState
				&& savedInstanceState.containsKey("correctAnswers")
				&& savedInstanceState.containsKey("incorrectAnswers")) {
			correctAnswers = savedInstanceState.getInt("correctAnswers");
			incorrectAnswers = savedInstanceState.getInt("incorrectAnswers");
		} else if (null != getIntent().getExtras()
				&& getIntent().getExtras().containsKey("correctAnswers")
				&& getIntent().getExtras().containsKey("incorrectAnswers")) {
			correctAnswers = getIntent().getExtras().getInt("correctAnswers");
			incorrectAnswers = getIntent().getExtras().getInt(
					"incorrectAnswers");
		} else {
			Log.w(TAG,
					"Os dados para o numero de respostas corretas/incorretas nao foram fornecidos.");
			correctAnswers = 0;
			incorrectAnswers = 0;
		}

		// Exibi o final do questionario
		setContentView(R.layout.quiz_end_summary);

		// Define os valores para respostas corretas e incorretas
		final TextView correctAnswersText = (TextView) findViewById(R.id.quiz_end_correct_number);
		correctAnswersText.setText(String.valueOf(correctAnswers));

		final TextView incorrectAnswersText = (TextView) findViewById(R.id.quiz_end_incorrect_number);
		incorrectAnswersText.setText(String.valueOf(incorrectAnswers));

		final Button quizEndButton = (Button) findViewById(R.id.quiz_end_accept_button);
		quizEndButton.setOnClickListener(new OnClickListener() {

			public void onClick(final View v) {
				QuizEndActivity.this.finish();
			}
		});

		Button menu = (Button) findViewById(R.id.btnmenu);
		menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaMenu();
			}
		});
	}

	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		outState.putInt("correctAnswers", correctAnswers);
		outState.putInt("incorrectAnswers", incorrectAnswers);
		super.onSaveInstanceState(outState);
	}

	public void chamaMenu() {
		Intent entra = new Intent(this, HomeActivity.class);
		entra.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(entra);
		QuizEndActivity.this.finish();
	}

	public boolean onCreateOptionsMenu(Menu options) {
		options.add(0, MENU1, 0, "Home");
		options.add(0, MENU2, 0, "Memória");
		options.add(0, MENU3, 0, "ImgQuiz");
		options.add(0, MENU4, 0, "Forca");
		options.add(0, MENU6, 0, "Informações");

		return super.onCreateOptionsMenu(options);
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case MENU1:
			Intent mudarDeTela = new Intent(this, MainActivity.class);
			startActivity(mudarDeTela);
			QuizEndActivity.this.finish();
			return true;

		case MENU2:
			Intent mudarDeTela3 = new Intent(this, MenuActivity.class);
			startActivity(mudarDeTela3);
			QuizEndActivity.this.finish();
			return true;

		case MENU3:
			Intent mudarDeTela2 = new Intent(this, MainMenuActivity.class);
			startActivity(mudarDeTela2);
			QuizEndActivity.this.finish();
			return true;

		case MENU4:
			Intent mudarDeTela4 = new Intent(this, HangActivity.class);
			startActivity(mudarDeTela4);
			QuizEndActivity.this.finish();
			return true;

		case MENU6:
			siteCaatinga();
			QuizEndActivity.this.finish();
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