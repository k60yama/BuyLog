package com.android.buylog;

import com.google.android.maps.MapActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateLogActivity extends MapActivity{

	private Typeface tf;			//フォントファイル指定
	private LinearLayout llInfo;	//詳細情報
	private ImageButton question;	//クエスチョンボタン
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//カスタムタイトルバーを使用
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		//レイアウト設定ファイルを指定
		this.setContentView(R.layout.createlog);
		
		//初期設定
		this.setUp();
	}
	
	//戻るアイコンを押下した場合
	public void finishActivity(View view){
		//アクティビティ終了
		this.finish();
	}
	
	//初期設定
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
	
	//タイトル設定
	private void setTitle(){
		//カスタムタイトルバーを使用
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.createlog_title);
		
		//フォントファイル取得
		tf = Typeface.createFromAsset(this.getAssets(), "fonts/APJapanesefont.ttf");
		
		//TextViewインスタンスを取得
		TextView tv = (TextView)this.findViewById(R.id.title);
		tv.setTypeface(tf);
	}
	
	//フッター設定
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
	
	//クローズアイコンを押下した場合
	public void onClose(View view){
		//LinearLayoutインスタンス取得
		llInfo = (LinearLayout)this.findViewById(R.id.createlog_info);
		llInfo.setVisibility(View.GONE);	//非表示
		
		//ImageButtonインスタンス取得
		question = (ImageButton)this.findViewById(R.id.question);
		question.setVisibility(View.VISIBLE);	//表示
	}
	
	//クエスチョンアイコンを押下した場合
	public void onQuestion(View view){
		question.setVisibility(View.GONE);	//非表示
		llInfo.setVisibility(View.VISIBLE);	//表示
	}
	
	//商品名を押下した場合の処理
	private int itemTitleStatus = 0;
	public void itemTitle(View view){		
		//アイコン変更処理へ
		this.itemTitleStatus = this.titleIconChange((TextView)view, this.itemTitleStatus);
		
		//EditTextインスタンス取得
		EditText itemName = (EditText)this.findViewById(R.id.itemName);
		this.isVisibility(itemName);	//表示・非表示切り替え
	}
	
	//値段を押下した場合の処理
	private int priceTitleStatus = 0;
	public void priceTitle(View view){		
		//アイコン変更処理へ
		this.priceTitleStatus = this.titleIconChange((TextView)view, this.priceTitleStatus);
		
		//LinearLayoutインスタンス取得
		LinearLayout itemPrice = (LinearLayout)this.findViewById(R.id.itemPrice);
		this.isVisibility(itemPrice);	//表示・非表示切り替え
	}	

	//ショップ情報を押下した場合の処理
	private int storeTitleStatus = 0;
	public void storeTitle(View view){		
		//アイコン変更処理へ
		this.storeTitleStatus = this.titleIconChange((TextView)view, this.storeTitleStatus);
		
		//LinearLayoutインスタンス取得
		LinearLayout storeInfo = (LinearLayout)this.findViewById(R.id.storeInfo);
		this.isVisibility(storeInfo);	//表示・非表示切り替え		
	}	
	
	//商品イメージを押下した場合の処理
	private int pictureTitleStatus = 0;
	public void pictureTitle(View view){		
		//アイコン変更処理へ
		this.pictureTitleStatus = this.titleIconChange((TextView)view, this.pictureTitleStatus);

		//LinearLayoutインスタンス取得
		LinearLayout pictureInfo = (LinearLayout)this.findViewById(R.id.pictureInfo);
		this.isVisibility(pictureInfo);	//表示・非表示切り替え		
	}
	
	//アイコン変更処理
	private int titleIconChange(TextView view, int iconStatus){
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
	
	//表示・非表示切り替え
	private void isVisibility(View view){
		if(view.getVisibility() == View.VISIBLE){
			view.setVisibility(View.GONE);		//非表示
		}else{
			view.setVisibility(View.VISIBLE);	//表示
		}		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
