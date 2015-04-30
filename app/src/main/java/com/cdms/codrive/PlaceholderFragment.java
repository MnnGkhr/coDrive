package com.cdms.codrive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment
{
    @Override
   	public void onActivityCreated(Bundle savedInstanceState) 
    {
   		super.onActivityCreated(savedInstanceState);
   		Button add =(Button)getActivity().findViewById(R.id.composebutton);
   		add.setOnClickListener(new View.OnClickListener() 
   		{
			
			@Override
			public void onClick(View v) 
			{
				Intent i=new Intent("com.cdms.codrive.SelectFriend");
				startActivity(i);
			}
		});
   	}

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
	 
}