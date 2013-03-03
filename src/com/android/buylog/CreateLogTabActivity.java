package com.android.buylog;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class CreateLogTabActivity extends TabActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		//ActivityクラスのonCreate実行
		super.onCreate(savedInstanceState);
		
		//カスタムタイトルバーの使用
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		//レイアウト設定ファイルの指定
		this.setContentView(R.layout.createlog);
		
		//タイトルバー設定
		this.setTitle();
		
		//リソースを取得
		Resources res = this.getResources();
		
		//TabHostインスタンス取得
		TabHost tabHost = this.getTabHost();
		
		//商品情報タブ設定
		TabSpec itemTab = tabHost.newTabSpec("tab1");
		itemTab.setIndicator(new CustomTabContentView(
				this, res.getString(R.string.itemTab), R.drawable.createlog_item));
		itemTab.setContent(new Intent().setClass(this, CreateLogItemActivity.class));
		tabHost.addTab(itemTab);
		
		//ショップ情報タブ設定
		TabSpec shopTab = tabHost.newTabSpec("tab2");
		//shopTab.setIndicator(res.getString(R.string.shopTab), res.getDrawable(R.drawable.createlog_store));
		shopTab.setIndicator(new CustomTabContentView(
				this, res.getString(R.string.shopTab), R.drawable.createlog_store));
		shopTab.setContent(new Intent().setClass(this, CreateLogStoreMapActivity.class));
		tabHost.addTab(shopTab);
		
		//初期表示するタブ
		tabHost.setCurrentTab(0);
	}
	
	//タイトル設定
	private void setTitle(){
		//タイトル用レイアウト設定
		this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.createlog_title);
		
		//フォントファイル指定
		Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/APJapanesefont.ttf");
		
		//TextViewインスタンスを取得
		TextView tv = (TextView)this.findViewById(R.id.title);
		tv.setTypeface(tf);		
	}
	
	//アクティビティ終了
	public void finishActivity(View view){
		this.finish();
	}
	
	//保存処理
	public void onSave(View view){
		//現在タブタグを取得
		//String tagName = this.getTabHost().getCurrentTabTag();
		//Toast.makeText(this, tagName, Toast.LENGTH_SHORT).show();
		
		//Activity取得
		Activity activity = this.getLocalActivityManager().getActivity("tab2");
		String status;
		if(activity == null){
			status = "アクティビティ起動していない";
		}else{
			status = "アクティビティ起動中";
		}
		Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
	}
}
