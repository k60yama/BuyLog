package com.android.buylog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomTabContentView extends FrameLayout {

	public CustomTabContentView(Context con, String tabTitle, int tabIcon){
		super(con);
		
		//LayoutInflaterインスタンス生成
		LayoutInflater mInflater = (LayoutInflater)con.getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//タブウィジェット用レイアウトをインフレート(LinearLayout)
		View view = mInflater.inflate(R.layout.custom_tabwidget, this);
		
		//TextViewインスタンス取得
		TextView tv = (TextView)view.findViewById(R.id.tabTitle);
		tv.setText(tabTitle);
		
		//ImageViewインスタンス取得
		ImageView iv = (ImageView)view.findViewById(R.id.tabIcon);
		iv.setImageResource(tabIcon);
	}
}
