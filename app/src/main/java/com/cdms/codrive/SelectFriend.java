package com.cdms.codrive;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cdms.codrive.classes.Interaction;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectFriend extends Activity 
{

	ListView listview;
	List<ParseObject> persons=new ArrayList<>();
	List<String> personName = new ArrayList<>();
	String filePath, responseString;
	ParseObject gameScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_friend);
		listview=(ListView)findViewById(R.id.listview);
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
	    	ParseQuery<ParseObject> queryUsers=new ParseQuery<>("_User");
	    	try 
	    	{
				persons=queryUsers.find();
				for(ParseObject po:persons)
		    	{
		    		personName.add(po.getString("username"));
		    	}
			} 
	    	catch (ParseException e) 
			{
				e.printStackTrace();
			}
	    	Log.d("values1",personName.toString());
	        return null;
	    }
	    @Override
	    protected void onPostExecute(Void result)
	    {
	    	
		      String[] values = personName.toArray(new String[personName.size()]);
		      Log.d("values",values.toString());
		      ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectFriend.this,android.R.layout.simple_list_item_1, android.R.id.text1, values);
		      listview.setAdapter(adapter); 

		      listview.setOnItemClickListener(new OnItemClickListener() 
		      {
	 
	                  @Override
	                  public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
	                  {
	                   int itemPosition     = position;
	                   String  itemValue    = (String) listview.getItemAtPosition(position);
	                    Toast.makeText(getApplicationContext(),
	                      "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
	                      .show();
	                    showFileChooser();
	                    
	                 
	                  }
	    
		      });
	    }
	}
	
	private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) 
                {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    // Get the path
                    String path = uri.getPath();//FileUtils.getPath(this, uri);
                    filePath = path;
                    // Get the file instance
                     File file = new File(path);
                     Toast.makeText(this,file.getName()+","+file.getPath(),
                             Toast.LENGTH_SHORT).show();
                     uploadFile(file);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    public void uploadFile(File f)
    {
    	
    	Interaction i=new Interaction();
//    	i.setFile(f.getAbsolutePath(), contentResolver);
//		String nm;
//		InputStream inputStream = null;
//		try 
//		{
//			inputStream = new FileInputStream(f);
//		} 
//		catch (Exception e) 
//		{
//			
//		}
//
//		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
//	    int bufferSize = 1024;
//	    byte[] buffer = new byte[bufferSize];
//	    int len = 0;
//	    try 
//	    {
//	    	while ((len = inputStream.read(buffer)) != -1) 
//	    	{
//	    		byteBuffer.write(buffer, 0, len);
//	    	}
//	    } 
//	    catch (Exception e) 
//        {
//  	  
//        }
//        byte[] bb=byteBuffer.toByteArray();
//        ParseFile parsefile = new ParseFile(f.getName(),bb);
//        gameScore = new ParseObject("Data");
//        gameScore.put("file",parsefile);
//        gameScore.put("name",f.getName());
//        int file_size = Integer.parseInt(String.valueOf(f.length()/1024));
//        gameScore.put("size",file_size);
//        gameScore.saveInBackground(new SaveCallback() 
//        {
//			
//			@Override
//			public void done(ParseException e) 
//			{
//				Toast.makeText(SelectFriend.this,gameScore.getObjectId(), Toast.LENGTH_LONG).show();
//				
//			}
//		});
//        
        
        
        
    }
}
