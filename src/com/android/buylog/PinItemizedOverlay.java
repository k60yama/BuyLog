package com.android.buylog;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;

public class PinItemizedOverlay extends ItemizedOverlay<PinOverlayItem> {
	
	private List<GeoPoint> points = new ArrayList<GeoPoint>();

	public PinItemizedOverlay(Drawable defaultMarker){
		//ピン画像を下部中央に設定する
		super(boundCenterBottom(defaultMarker));
	}

	@Override
	protected PinOverlayItem createItem(int i) {
		GeoPoint point = this.points.get(i);
		return new PinOverlayItem(point);
	}

	@Override
	public int size() {
		return this.points.size();
	}
	
	public void addPoint(GeoPoint point){
		//描画を追加する
		this.points.add(point);
		this.populate();		//オーバーレイをMapViewに貼り直し
	}
	
	public void clearPoint(){
		//描画を消去する
		this.points.clear();
		this.populate();		//オーバーレイをMapViewに貼り直し
	}
}
