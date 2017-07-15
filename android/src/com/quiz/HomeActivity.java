package com.quiz;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.caatinga.R;
import com.caatingadigital.MainActivity;
import com.forca.HangActivity;
import com.imgquiz.MainMenuActivity;
import com.memoria.MenuActivity;

public class HomeActivity extends Activity {
	public static final int MENU1 = Menu.FIRST;
	public static final int MENU2 = Menu.FIRST + 1;
	public static final int MENU3 = Menu.FIRST + 3;
	public static final int MENU4 = Menu.FIRST + 4;
	public static final int MENU6 = Menu.FIRST + 6;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_home);

		final Button startQuiz = (Button) findViewById(R.id.btn_start_quiz);
		startQuiz.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				final Intent i = new Intent(HomeActivity.this,
						QuizActivity.class);

				// pega oq o usuario informou de numero de perguntas
				final EditText numberOfQuestionsInput = (EditText) findViewById(R.id.input_number_of_questions);
				final int numberOfQuestions;
				try {
					numberOfQuestions = Integer.parseInt(numberOfQuestionsInput
							.getText().toString());
				} catch (final NumberFormatException e) {
					Toast.makeText(
							getApplicationContext(),
							getResources().getString(
									R.string.invalid_number_of_questions),
							Toast.LENGTH_LONG).show();
					return;
				}

				// verifica se ao menos 1 pergunta foi informada
				if (numberOfQuestions < 1) {
					Toast.makeText(
							getApplicationContext(),
							getResources().getString(
									R.string.invalid_number_of_questions),
							Toast.LENGTH_LONG).show();
					return;
				}

				i.putExtra("numberOfQuestions", numberOfQuestions);
				startActivity(i);
				HomeActivity.this.finish();
			}
		});
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
			HomeActivity.this.finish();
			return true;

		case MENU2:
			Intent mudarDeTela3 = new Intent(this, MenuActivity.class);
			startActivity(mudarDeTela3);
			HomeActivity.this.finish();
			return true;

		case MENU3:
			Intent mudarDeTela2 = new Intent(this, MainMenuActivity.class);
			startActivity(mudarDeTela2);
			HomeActivity.this.finish();
			return true;

		case MENU4:
			Intent mudarDeTela4 = new Intent(this, HangActivity.class);
			startActivity(mudarDeTela4);
			HomeActivity.this.finish();
			return true;

		case MENU6:
			siteCaatinga();
			HomeActivity.this.finish();
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
