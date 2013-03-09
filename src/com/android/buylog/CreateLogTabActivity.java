package com.android.buylog;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class CreateLogTabActivity extends TabActivity {
	
	private ValuesCheck chk;
	private ShowMessage sMsg;
	
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
		
		//ValuesChkインスタンス生成
		this.chk = new ValuesCheck();
		this.sMsg = new ShowMessage(this);
		
		//リソースを取得
		Resources res = this.getResources();
		
		//TabHostインスタンス取得
		TabHost tabHost = this.getTabHost();
		
		//商品情報タブ設定
		TabSpec itemTab = tabHost.newTabSpec("item");
		itemTab.setIndicator(new CustomTabContentView(
				this, res.getString(R.string.itemTab), R.drawable.tab_item_stateful));
		itemTab.setContent(new Intent().setClass(this, CreateLogItemActivity.class));
		tabHost.addTab(itemTab);
		
		//ショップ情報タブ設定
		TabSpec shopTab = tabHost.newTabSpec("shop");
		shopTab.setIndicator(new CustomTabContentView(
				this, res.getString(R.string.shopTab), R.drawable.tab_shop_stateful));
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
		//CreateLogStoreMapActivityインスタンスを取得
		//アクティビティが存在していない場合は強制終了
		CreateLogStoreMapActivity storeActivity = 
				(CreateLogStoreMapActivity)this.getLocalActivityManager().getActivity("shop");
		if(!this.chk.storeActivityChk(storeActivity)){
			this.sMsg.showToastMsg("ショップ情報を入力してください。");
			return ;
		}
		
		//ショップ名を取得
		//ショップ名が未入力の場合は強制終了
		EditText sName = (EditText)storeActivity.findViewById(R.id.storeName);
		if(!this.chk.inputItemChk(sName)){
			this.sMsg.showToastMsg("ショップ名を入力してください。");
			return ;
		}else{
			String sNameStr = sName.getText().toString();
		}
		
		//アドレスを取得
		//アドレスが未入力の場合は強制終了
		EditText sAddr = (EditText)storeActivity.findViewById(R.id.address);
		if(!this.chk.inputItemChk(sAddr)){
			this.sMsg.showToastMsg("ショップのアドレスを入力してください。");
			return ;
		}else{
			String sAddrStr = sAddr.getText().toString();
		}
		
		//CreateLogItemActivityインスタンスを取得
		CreateLogItemActivity itemActivity = 
				(CreateLogItemActivity)this.getLocalActivityManager().getActivity("item");
		
		//商品名を取得
		//商品名が未入力の場合は強制終了
		EditText iName = (EditText)itemActivity.findViewById(R.id.itemName);
		if(!this.chk.inputItemChk(iName)){
			this.sMsg.showToastMsg("商品名を入力してください。");
			return ;
		}else{
			String iNameStr = iName.getText().toString();
		}
		
		//値段を取得
		//値段が未入力の場合は強制終了
		EditText iPrice = (EditText)itemActivity.findViewById(R.id.itemPrice);
		if(!this.chk.inputItemChk(iPrice)){
			this.sMsg.showToastMsg("商品の値段を入力してください。");
			return ;
		}else{
			String iPriceStr = iPrice.getText().toString();
		}
		
		//画像を取得
		ImageView iImage = (ImageView)itemActivity.findViewById(R.id.pictureImage);
		BitmapDrawable itemBitmapDraw = (BitmapDrawable)iImage.getDrawable();	//BitmapDrawableキャストして画像を取得
		Bitmap itemBitmap = itemBitmapDraw.getBitmap();							//ビットマップファイルを取り出す
		
		//ConfirmMapActivityインスタンス生成;
		ConfirmMapActivity cm = new ConfirmMapActivity(this);
		
		//LayoutInflaterインスタンスを生成
		LayoutInflater inflater = getLayoutInflater();
		View v = inflater.inflate(R.layout.createlog_confirm_dialog, null);
		cm.showConfirmDialog(v);
		
		
		/*ここからテスト
		ImageView iv = new ImageView(this);
		iv.setImageBitmap(itemBitmap);
			
		Toast toast = new Toast(this);
		toast.setView(iv);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();*/
		//ここまでテスト
	}
}
