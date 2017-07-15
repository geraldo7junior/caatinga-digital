package com.imgquiz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.caatinga.R;
import com.caatingadigital.MainActivity;
import com.forca.HangActivity;
import com.memoria.MenuActivity;
import com.quiz.HomeActivity;

public class ImgQuizActivity extends Activity {
	public static final int MENU1 = Menu.FIRST;
	public static final int MENU2 = Menu.FIRST + 1;
	public static final int MENU3 = Menu.FIRST + 3;
	public static final int MENU4 = Menu.FIRST + 4;
	public static final int MENU6 = Menu.FIRST + 6;

	public static ArrayList<ImgItem> items;
	public static int numCorrect;
	TextView correct;
	ImgAdapter imgAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_img_quiz);

		// Inicializando adapter
		imgAdapter = new ImgAdapter(this);

		items = new ArrayList<ImgItem>();
		for (int i = 0; i < ImgAdapter.mThumbIds.length; i++)
			items.add(new ImgItem(i));

		numCorrect = 0;

		// Barra de progresso
		correct = (TextView) findViewById(R.id.tvNumCorrect);
		correct.setText("Progresso: 0%");

		GridView gridview = (GridView) findViewById(R.id.logoquiz);
		gridview.setAdapter(imgAdapter);
		gridview.setVerticalScrollBarEnabled(false);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent intent = new Intent(ImgQuizActivity.this, ImgAnswerActivity.class);
				intent.putExtra("id", position);

				startActivityForResult(intent, position);
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent i) {

		int id = i.getExtras().getInt("id");
		if (items.get(id).answered) {
			imgAdapter.notifyDataSetChanged();
			correct = (TextView) findViewById(R.id.tvNumCorrect);
			int progress = (Integer) 100
					* (numCorrect / ImgAdapter.mThumbIds.length);
			correct.setText("Progresso: " + progress + "%");
		}
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
			ImgQuizActivity.this.finish();
			return true;

		case MENU2:
			Intent mudarDeTela3 = new Intent(this, MenuActivity.class);
			startActivity(mudarDeTela3);
			ImgQuizActivity.this.finish();
			return true;

		case MENU3:
			Intent mudarDeTela2 = new Intent(this, MainActivity.class);
			startActivity(mudarDeTela2);
			ImgQuizActivity.this.finish();
			return true;

		case MENU4:
			Intent mudarDeTela4 = new Intent(this, HangActivity.class);
			startActivity(mudarDeTela4);
			ImgQuizActivity.this.finish();
			return true;

		case MENU6:
			siteCaatinga();
			ImgQuizActivity.this.finish();
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