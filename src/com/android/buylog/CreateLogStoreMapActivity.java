package com.android.buylog;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.content.Context;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CreateLogStoreMapActivity extends MapActivity {

/*	
	@Override
	protected void onRestart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onRestart();
		this.showLifeCycle(": onRestart");
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		this.showLifeCycle(": onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
		this.showLifeCycle(": onPause");
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		this.showLifeCycle(": onResume");
	}

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
		this.showLifeCycle(": onStart");
	}

	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
		this.showLifeCycle(": onStop");
	}
*/

	@Override
	public void onCreate(Bundle savedInstanceState){
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルの指定
		this.setContentView(R.layout.createlog_store_input);
		
		//フォント設定
		this.setFontType();
		//this.showLifeCycle(": onCreate");
		
		//現在地取得
		this.getMyLocal();
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
		return false;
	}
	
	private void getMyLocal(){
		//MapViewの取得
		MapView mv = (MapView)this.findViewById(R.id.storeMap);
		
		//LocationManagerインスタンスの取得
		LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		//GPSから現在地の情報を取得
		Location myLocate = lm.getLastKnownLocation("gps");
		
		//MapControllerインスタンスの取得
		MapController mc = mv.getController();

		//現在地取得チェック
		if(myLocate != null){
			//取得できた場合
			//緯度の取得
			int latitude = (int)(myLocate.getLatitude() * 1e6);
			
			//経度の取得
			int longitude = (int)(myLocate.getLongitude() * 1e6);
			
			//GeoPointに緯度・経度を設定
			GeoPoint GP = new GeoPoint(latitude, longitude);
			
			//現在地までアニメーションで移動
			mc.animateTo(GP);
		}else{
			//取得できない場合
			Toast.makeText(this, "現在地が取得できません。", Toast.LENGTH_SHORT).show();
		}
	}
	
	/*
	//ライフサイクル確認用メソッド
	private void showLifeCycle(String cycleName){
		Toast.makeText(this, "StoreMap" + cycleName, Toast.LENGTH_SHORT).show();
	}
	*/
}
