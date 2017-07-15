package com.imgquiz;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.caatinga.R;

public class ImgAnswerActivity extends Activity {

	int pos;
	String answer;
	Intent toReturn = new Intent();

	ImgItem item;

	EditText input;
	Button submit;
	ImageView pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_img_answer);

		Intent intent = getIntent();
		pos = (int) intent.getExtras().getInt("id");

		item = ImgQuizActivity.items.get(pos);
		input = (EditText) findViewById(R.id.etLogoanswer);
		submit = (Button) findViewById(R.id.bLogoanswersubmit);
		pic = (ImageView) findViewById(R.id.ivLogoanswerpic);
		pic.setImageResource(ImgAdapter.mThumbIds[pos]);

		if (item.answered)
			disableAnswer();

		if (item.userAnswer != null)
			input.setText(item.userAnswer);
	}

	@SuppressLint("DefaultLocale")
	// padronizando removendo caixa alta
	public void submitAnswer(View view) {

		CharSequence text = "";
		answer = (input.getText().toString()).toLowerCase();
		item.userAnswer = answer;

		if (checkAnswer()) {
			item.answered = true;
			ImgQuizActivity.numCorrect++;
			text = "Acertou!";

			disableAnswer();

			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					finish();
				}
			}, 1000);
		} else
			text = "Resposta Errada!";

		Toast toast = Toast.makeText(getApplicationContext(), text,
				Toast.LENGTH_SHORT);
		toast.show();
	}

	public void disableAnswer() {
		input.setEnabled(false);
		input.setBackgroundColor(Color.rgb(31, 121, 0));
		input.setTextColor(Color.WHITE);

		ColorMatrix matrix = new ColorMatrix();
		matrix.setSaturation(0); // 0 para deixar cinza
		ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
		pic.setColorFilter(cf);

		submit.setEnabled(false);
		submit.setText("Acertou!");
	}

	public boolean checkAnswer() {
		ArrayList<String> answers = new ArrayList<String>();

		switch (pos) {
		case 0:
			answers.add("Jacaré do papo amarelo");
			answers.add("Jacaré");
			answers.add("jacaré");
			answers.add("Jacare");
			break;

		case 1:
			answers.add("águia chilena");
			answers.add("águia");
			break;

		case 2:
			answers.add("tatu bola");
			answers.add("tatu");
			break;

		case 3:
			answers.add("arara azul");
			answers.add("arara");
			break;

		case 4:
			answers.add("cachorro do mato");
			break;

		case 5:
			answers.add("cactus");
			answers.add("xique xique");
			break;

		case 6:
			answers.add("calango");
			answers.add("cauda verde");
			break;

		case 7:
			answers.add("carcara");
			break;

		case 8:
			answers.add("carnaúba");
			break;

		case 9:
			answers.add("gamba");
			answers.add("orelha branca");
			break;

		case 10:
			answers.add("juazeiro");
			break;

		case 11:
			answers.add("macaco prego");
			break;

		case 12:
			answers.add("mão pelada");
			break;

		case 13:
			answers.add("onça parda");
			break;

		case 14:
			answers.add("perereca");
			break;

		case 15:
			answers.add("periquito");
			break;

		case 16:
			answers.add("saqui de tufos brancos");
			break;

		default:
			break;
		}

		if (answers.contains(answer))
			return true;
		else
			return false;
	}

	@Override
	public void finish() {
		toReturn.putExtra("id", pos);
		setResult(RESULT_OK, toReturn);
		super.finish();
	}

}