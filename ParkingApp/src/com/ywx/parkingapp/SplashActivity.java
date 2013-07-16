package com.ywx.parkingapp;
   
import com.ywx.parkingapp.view.GestureDetectorViewFlipper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	
	private GestureDetectorViewFlipper viewFlipper = null;
	private GestureDetector mGestureDetector = null;
	
	private int currentPos = 0;
	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;
	
	Button login_btn = null;
	Button signin_btn = null;
	
	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		

        viewFlipper = (GestureDetectorViewFlipper)findViewById(R.id.viewflipper);
        mGestureDetector = new GestureDetector(new GDListener());
        viewFlipper.setGestureDetector(mGestureDetector);
        
        login_btn = (Button)findViewById(R.id.login);
        signin_btn = (Button)findViewById(R.id.signin);
         
        show_selected(currentPos);
        login_btn.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent login = new Intent(SplashActivity.this, LoginActivity.class);
				startActivityForResult(login, 0);
				//SplashActivity.this.finish();
			}
		}); 
		
        signin_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent signin = new Intent(SplashActivity.this, UserSignupActivity.class);
				startActivityForResult(signin, 0);
			}
		});
        
		/*try {
			sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory()))); 
		} catch (Exception e) {
		
		}*/
		 
        /*new Handler().postDelayed(new Thread() {
        	@Override
        	public void run() {
				Intent activity = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(activity);
				SplashActivity.this.finish();
        	}
        }, 3000);*/
	}
	
	///////////////////////////////////////////////////////////////////////////
	/*
	 * 
	 */
	private void showNextView(){
			
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));		
		viewFlipper.showNext();
		currentPos++;
		if(currentPos == viewFlipper.getChildCount()){
			currentPos = 0;
			show_selected(currentPos);
			show_noselected(viewFlipper.getChildCount()-1);
			//login_btn.setEnabled(true);
	        //signin_btn.setEnabled(true);
		}else{
			show_selected(currentPos);
			show_noselected(currentPos-1);
			//login_btn.setEnabled(true);
	        //signin_btn.setEnabled(true);
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
			//login_btn.setEnabled(true);
	        //signin_btn.setEnabled(true);
		}else{
			show_selected(currentPos);
			show_noselected(currentPos+1);
			//login_btn.setEnabled(true);
	        //signin_btn.setEnabled(true);
		}
	}
	
	/*private void showView(int curentpos, int nowpos){
		if(curentpos > nowpos){
			for(int i=0; i < curentpos-nowpos; i++){
				showPreviousView();
			}
			
		}else if(curentpos < nowpos){
			for(int i=0; i < nowpos - curentpos; i++){
				showNextView();
			}
		}
	}*/
		
	private void show_selected(int id){ 
		
		int[] ratioId = {R.id.radio_bar01, R.id.radio_bar02, R.id.radio_bar03,
				R.id.radio_bar04, R.id.radio_bar05};
		ImageView img = (ImageView)findViewById(ratioId[id]);
		img.setSelected(true);
	}
	
	private void show_noselected(int id){
		
		int[] ratioId = {R.id.radio_bar01, R.id.radio_bar02, R.id.radio_bar03,
				R.id.radio_bar04, R.id.radio_bar05};
		ImageView img = (ImageView)findViewById(ratioId[id]);
		img.setSelected(false);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	
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
	
	/*
	 * ImageView Listener
	 */
	/*private class imageviewListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			
			switch(v.getId()){
			case R.id.textviewID01:
				showView(currentPos, 0);
				break;
			case R.id.textviewID02:
				showView(currentPos, 1);
				break;
			case R.id.textviewID03:
				showView(currentPos, 2);
				break;
			case R.id.textviewID04:
				showView(currentPos, 3);
				break;
			case R.id.textviewID05:
				showView(currentPos, 4);
				break;
				
			}
			return false;
		}
		
	}*/

}