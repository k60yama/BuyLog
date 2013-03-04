package com.android.buylog;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class CreateLogStoreMapActivity extends MapActivity {
	private MapView mv;
	private MyLocationOverlay overLay;
	private EditText address;
	
	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		//現在入力されている住所取得
		String storeAddress = (this.address.getText().toString()).trim();	
		
		//未入力の場合、住所を自動補完する
		if("".equals(storeAddress)){		
			//現在地取得
			this.getMyLocation();
		}		
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルの指定
		this.setContentView(R.layout.createlog_store_input);
		
		//フォント設定
		this.setFontType();
		
		//MapViewの取得
		this.mv = (MapView)this.findViewById(R.id.storeMap);
		this.mv.setBuiltInZoomControls(true);	//ズームコントローラを有効化
		
		//MyLocationOverlayインスタンス生成
		overLay = new MyLocationOverlay(this, mv);
		
		//EditText取得
		this.address = (EditText)this.findViewById(R.id.address);		
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
	
	private void getMyLocation(){
		//GPSを使用する
		overLay.onProviderEnabled(LocationManager.GPS_PROVIDER);
		
		//現在地の追跡を有効化
		overLay.enableMyLocation();
		
		//現在位置を自動追跡する
		overLay.runOnFirstFix(new Runnable(){
			@Override
			public void run() {
				GeoPoint gp = overLay.getMyLocation();		//現在位置を取得
				getStoreAddress(gp);						//現在地から住所取得
				MapController mc = mv.getController();		//MapControllerインスタンス取得
				mc.setZoom(19);								//ズーム表示
				mc.animateTo(gp);							//地図移動
			}
		});
		//MapViewにMyLocationOverLayを追加
		mv.getOverlays().add(overLay);
	}
	
	private void getStoreAddress(GeoPoint gp){
		//初期化
		String addressTxt = "";
		double latitude = (double)(gp.getLatitudeE6() / 1E6);		//緯度取得
		double longitude = (double)(gp.getLongitudeE6() / 1E6);		//経度取得
		try{
			//Geocoderインスタンス生成
			Geocoder coder = new Geocoder(this.getApplicationContext(), Locale.JAPAN);
			List<Address> list_addr = coder.getFromLocation(latitude, longitude, 10);	//住所を取得
				
			//取得できた場合
			if(!list_addr.isEmpty()){
				for(int i=0; i<list_addr.size(); i++){
					//住所を取得
					Address addr = list_addr.get(i);
					
					//StringBufferインスタンス生成
					StringBuffer sb = new StringBuffer();
						
					//文字列を半角カンマで結合
					String s;
					for(int j=0; (s = addr.getAddressLine(j)) != null; j++){
						sb.append(s + ",");
					}
					addressTxt = sb.toString();		//String変換
						
					//住所取得チェック
					if(!("〒".equals(addressTxt.substring(0, 1)))){
						//ループを終了
						break;
					}
				}
					
				//取得した住所をキューに追加する
				Message msg = Message.obtain();
				msg.obj = addressTxt;
				handler.sendMessage(msg);
			}
		}catch(IOException e){
			Log.e("getStoreAddressError", "住所取得でエラーが発生しました。");
		}
		Log.d("住所", addressTxt);
	}
	
	//UIスレッドにメッセージを送信
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			address.setText((String)msg.obj);
		}
	};
	
	/*
	//ライフサイクル確認用メソッド
	private void showLifeCycle(String cycleName){
		Toast.makeText(this, "StoreMap" + cycleName, Toast.LENGTH_SHORT).show();
	}
	*/
}
