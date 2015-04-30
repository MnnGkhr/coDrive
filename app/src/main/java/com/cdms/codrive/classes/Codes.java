package com.cdms.codrive.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;

import com.cdms.codrive.classes.Interaction.MyData;
import com.parse.ParseException;
import com.parse.ParseFile;

/**
 * 
 * Dummy codes to help deal with the things
 * 
 * @author mg
 *
 */
public class Codes {
	
	public static final String SEPARATOR = "1";
	public static final String FOLDER = Environment.getDataDirectory().getAbsolutePath();
	
	public void storeUserData(User fromUser, MyData data)
	{
		File f = new File(FOLDER,this.getFileNameForData(fromUser, data));
	}
	
	public String getFileNameForData(User fromUser, MyData data)
	{
		String fileName="";
		fileName+=fromUser.parseUser.getObjectId();
		fileName+=SEPARATOR;
		fileName+=data.getName();
		return fileName;
	}
	
	//TODO change data types according to need
	public void storeFile(MyData data, File f) throws ParseException, IOException
	{
		ParseFile parseFile = data.getParseFile();
		byte[] bytes = parseFile.getData();
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(bytes);
		fos.close();
	}
	
	//TODO upload file to temp via Interaction.setFile function()

}
