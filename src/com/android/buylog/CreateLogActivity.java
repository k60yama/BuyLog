package com.android.buylog;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CreateLogActivity extends Activity{

	private Typeface tf;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//カスタムタイトルバーを使用
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		//レイアウト設定ファイルを指定
		this.setContentView(R.layout.createlog);
		
		//レイアウトセットアップ
		this.setUp();
	}
	
	
	public void finishActivity(View view){
		//アクティビティ終了
		this.finish();
	}
	
	
	private void setUp(){
		//タイトルセットアップ
		this.setTitle();
		
		//TextViewインスタンス取得
		TextView[] itemTitles = {
				(TextView)this.findViewById(R.id.itemTitle),
				(TextView)this.findViewById(R.id.priceTitle),
				(TextView)this.findViewById(R.id.storeTitle),
				(TextView)this.findViewById(R.id.pictureTitle)
		};
		
		//フォント設定
		for(TextView tv : itemTitles){
			tv.setTypeface(tf);
		}
		
		//フッター初期設定
		String title = this.getString(R.string.createLogTitle);
		String about = this.getString(R.string.create_log_about);
		this.setFooter(title, about);		
	}
	
	
	private void setTitle(){
		//カスタムタイトルバーを使用
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.createlog_title);
		
		//フォントファイル取得
		tf = Typeface.createFromAsset(this.getAssets(), "fonts/APJapanesefont.ttf");
		
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
	
	
	//商品名を押下した場合の処理
	private int itemTitleStatus = 0;	//展開省略フラグ
	public void itemTitle(View view){		
		//アイコン変更処理へ
		//処理結果を格納
		this.itemTitleStatus = this.titleIconChange((TextView)view, this.itemTitleStatus);
	}
	
	//値段を押下した場合の処理
	private int priceTitleStatus = 0;	//展開省略フラグ
	public void priceTitle(View view){		
		//アイコン変更処理へ
		//処理結果を格納
		this.priceTitleStatus = this.titleIconChange((TextView)view, this.priceTitleStatus);
	}	

	//ショップ情報を押下した場合の処理
	private int storeTitleStatus = 0;	//展開省略フラグ
	public void storeTitle(View view){		
		//アイコン変更処理へ
		//処理結果を格納
		this.storeTitleStatus = this.titleIconChange((TextView)view, this.storeTitleStatus);
	}	
	
	//商品イメージを押下した場合の処理
	private int pictureTitleStatus = 0;	//展開省略フラグ
	public void pictureTitle(View view){		
		//アイコン変更処理へ
		//処理結果を格納
		this.pictureTitleStatus = this.titleIconChange((TextView)view, this.pictureTitleStatus);
	}
	
	//アイコン変更処理
	public int titleIconChange(TextView view, int iconStatus){
		int drawableId;		//アイコンID
		if(iconStatus == 0){
			//展開用アイコン設定
			drawableId = R.drawable.createlog_pencil_on;
			iconStatus = 1;
		}else{
			//省略用アイコン設定
			drawableId = R.drawable.createlog_pencil_off;
			iconStatus = 0;
		}
		//アイコン設定
		view.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0);
		return iconStatus;
	}
}
