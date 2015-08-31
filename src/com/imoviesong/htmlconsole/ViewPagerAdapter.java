package com.imoviesong.htmlconsole;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewPagerAdapter extends PagerAdapter {
	int size;
	Activity act;
	View layout;
	EditText pagenumber;
	Button click;

	public ViewPagerAdapter(textfile mainActivity, int noofsize) {
		// TODO Auto-generated constructor stub
		size = noofsize;
		act = mainActivity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) act
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.layout.pages, null);
		pagenumber = (EditText) layout.findViewById(R.id.pagenumber);
		int pagenumberTxt=position + 1;
		pagenumber.setText("Now your in Page No  " +pagenumberTxt );
		((ViewPager) container).addView(layout, 0);
		return layout;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	// }

}
