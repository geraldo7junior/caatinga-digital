package com.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Question {

	private final String questionType;
	private final String question;
	private final ArrayList<String> answers;

	private Question(final String questionType, final String question,
			final ArrayList<String> answers) {
		this.questionType = questionType;
		this.question = question;
		this.answers = answers;
	}

	public static Question parse(final String input)
			throws IllegalArgumentException {

		final String[] questionComponents;
		final String questionType;
		final String question;
		final ArrayList<String> answers = new ArrayList<String>();

		// Split da entrada em virgulas
		if (null != input) {
			questionComponents = input.split(",");
		} else {
			throw new IllegalArgumentException("Questao nula.");
		}

		// Pega os componentes da questao
		if (4 < questionComponents.length) {
			questionType = questionComponents[0].trim();
			question = questionComponents[1].trim();

			for (int i = 2; i < questionComponents.length; i++) {
				answers.add(questionComponents[i].trim());
			}

		} else {
			throw new IllegalArgumentException("Questao incompleta.");

		}

		// Constroi e retorna uma nova questao
		return new Question(questionType, question, answers);

	}

	// retorna o tipo da questao
	public final String getQuestionType() {
		return questionType;
	}

	// retorna os elementos da questaao
	public final String getQuestion() {
		return question;
	}

	// retorna a questao de forma randomica
	public final List<String> getAnswers() {
		final ArrayList<String> answers = new ArrayList<String>(this.answers);
		Collections.shuffle(answers);
		return answers;
	}

	// retorna a resposta correta da questao
	public final String getAnswer() {
		return answers.get(0);
	}

}
