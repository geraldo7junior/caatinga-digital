package com.memoria;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.caatinga.R;
import com.caatingadigital.MainActivity;
import com.forca.HangActivity;
import com.imgquiz.MainMenuActivity;
import com.quiz.HomeActivity;

public class MenuActivity extends ListActivity {

	public static final int MENU1 = Menu.FIRST;
	public static final int MENU2 = Menu.FIRST + 1;
	public static final int MENU3 = Menu.FIRST + 3;
	public static final int MENU4 = Menu.FIRST + 4;
	public static final int MENU6 = Menu.FIRST + 6;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final String[] ACTIVITY_CHOICES = new String[] {
				getString(R.string.menu_new_game),
				getString(R.string.menu_prefss) };

		setContentView(R.layout.memory_menu);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ACTIVITY_CHOICES));
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		getListView().setTextFilterEnabled(true);
		OnItemClickListener myOnItemClickListener = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					startGame();
					break;
				case 1:
					startPrefs();
					break;
				default:
					break;
				}

			}
		};
		getListView().setOnItemClickListener(myOnItemClickListener);

	}

	private void startGame() {
		SharedPreferences settings = getSharedPreferences("memory", 0);
		SharedPreferences.Editor prefeditor = settings.edit();
		prefeditor.putBoolean("novo_jogo", true);
		prefeditor.putInt("score", 100);
		prefeditor.commit();

		Intent launchGame = new Intent(this, MemoryGameActivity.class);
		startActivity(launchGame);
		MenuActivity.this.finish();
	}

	private void startPrefs() {
		Intent launchPrefs = new Intent(this, SettingsActivity.class);
		startActivity(launchPrefs);
		MenuActivity.this.finish();
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences settings = getSharedPreferences("memory", 0);
		SharedPreferences.Editor prefeditor = settings.edit();
		prefeditor.putString("tela", "Menu");
		prefeditor.commit();
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
			MenuActivity.this.finish();
			return true;

		case MENU2:
			Intent mudarDeTela3 = new Intent(this, MainActivity.class);
			startActivity(mudarDeTela3);
			MenuActivity.this.finish();
			return true;

		case MENU3:
			Intent mudarDeTela2 = new Intent(this, MainMenuActivity.class);
			startActivity(mudarDeTela2);
			MenuActivity.this.finish();
			return true;

		case MENU4:
			Intent mudarDeTela4 = new Intent(this, HangActivity.class);
			startActivity(mudarDeTela4);
			MenuActivity.this.finish();
			return true;

		case MENU6:
			siteCaatinga();
			MenuActivity.this.finish();
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