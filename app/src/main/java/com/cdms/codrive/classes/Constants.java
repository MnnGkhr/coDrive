package com.cdms.codrive.classes;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants
{
	public static User user;
	public static User ownerUser;
	public static User keeperUser;
    public static ParseObject Friend;
    public static List<ParseObject> persons=new ArrayList<>();
    public static List<String> personName = new ArrayList<>();

    public static void saveLog(String dataObjectId,String keeperId,String ownerId) throws ParseException
    {
        Map<String, Object> params=new HashMap<>();
        params.put("dataObjectId",dataObjectId);
        params.put("ownerId",keeperId);
        params.put("keeperId",ownerId);
        Map response= ParseCloud.callFunction("AddRequest", params);
    }
}
