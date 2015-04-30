package com.cdms.codrive.Login;

import java.io.Console;

import com.cdms.codrive.R;
import com.cdms.codrive.classes.Constants;
import com.cdms.codrive.classes.User;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class LoginActivity extends Activity 
{
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        new BgTask().execute();
	}
	
	public class BgTask extends AsyncTask<Void, Void, Void> 
	{

	    @Override
	    protected void onPreExecute()
	    {
	        super.onPreExecute();
	    }

	    @Override
	    protected Void doInBackground(Void... params)
	    {
	        ParseUser user=checkAndGetUser();
	        User newuser=new User();
	        newuser.setParseUser(user);
	        Constants.user=newuser;
	        
	        return null;
	    }
	    @Override
	    protected void onPostExecute(Void result)
	    {
	    	Intent i=new Intent("com.cdms.codrive.MainActivity");
	    	startActivity(i);
	    }
	}
	private ParseUser checkAndGetUser()
	{
		//fetch googleId
		String googleId = "";
		try 
		{
			Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");
			googleId+=accounts[0].name;
		} 
		catch (Exception e) 
		{
			
		}
		
		//ParseUser.logOut();
		ParseUser user= ParseUser.getCurrentUser();
//		Toast.makeText(this, user.toString(), 0).show();
		if(user!=null)
		{
			//Log.d("asdf","user not null");
			if(user.getEmail()==null || user.getString("phone")==null)
			{
				if(user.getEmail()==null && googleId!=null)
					user.setEmail(googleId);
				try {
					user.save();
				} 
				catch (ParseException e) 
				{	
					
				}
			}
			//Log.d("asdf","1");
			return user;
		}
		
		//check existence
		try 
		{
			//Log.d("asdf","starting sign in");
			user = ParseUser.logIn(googleId, "password");
			//Log.d("asdf","sign in done");
			if(user.getEmail()==null)
			{
				if(user.getEmail()==null && googleId!=null)
					user.setEmail(googleId);
				try {
					user.save();
				} catch (ParseException e) {	}
			}
			//Log.d("asdf","2");
			return user;
		} 
		catch (ParseException e1) {
			String debugStr="";
			try{
				user = new ParseUser();
				user.setUsername(googleId);
				user.setEmail(googleId);
				user.setPassword("password");
				user.signUp(); debugStr+="2";
				return user;
			}
			catch(Exception excep)
			{
			}
		}
		
		return null;
	}
	
}


