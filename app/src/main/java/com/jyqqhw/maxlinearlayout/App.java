package com.jyqqhw.maxlinearlayout;

import android.app.Application;

import com.wanjian.sak.LayoutManager;

/**
 * Created by wj on 16-12-16.
 */
public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		LayoutManager.init(this);
	}
}
