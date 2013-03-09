package com.android.buylog;

import android.graphics.drawable.BitmapDrawable;
import android.widget.EditText;

public class ValuesCheck {
	
	//CreateLogStoreMapActivityインスタンスチェック
	protected boolean storeActivityChk(CreateLogStoreMapActivity sActivity){
		if(sActivity == null){
			return false;
		}
		return true;
	}
	
	//必須入力チェック
	protected boolean inputItemChk(EditText itemName){
		//テキスト取得
		String itemNameStr = (itemName.getText().toString()).trim();
		if("".equals(itemNameStr)){
			return false;
		}
		return true;
	}
	
	//画像チェック
	protected boolean inputImageChk(BitmapDrawable itemBitmap){
		//画像取得
		if(itemBitmap == null){
			return false;
		}
		return true;
	}
}
