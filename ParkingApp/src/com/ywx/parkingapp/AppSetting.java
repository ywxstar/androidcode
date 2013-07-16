package com.ywx.parkingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.TextView;

public class AppSetting extends Activity{
 
	TextView about_btn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		 
		about_btn = (TextView)findViewById(R.id.about);
		about_btn.setOnTouchListener(new OnTouchListener() { 
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AppSetting.this, AboutActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
				return false;
			}
		});
	} 
	
	/*public void startAbout(View view){
		
		Intent intent = new Intent(AppSetting.this, AboutActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}*/
	
}
