package com.ywx.parkingapp;
 
import com.ywx.parkingapp.view.GestureDetectorViewFlipper;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends Activity{

	private GestureDetectorViewFlipper viewFlipper = null;
	private GestureDetector mGestureDetector = null;
	
	private int currentPos = 0;
	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;
	
	//=================================================
	//userprofile
	//=================================================
	//private ImageView user_imge = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_main);
		
		 viewFlipper = (GestureDetectorViewFlipper)findViewById(R.id.viewflipper1);
	     mGestureDetector = new GestureDetector(new GDListener());
	     viewFlipper.setGestureDetector(mGestureDetector); 
	     show_selected(currentPos);
	     
	     //user_imge = (ImageView)findViewById(R.id.user_image);
	}
	

	public void ChangeImage(View view){
		Toast.makeText(UserProfile.this, "Change Image", 1000).show();
	}
 
	

    ///////////////////////////////////////////////////////////////////////////////////////////
	private void show_selected(int id){ 
		
		int[] ratioId = {R.id.user_search_bg, R.id.user_status_bg, R.id.user_profile_bg};
		ImageView image = (ImageView)findViewById(ratioId[id]);
		image.setSelected(true);
	}
	
	private void show_noselected(int id){
		
		int[] ratioId = {R.id.user_search_bg, R.id.user_status_bg, R.id.user_profile_bg};
		ImageView image = (ImageView)findViewById(ratioId[id]);
		image.setSelected(false);;
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
				showNextView();
				return true;
			} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && 
					Math.abs(velocityX) > FLING_MIN_VELOCITY){
				Log.e("fling", "right");
				showPreviousView();
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
	
	private void showNextView(){
		
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));		
		viewFlipper.showNext();
		currentPos++;
		if(currentPos == viewFlipper.getChildCount()){
			currentPos = 0;
			show_selected(currentPos);
			show_noselected(viewFlipper.getChildCount()-1); 
		}else{
			show_selected(currentPos);
			show_noselected(currentPos-1); 
		}
	}
	
	private void showPreviousView(){
		
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
		viewFlipper.showPrevious();
		
		currentPos--;
		if(currentPos == -1){
			currentPos = viewFlipper.getChildCount()-1;
			show_selected(currentPos);
			show_noselected(0); 
		}else{
			show_selected(currentPos);
			show_noselected(currentPos+1); 
		}
	}

	
}
