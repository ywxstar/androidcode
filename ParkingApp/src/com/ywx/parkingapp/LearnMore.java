package com.ywx.parkingapp;

import com.handmark.pulltorefresh.library.PullToRefreshWebView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LearnMore extends Activity{

	PullToRefreshWebView mPullRefreshWebView;
	WebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.learn_more);
		
		final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
        actionBar.setHomeAction(new IntentAction(this, createIntent(this), R.drawable.ic_title_home_demo));
        actionBar.setTitle("Login");

        final Action shareAction = new IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default);
        actionBar.addAction(shareAction); 
	    
        ///////////////////////////////////////////////////////////
        mPullRefreshWebView = (PullToRefreshWebView) findViewById(R.id.pull_refresh_webview);
		mWebView = mPullRefreshWebView.getRefreshableView();

		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new SampleWebViewClient());
		mWebView.loadUrl("http://www.baidu.com");
	}
	
	
	   public static Intent createIntent(Context context) {
	        
	    	Intent i = new Intent(context, LoginActivity.class);
	        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        return i;
	    }

	    private Intent createShareIntent() {
	    
	    	final Intent intent = new Intent(Intent.ACTION_SEND);
	        intent.setType("text/plain");
	        intent.putExtra(Intent.EXTRA_TEXT, "Shared from the ActionBar widget.");
	        return Intent.createChooser(intent, "Share");
	    }
	    
	    private static class SampleWebViewClient extends WebViewClient {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		}
	

}
