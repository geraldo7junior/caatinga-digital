package com.imgquiz;

public class ImgItem {

	boolean answered;
	int id;
	String userAnswer;

	public ImgItem(int i) {
		id = i;
		answered = false;
	}
}