package com.android.buylog;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class CreateLogItemActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルの指定
		this.setContentView(R.layout.createlog_item);
		
		//フォント適用
		this.setFontType();
	}
	
	private void setFontType(){
		//TextViewインスタンス取得
		TextView[] titles = {
				(TextView)this.findViewById(R.id.itemTitle),
				(TextView)this.findViewById(R.id.priceTitle),
				(TextView)this.findViewById(R.id.pictureTitle)
		};
		
		//フォントファイル取得
		Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/APJapanesefont.ttf");
		
		//フォント適用
		for(TextView title:titles){
			title.setTypeface(tf);
		}
	}
}
