package com.cdms.codrive;

import com.parse.Parse;
import com.parse.ParseCrashReporting;

import android.app.Application;

public class MyApplication extends Application
{
  @Override
  public void onCreate()
  {
	  ParseCrashReporting.enable(this);
	Parse.initialize(this, "HZrXCS46YpLltwRYXUG4YS5N8msZFvZTP5z6KdXE", "cNHwwsUrctpFw54Bd5hSaEo61UyCiI7zgWvdtTgl");
	  
    super.onCreate();
  }
}
