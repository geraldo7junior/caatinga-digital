package com.imgquiz;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.caatinga.R;

public class ImgAdapter extends BaseAdapter {
	private Context mContext;
	private ColorMatrixColorFilter cf;

	public ImgAdapter(Context c) {
		mContext = c;
		ColorMatrix matrix = new ColorMatrix();
		matrix.setSaturation(0); // 0 para deixar cinza
		cf = new ColorMatrixColorFilter(matrix);
	}

	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;

		imageView = (ImageView) convertView;
		imageView = new ImageView(mContext);
		imageView.setLayoutParams(new GridView.LayoutParams(80, 80));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setAdjustViewBounds(true);
		imageView.setImageResource(mThumbIds[position]);

		if (ImgQuizActivity.items.get(position).answered) {
			imageView.setColorFilter(cf);
			imageView.setAlpha(175);
		}

		return imageView;
	}

	public static Integer[] mThumbIds = { R.drawable.jacare, R.drawable.aguia,
			R.drawable.tatu, R.drawable.arara, R.drawable.cachorro,
			R.drawable.cactus, R.drawable.calango, R.drawable.carcara,
			R.drawable.carnauba, R.drawable.gamba, R.drawable.juazeiro,
			R.drawable.macaco, R.drawable.mao, R.drawable.onca,
			R.drawable.perereca, R.drawable.periquito, R.drawable.sagui };

}