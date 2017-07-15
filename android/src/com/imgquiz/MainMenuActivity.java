package com.imgquiz;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.caatinga.R;
import com.caatingadigital.MainActivity;
import com.forca.HangActivity;
import com.memoria.MenuActivity;
import com.quiz.HomeActivity;

public class MainMenuActivity extends Activity {
	public static final int MENU1 = Menu.FIRST;
	public static final int MENU2 = Menu.FIRST + 1;
	public static final int MENU3 = Menu.FIRST + 3;
	public static final int MENU4 = Menu.FIRST + 4;
	public static final int MENU6 = Menu.FIRST + 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_img_menu);
	}

	public void play(View view) {
		Intent intent = new Intent(MainMenuActivity.this, ImgQuizActivity.class);
		startActivity(intent);
		MainMenuActivity.this.finish();
	}

	public boolean onCreateOptionsMenu(Menu options) {
		options.add(0, MENU1, 0, "Quiz");
		options.add(0, MENU2, 0, "Memória");
		options.add(0, MENU3, 0, "Home");
		options.add(0, MENU4, 0, "Forca");
		options.add(0, MENU6, 0, "Informações");

		return super.onCreateOptionsMenu(options);
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case MENU1:
			Intent mudarDeTela = new Intent(this, HomeActivity.class);
			startActivity(mudarDeTela);
			MainMenuActivity.this.finish();
			return true;

		case MENU2:
			Intent mudarDeTela3 = new Intent(this, MenuActivity.class);
			startActivity(mudarDeTela3);
			MainMenuActivity.this.finish();
			return true;

		case MENU3:
			Intent mudarDeTela2 = new Intent(this, MainActivity.class);
			startActivity(mudarDeTela2);
			MainMenuActivity.this.finish();
			return true;

		case MENU4:
			Intent mudarDeTela4 = new Intent(this, HangActivity.class);
			startActivity(mudarDeTela4);
			MainMenuActivity.this.finish();
			return true;

		case MENU6:
			siteCaatinga();
			MainMenuActivity.this.finish();
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
