package com.nkia.test_server.pages;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nkia.test_core.common.JsonStreamResponse;

@Import(stack="ExtJSStack" )
public class Network
{

    public String[] getPageNames()
    {
        return new String[]{"Server"};
    }


	@Autowired
	private SessionFactory sessionFactory;

	@Inject
	private Request request;

	@Inject
	private AlertManager alertManager;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private String message;

	@Inject
	private RequestGlobals requestGlobals;

	StreamResponse onAddNetwork() {
		System.out.println("====================================>onAddNetwork");
		
		JSONObject myData = new JSONObject();
		try{
	
			myData.put("result", "Add Netowrk");
			
		}catch(Exception e){
	
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}

	StreamResponse onListNetwork() {
		System.out.println("====================================>onListNetwork");
		
		JSONObject myData = new JSONObject();
		try{
	
			myData.put("result", "Network List");
			
		}catch(Exception e){
	
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}

	StreamResponse onViewNetwork() {
		System.out.println("====================================>onViewNetwork");
		
		JSONObject myData = new JSONObject();
		try{
	
			myData.put("result", "Network View");
			
		}catch(Exception e){
	
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}
}
