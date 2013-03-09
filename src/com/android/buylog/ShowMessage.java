package com.android.buylog;


import android.content.Context;
import android.widget.Toast;

public class ShowMessage {
	//アクティビティ保持
	private Context mContext;
	
	//コンストラクタ
	protected ShowMessage(Context con){
		this.mContext = con;
	}
	
	//トースト用メッセージ
	protected void showToastMsg(String msg){
		Toast toast = Toast.makeText(this.mContext, msg, Toast.LENGTH_LONG);
		toast.show();
	}
}
