package com.ywx.parkingapp;
 
import com.ywx.parkingapp.view.GestureDetectorViewFlipper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;

public class UserProfile extends Activity{

	private GestureDetectorViewFlipper viewFlipper = null;
	private GestureDetector mGestureDetector = null;
	
	private int currentPos = 0;
	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_main);
		
		 viewFlipper = (GestureDetectorViewFlipper)findViewById(R.id.viewflipper1);
	     mGestureDetector = new GestureDetector(new GDListener());
	     viewFlipper.setGestureDetector(mGestureDetector);
	}
	

	/*
	 * 自定义OnGestureListener类
	 */
	private class GDListener implements  android.view.GestureDetector.OnGestureListener{

		@Override
		public boolean onDown(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			if (e1.getX() - e2.getX()> FLING_MIN_DISTANCE && 
					Math.abs(velocityX) > FLING_MIN_VELOCITY) {
				Log.e("fling", "left");
				//showNextView();
				return true;
			} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && 
					Math.abs(velocityX) > FLING_MIN_VELOCITY){
				Log.e("fling", "right");
				//showPreviousView();
				return true;
			}
			return false;
		}

		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}
 
	}

	
}
