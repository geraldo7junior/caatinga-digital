package com.forca;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.caatinga.R;
import com.caatingadigital.MainActivity;
import com.imgquiz.MainMenuActivity;
import com.memoria.MenuActivity;
import com.quiz.HomeActivity;

public class HangActivity extends Activity implements OnClickListener {
	public static final int MENU1 = Menu.FIRST;
	public static final int MENU2 = Menu.FIRST + 1;
	public static final int MENU3 = Menu.FIRST + 3;
	public static final int MENU4 = Menu.FIRST + 4;
	public static final int MENU6 = Menu.FIRST + 6;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hang);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Button bForca = (Button) findViewById(R.id.btnForca);
		Button bSair = (Button) findViewById(R.id.btnSair);
		bForca.setOnClickListener(this);
		bSair.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent i = new Intent(HangActivity.this, HangManActivity.class);
		switch (v.getId()) {
		case R.id.btnForca:
			i.putExtra("tipo", "jogador1");
			startActivity(i);
			HangActivity.this.finish();
			break;

		case R.id.btnSair:
			finish();
		}
	}

	public boolean onCreateOptionsMenu(Menu options) {
		options.add(0, MENU1, 0, "Quiz");
		options.add(0, MENU2, 0, "Memória");
		options.add(0, MENU3, 0, "ImgQuiz");
		options.add(0, MENU4, 0, "Home");
		options.add(0, MENU6, 0, "Informações");

		return super.onCreateOptionsMenu(options);
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case MENU1:
			Intent mudarDeTela = new Intent(this, HomeActivity.class);
			startActivity(mudarDeTela);
			HangActivity.this.finish();
			return true;

		case MENU2:
			Intent mudarDeTela3 = new Intent(this, MenuActivity.class);
			startActivity(mudarDeTela3);
			HangActivity.this.finish();
			return true;

		case MENU3:
			Intent mudarDeTela2 = new Intent(this, MainMenuActivity.class);
			startActivity(mudarDeTela2);
			HangActivity.this.finish();
			return true;

		case MENU4:
			Intent mudarDeTela4 = new Intent(this, MainActivity.class);
			startActivity(mudarDeTela4);
			HangActivity.this.finish();
			return true;

		case MENU6:
			siteCaatinga();
			HangActivity.this.finish();
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
