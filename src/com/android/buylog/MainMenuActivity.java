package com.android.buylog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends Activity implements View.OnFocusChangeListener{

	TextView functionName;
	TextView functionAbout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//ActivityクラスのonCreateを実行
        super.onCreate(savedInstanceState);
        
        //タイトルバーを使用しない
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        //レイアウト設定ファイルを指定
        setContentView(R.layout.main_menu);
        
        //フォント設定
        this.setFontType();
        
        //フッターに表示するTextViewを取得
        functionName = (TextView)this.findViewById(R.id.function_name);
        functionAbout = (TextView)this.findViewById(R.id.function_about);
        
        //LinearLayoutを取得し、フォーカスイベントを設定
        LinearLayout ll1 = (LinearLayout)this.findViewById(R.id.moveNewLog);
        ll1.setOnFocusChangeListener(this);
        
        //LinearLayoutを取得し、フォーカスイベントを設定
        LinearLayout ll2 = (LinearLayout)this.findViewById(R.id.moveShowLog);
        ll2.setOnFocusChangeListener(this);
    }

    public void moveNewLog(View view){
    	//Toast.makeText(MainMenuActivity.this, "次のアクティビティへ：moveNewLog", Toast.LENGTH_SHORT).show();
    	//Intentインスタンス生成
    	Intent intent = new Intent(this, CreateLogTabActivity.class);
    	
    	//アクティビティ起動
    	this.startActivity(intent);
    }
    
    public void moveShowLog(View view){
     	Toast.makeText(MainMenuActivity.this, "次のアクティビティへ：moveShowLog", Toast.LENGTH_SHORT).show();
    }

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		//フォーカス時のみ処理をする
		if(hasFocus){
			switch(v.getId()){
			case R.id.moveNewLog:
				functionName.setText(R.string.new_log);
				functionAbout.setText(R.string.new_log_about);
				break;
			case R.id.moveShowLog:
				functionName.setText(R.string.show_log);
				functionAbout.setText(R.string.show_log_about);
				break;
			}
		}
	}
	
	private void setFontType(){
		Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/APJapanesefont.ttf");
		
		TextView tv;
		
		//タイトル
		tv = (TextView)this.findViewById(R.id.title);
		tv.setTypeface(tf);
		
		//ログを記録
		tv = (TextView)this.findViewById(R.id.newLog);
		tv.setTypeface(tf);
		
		//ログを参照
		tv = (TextView)this.findViewById(R.id.showLog);
		tv.setTypeface(tf);
	}
}

