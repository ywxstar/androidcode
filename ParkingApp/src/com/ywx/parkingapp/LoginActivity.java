package com.ywx.parkingapp;
 
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	 
	EditText uname;
	EditText passwd;
	Button submit; 
	String response;

	//String deviceId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		/*if(!Utils.isNetworkAvailable(LoginActivity.this)){
			Utils.showToast("Not Connected!", this);
		}*/

        //deviceId = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        //sLog.i("dev id",deviceId);		
		
		uname = (EditText) findViewById(R.id.etusername);
		passwd = (EditText) findViewById(R.id.etpassword);
		submit = (Button) findViewById(R.id.blogin); 
		 
		submit.setOnClickListener( new OnClickListener(){
			@Override
			public void onClick(View v){
				String username = uname.getText().toString();
				String password = passwd.getText().toString();
				
				if(username.equals("") == true && password.equals("") == true){
					Toast.makeText(LoginActivity.this, "please input user name and user password!", 1000).show();
				}else if(username.equals("") == true){
					Toast.makeText(LoginActivity.this, "please input user name!", 1000).show();
				}else if(password.equals("") == true){
					Toast.makeText(LoginActivity.this, "please input user password!", 1000).show();
				}else{
					new LoginTask().execute();
				}
				
				
			}
		});
	}
		
	
	public void ToLearnMore(View view){
		Intent intent = new Intent(LoginActivity.this, LearnMore.class);
		startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	class LoginTask extends AsyncTask<String, Void, String>{
 
		private ProgressDialog pDialog;
		/*
		 * step 1
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pDialog = new ProgressDialog(LoginActivity.this);
			pDialog.setCancelable(false);
			pDialog.setProgressStyle(0);
			pDialog.setMessage("Please Wait");
			pDialog.show();
		}
		
		/*
		 *  step 2
		 */
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String resp = trylogin();
			//Toast.makeText(LoginActivity.this, resp, 1000).show();
			return resp;
		}

		/*
		 * step 3
		 */
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		/*
		 * step 4
		 */
		@Override
		protected void onPostExecute(String response) {
			// TODO Auto-generated method stub 
			super.onPostExecute(response);
			
			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}
			
			Toast.makeText(LoginActivity.this, response, 1000).show();
			
			if(response.compareTo("-1") == 0){
				
				Toast.makeText(LoginActivity.this, "Unknown Error Occured, exiting", 1000).show();
			}else if(response.compareTo("1") == 0){
			
				//Intent i = new Intent(LoginActivity.this,)
			}else{
				
				Toast.makeText(LoginActivity.this, "Invalid Username/Password", 1000).show();
				//Utils.showToast("Invalid Username/Password", getApplicationContext());
			}
		}

		String trylogin(){
			
			String user_name = uname.getText().toString();
	        String password= passwd.getText().toString();    
	        
	        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
	        /*nvps.add(new BasicNameValuePair("method", "signIn"));
	        nvps.add(new BasicNameValuePair("api-key", "NGNkZGRhYjkzY2Z"));
	        nvps.add(new BasicNameValuePair("timestamp",String.valueOf(ts)));*/
	        nvps.add(new BasicNameValuePair("username", user_name));//"pyee"));
	        nvps.add(new BasicNameValuePair("password", password));//"rocyeahyepen"));
	       /* nvps.add(new BasicNameValuePair("api-token",s));
	        nvps.add(new BasicNameValuePair("device-uuid",deviceId));*/
			
	        try{
	        	 
	        	
	        	HttpClient client = new DefaultHttpClient();  
		        user_name = URLEncoder.encode(user_name, "utf-8");
		        password = URLEncoder.encode(password, "utf-8");
		        Resources r = getResources();
			   /* String getURL = r.getString(R.string.root_url) + "login_user.php" + 
			    		"?uname=" + user_name+ "&pwd=" + password;*/
		        String getURL = "http://192.168.0.2:8080/sessionsMobile";
			    HttpPost post = new HttpPost(getURL);
			    UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(nvps,HTTP.UTF_8);
			    post.setEntity(p_entity);
			    HttpResponse responseGet = client.execute(post);  
			   
			    ///////////////////
			    HttpEntity resEntityGet = responseGet.getEntity();  
			    if (resEntityGet != null) {  
			    	response = EntityUtils.toString(resEntityGet);
			    	System.out.println(response);
			    }
			    
		    } catch(Exception e){
		    	e.printStackTrace();
	        	response = "-1";
	        }
			
			return response;
		}
		 
		/*private String getSha(String in) {
			
			String converted = null;
			 try{ 
				 MessageDigest md = MessageDigest.getInstance("SHA1");
		         System.out.println("Message digest object info: ");
		         System.out.println("   Algorithm = "+md.getAlgorithm());
		         System.out.println("   Provider = "+md.getProvider());
		         System.out.println("   toString = "+md.toString());
		         
		         md.update(in.getBytes()); 
		      	 byte[] output = md.digest();
		         System.out.println();
		         System.out.println("SHA1(\""+in+"\") =");
		         System.out.println("   "+bytesToHex(output));
		         converted = bytesToHex(output);
		         Log.i("Con",converted);
			 }catch (Exception e) {
	             System.out.println("Exception: "+e);
			 }
			 return converted;
		 }	*/	
		
	/*	 public String bytesToHex(byte[] b) {
		      
			 char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
		                         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		      StringBuffer buf = new StringBuffer();
		      for (int j=0; j<b.length; j++) {
		      
		    	  buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
		          buf.append(hexDigit[b[j] & 0x0f]);
		      }
		      return buf.toString();
		 }
		 */
		 
		/* public void tryLogin(String u,String pass,String s){
			 
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		  		//imm.hideSoftInputFromWindow(this.pass.getWindowToken(), 0);
		  		
				DefaultHttpClient client = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("192.168.0.2");//http://api.ticketline.co.uk//user");
		            
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		        nvps.add(new BasicNameValuePair("method", "signIn"));
		        nvps.add(new BasicNameValuePair("api-key", "NGNkZGRhYjkzY2Z"));
		        nvps.add(new BasicNameValuePair("email",u));
		        nvps.add(new BasicNameValuePair("password",pass));
		        nvps.add(new BasicNameValuePair("api-token",s));
		        nvps.add(new BasicNameValuePair("device-uuid",deviceId));
			        
				Log.e("Username",u);
				Log.e("Password",pass);
		        try {
		        	UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(nvps,HTTP.UTF_8);
		            httppost.setEntity(p_entity);
		            Log.v("t", nvps.toString());	              
		            HttpResponse response = client.execute(httppost);
		              			              
		            Log.v("t", response.getStatusLine().toString());			              
		            ByteArrayOutputStream ostream = new ByteArrayOutputStream();			              
		            response.getEntity().writeTo(ostream);			          
		            Log.e("HTTP CLIENT", ostream.toString());
		              
		            JSONObject jArray = new JSONObject( ostream.toString());
		            JSONArray  earthquakes = jArray.getJSONArray("categories");
			        	
		      	    JSONObject e = earthquakes.getJSONObject(0);
				    Log.i("Login handle",e.toString());
				    Log.i("id",e.getString("id"));
				    Log.i("email",e.getString("email_address"));	 		

		        	
		        	Intent PR = new Intent(getParent(),UserProfile.class);
					PR.putExtra("username",e.getString("email_address") );
					PR.putExtra("fname",e.getString("first_name") );
					PR.putExtra("lname",e.getString("last_name") );
					PR.putExtra("add1",e.getString("address1") );
					PR.putExtra("add2",e.getString("address2") );
					PR.putExtra("city",e.getString("city") );
					PR.putExtra("post",e.getString("postal_code") );
					PR.putExtra("country",e.getString("country") );
					PR.putExtra("phone",e.getString("phone") );
					PR.putExtra("json",us );
                    
	        	}catch (Exception e) {			        		
	        	  e.printStackTrace();
	              File newxmlfile = new File(Environment.getExternalStorageDirectory()+"/user.xml");
	      	      newxmlfile.delete();			        	
	        	} 
			 }*/ 
		 
	}
}
