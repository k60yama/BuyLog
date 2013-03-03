package com.android.buylog;


import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateLogItemActivity extends Activity {

	//遷移先アクティビティコード
	private final static int RESULT_GALLERY = 0;
	
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
	
	//画像読み込み
	public void onGetPicture(View view){
		//Intentインスタンス生成
		Intent intent = new Intent();
		intent.setType("image/*");				//画像全般
		intent.setAction(Intent.ACTION_PICK);	//ギャラリーアプリを指定
		
		//ギャラリーアプリを起動
		this.startActivityForResult(intent, RESULT_GALLERY);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == RESULT_GALLERY && resultCode == RESULT_OK){		
			try{	
				InputStream in;
				BitmapFactory.Options opts;
				Bitmap img;
				
				//ファイルサイズを取得するために InputStream インスタンスを取得
				in = this.getContentResolver().openInputStream(data.getData());
				
				//BitmapFactory.Options()インスタンスを生成
				opts = new BitmapFactory.Options();
				
				//ファイルサイズを取得するために true を設定
				opts.inJustDecodeBounds = true;
				
				//画像サイズ取得
				img = BitmapFactory.decodeStream(in, null, opts);
				in.close();
				
				//ディスプレイ情報取得
				WindowManager wm = this.getWindowManager();		//WindowManagerインスタンス取得
				Display display = wm.getDefaultDisplay();		//Displayインスタンス取得		
				DisplayMetrics metrics = new DisplayMetrics();	//DisplayMetricsインスタンス取得
				display.getMetrics(metrics);
				
				//画像の大きさ変更
				int zoomWidth = (int)Math.floor(opts.outWidth / metrics.widthPixels);		//幅
				int zoomHeight = (int)Math.floor(opts.outHeight / metrics.heightPixels);	//高さ
				opts.inSampleSize = Math.max(zoomWidth, zoomHeight);						//縮小値
				
				//画像を読み込む
				opts.inJustDecodeBounds = false;
				in = this.getContentResolver().openInputStream(data.getData());
				img = BitmapFactory.decodeStream(in, null, opts);
				
				//InputStream を閉じる
				in.close();
				
				//ImageViewインスタンス取得
				ImageView itemPicture = (ImageView)this.findViewById(R.id.pictureImage);
				itemPicture.setImageBitmap(img);
				itemPicture.setVisibility(View.VISIBLE);
			}catch(Exception e){
			}
		}
	}
}
