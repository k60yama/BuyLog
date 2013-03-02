package com.android.buylog;

import com.google.android.maps.MapActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class CreateLogStoreMapActivity extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルの指定
		this.setContentView(R.layout.createlog_store_input);
		
		//フォント設定
		this.setFontType();
	}

	private void setFontType(){
		//フォントファイル取得
		Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/APJapanesefont.ttf");
		
		//TextViewインスタンス取得
		TextView storeTitle = (TextView)this.findViewById(R.id.storeTitle);
		storeTitle.setTypeface(tf);
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
