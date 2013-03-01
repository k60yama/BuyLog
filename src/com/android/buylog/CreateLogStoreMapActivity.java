package com.android.buylog;

import com.google.android.maps.MapActivity;

import android.os.Bundle;

public class CreateLogStoreMapActivity extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.createlog_store_input);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
