package com.caatingadigital;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.caatinga.R;
import com.forca.HangActivity;
import com.imgquiz.MainMenuActivity;
import com.memoria.MenuActivity;
import com.quiz.HomeActivity;

public class MainActivity extends Activity {

	public static final int MENU1 = Menu.FIRST;
	public static final int MENU2 = Menu.FIRST + 1;
	public static final int MENU3 = Menu.FIRST + 3;
	public static final int MENU4 = Menu.FIRST + 4;
	public static final int MENU6 = Menu.FIRST + 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		ImageButton quiz = (ImageButton) findViewById(R.id.ibtnQuiz);
		quiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaQuiz();
			}
		});

		ImageButton forca = (ImageButton) findViewById(R.id.ibtnForca);
		forca.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaForca();
			}
		});

		ImageButton quebra = (ImageButton) findViewById(R.id.ibtnMemoria);
		quebra.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaQuebraCabeca();
			}
		});

		ImageButton imgquiz = (ImageButton) findViewById(R.id.ibtimgquiz);
		imgquiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaImgQuiz();
			}
		});
	}

	public void chamaQuiz() {
		Intent entra = new Intent(this, HomeActivity.class);
		entra.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(entra);
	}

	public void chamaForca() {
		Intent entra = new Intent(this, HangActivity.class);
		entra.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(entra);
	}

	public void chamaQuebraCabeca() {
		Intent entra = new Intent(this, MenuActivity.class);
		entra.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(entra);
	}

	public void chamaImgQuiz() {
		Intent entra = new Intent(this, MainMenuActivity.class);
		entra.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(entra);
	}

	public boolean onCreateOptionsMenu(Menu options) {
		options.add(0, MENU1, 0, "Quiz");
		options.add(0, MENU2, 0, "Memória");
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
			return true;

		case MENU2:
			Intent mudarDeTela3 = new Intent(this, MenuActivity.class);
			startActivity(mudarDeTela3);
			return true;

		case MENU3:
			Intent mudarDeTela2 = new Intent(this, MainMenuActivity.class);
			startActivity(mudarDeTela2);
			return true;

		case MENU4:
			Intent mudarDeTela4 = new Intent(this, HangActivity.class);
			startActivity(mudarDeTela4);
			return true;

		case MENU6:
			siteCaatinga();
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
