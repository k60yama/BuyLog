package com.android.buylog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.google.android.maps.MapActivity;

public class ConfirmMapActivity {

	private Context mCon;
	
	//コンストラクタ
	protected ConfirmMapActivity(Context con){
		this.mCon = con;
	}
	
	protected void showConfirmDialog(View view){
		//AlertDialog.Builderインスタンス生成
		AlertDialog.Builder confirm = new AlertDialog.Builder(this.mCon);
		confirm.setView(view);
		confirm.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		confirm.setNegativeButton("NG", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		confirm.show();
	}
}
