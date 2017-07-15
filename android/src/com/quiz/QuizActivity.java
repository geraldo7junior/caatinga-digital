package com.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.caatinga.R;

public class QuizActivity extends Activity {

	private static final String TAG = "QuizActivity";
	public static final int DEFAULT_NUMBER_OF_QUESTIONS = 20;

	private ArrayList<Question> questions;
	private long seed;
	private int currentQuestion;
	private int correctAnswers;
	private int incorrectAnswers;
	private int numberOfQuestions;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		questions = new ArrayList<Question>();

		if (null != savedInstanceState) {
			if (savedInstanceState.containsKey("seed")) {
				seed = savedInstanceState.getLong("seed");
			} else {
				seed = new Random().nextLong();
			}

			if (savedInstanceState.containsKey("currentQuestion")) {
				currentQuestion = savedInstanceState.getInt("currentQuestion");
			} else {
				currentQuestion = 0;
			}

			if (savedInstanceState.containsKey("correctAnswers")) {
				correctAnswers = savedInstanceState.getInt("correctAnswers");
			} else {
				correctAnswers = 0;
			}

			if (savedInstanceState.containsKey("incorrectAnswers")) {
				incorrectAnswers = savedInstanceState
						.getInt("incorrectAnswers");
			} else {
				incorrectAnswers = 0;
			}
		} else {
			seed = new Random().nextLong();
			currentQuestion = 0;
			correctAnswers = 0;
			incorrectAnswers = 0;
		}

		// obtem numero de perguntas que deve ser exibido
		if (null != savedInstanceState
				&& savedInstanceState.containsKey("numberOfQuestions")) {
			numberOfQuestions = savedInstanceState.getInt("numberOfQuestions");
		} else if (null != getIntent().getExtras()
				&& getIntent().getExtras().containsKey("numberOfQuestions")) {
			numberOfQuestions = getIntent().getExtras().getInt(
					"numberOfQuestions");
		} else {
			Log.w(TAG, "Foi iniciado sem um numero especifico de perguntas");
			// String
			numberOfQuestions = DEFAULT_NUMBER_OF_QUESTIONS;
		}

		new LoadQuestionsTask().execute("questions");
	}

	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		outState.putLong("seed", seed);
		outState.putInt("currentQuestion", currentQuestion);
		outState.putInt("correctAnswers", correctAnswers);
		outState.putInt("incorrectAnswers", incorrectAnswers);
		outState.putInt("numberOfQuestions", numberOfQuestions);
		super.onSaveInstanceState(outState);
	}

	// carrega as questoes dos arquivos
	private ArrayList<String> loadQuestions(final String questionFilePath) {
		final ArrayList<String> questions = new ArrayList<String>();
		try {
			for (final String fileName : getAssets().list(questionFilePath)) {
				final InputStream input = getAssets().open(
						questionFilePath + "/" + fileName);
				final BufferedReader reader = new BufferedReader(
						new InputStreamReader(input));

				// Popula o ArrayList de questoes
				String inputLine;
				while (null != (inputLine = reader.readLine())) {
					// ignora comentarios
					if (!inputLine.startsWith("//")
							&& !(inputLine.length() == 0)) {
						questions.add(inputLine);
					}
				}
			}
		} catch (final IOException e) {
			Log.e(TAG, "IOException na leitura do arquivo.", e);

			Toast.makeText(
					getApplicationContext(),
					getResources().getString(
							R.string.question_reading_exception),
					Toast.LENGTH_LONG).show();
		}
		return questions;
	}

	private class LoadQuestionsTask extends AsyncTask<String, Integer, Void> {

		@Override
		protected Void doInBackground(String... params) {
			final ArrayList<String> questionsTemp = new ArrayList<String>();

			// Carrega todas as questoes de todos os caminhos dados como
			// argumento
			for (String path : params) {
				questionsTemp.addAll(loadQuestions(path));
			}

			// Randomize a ordem de saíia das questoes
			final Random rand = new Random(seed);
			Collections.shuffle(questionsTemp, rand);

			// Ajusta as perguntas ao numero especificado pelo usuario
			if (numberOfQuestions < questionsTemp.size()) {
				int failedParses = 0;
				questions = new ArrayList<Question>();
				for (String s : questionsTemp.subList(0, numberOfQuestions)) {
					try {
						questions.add(Question.parse(s));
					} catch (final IllegalArgumentException e) {
						failedParses++;
						Log.e(TAG, "Nao e possivel analisar a pergunta: " + s,
								e);

					}
				}

				// quantidade de erros
				if (0 < failedParses) {
					Toast.makeText(
							getApplicationContext(),
							String.format(
									getResources()
											.getQuantityString(
													R.plurals.question_reading_parse_fail_number,
													failedParses), failedParses),
							Toast.LENGTH_LONG).show();
				}
			} else {
				int failedParses = 0;
				questions = new ArrayList<Question>();
				for (String s : questionsTemp) {
					try {
						questions.add(Question.parse(s));
					} catch (final IllegalArgumentException e) {
						failedParses++;
						Log.e(TAG, "Nao e possivel analisar a pergunta: " + s,
								e);

					}
				}

				// quantidade de erros
				if (0 < failedParses) {
					Toast.makeText(
							getApplicationContext(),
							String.format(
									getResources()
											.getQuantityString(
													R.plurals.question_reading_parse_fail_number,
													failedParses), failedParses),
							Toast.LENGTH_LONG).show();
				}
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			// Se nenhuma pergunta foi carregada, feche a atividade
			if (questions.isEmpty()) {
				QuizActivity.this.finish();
			} else {
				displayQuestion(currentQuestion);
			}
		}

		private void displayQuestion(final int questionID) {
			setContentView(R.layout.quiz);

			// Exibe o tema da questao
			final TextView quizTopic = (TextView) findViewById(R.id.quiz_topic);
			quizTopic.setText(questions.get(questionID).getQuestionType());

			// Exibe a questao
			final TextView quizQuestion = (TextView) findViewById(R.id.quiz_question);
			quizQuestion.setText(questions.get(questionID).getQuestion());

			// Exibe as respostas da questao
			final ListView quizAnswers = (ListView) findViewById(R.id.quiz_answers);

			final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					QuizActivity.this, R.layout.quiz_answer_row, questions
							.get(questionID).getAnswers()
							.toArray(new String[0]));
			quizAnswers.setAdapter(adapter);

			// configura o OnClickListener do ListView
			quizAnswers
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						public void onItemClick(final AdapterView<?> parent,
								final View view, final int position,
								final long id) {
							final String selectedAnswer = ((TextView) view)
									.getText().toString();
							if (selectedAnswer.equals(questions.get(questionID)
									.getAnswer())) {
								correctAnswers++;
								Toast.makeText(
										getApplicationContext(),
										getResources().getString(
												R.string.answer_correct),
										Toast.LENGTH_SHORT).show();
							} else {
								incorrectAnswers++;
								String resp = questions.get(questionID)
										.getAnswer();
								Toast.makeText(getApplicationContext(),
										"A resposta correta é: " + resp,
										Toast.LENGTH_SHORT).show();
							}

							// Exibe a proxima questao
							currentQuestion++;

							if (currentQuestion <= questions.size() - 1) {
								displayQuestion(currentQuestion);
							} else {
								// Desativa a Intent e finaliza esta Activity
								final Intent i = new Intent(QuizActivity.this,
										QuizEndActivity.class);
								i.putExtra("correctAnswers", correctAnswers);
								i.putExtra("incorrectAnswers", incorrectAnswers);
								startActivity(i);

								QuizActivity.this.finish();
							}
						}
					});
		}
	}
}
