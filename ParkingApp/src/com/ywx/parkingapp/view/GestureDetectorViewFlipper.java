package com.ywx.parkingapp.view;
 
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class GestureDetectorViewFlipper extends ViewFlipper{

	GestureDetector mGestureDetector;
	
	public GestureDetectorViewFlipper(Context context) {
	super(context);
	// TODO Auto-generated constructor stub
	}
	
	public GestureDetectorViewFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public void setGestureDetector(GestureDetector myGestureDetector){
		this.mGestureDetector = myGestureDetector;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		mGestureDetector.onTouchEvent(ev);
		super.dispatchTouchEvent(ev);
		return true;
	}

}
