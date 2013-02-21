package com.android.buylog;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CreateLogActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//カスタムタイトルバーを使用
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		//レイアウト設定ファイルを指定
		this.setContentView(R.layout.create_log);
		
		//タイトルセットアップ
		this.setTitle();
		
		//フッター初期設定
		String title = this.getString(R.string.createLogTitle);
		String about = this.getString(R.string.create_log_about);
		this.setFooter(title, about);
	}
	
	public void finishActivity(View view){
		//アクティビティ終了
		this.finish();
	}
	
	private void setTitle(){
		//カスタムタイトルバーを使用
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.create_log_title);
		
		//フォントファイル取得
		Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/APJapanesefont.ttf");
		
		//TextViewインスタンスを取得
		TextView tv = (TextView)this.findViewById(R.id.title);
		tv.setTypeface(tf);
	}
	
	private void setFooter(String aboutTitle, String aboutMsg){
		//TextViewインスタンス取得
		TextView tv;
		
		//フッタータイトル
		tv = (TextView)this.findViewById(R.id.function_name);
		tv.setText(aboutTitle);
		
		//機能説明
		tv = (TextView)this.findViewById(R.id.function_about);
		tv.setText(aboutMsg);
	}
}
