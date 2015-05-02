package com.cdms.codrive.classes;

import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

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
    public static List<ParseUser> persons=new ArrayList<>();
    public static List<String> personName = new ArrayList<>();
    public static List<Interaction> notification = new ArrayList<>();

    public static void saveLog(String dataObjectId,String keeperId,String ownerId) throws ParseException
    {
        Map<String, Object> params=new HashMap<>();
        params.put("dataObjectId",dataObjectId);
        params.put("ownerId",ownerId);
        params.put("keeperId",keeperId);
        ParseCloud.callFunction("AddStoreRequest", params);
    }

    public static void RespondToStoreRequest(Boolean isAccepted,String InteractionId) throws ParseException
    {
        Map<String, Object> params=new HashMap<>();
        params.put("isAccepted",isAccepted);
        params.put("serverStatusId",InteractionId);
        ParseCloud.callFunction("RespondToStoreRequest", params);
    }

    public static void getInteractions()
    {
        try {
            notification=Interaction.getServerStatus(Constants.user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
