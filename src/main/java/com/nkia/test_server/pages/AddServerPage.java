package com.nkia.test_server.pages;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.hibernate.Session;

import com.nkia.test_core.common.JsonStreamResponse;
import com.nkia.test_server.entities.Server;


public class AddServerPage {


	
	@Inject
	private Session session;

	/*
	 * Add Servers
	 */
	@CommitAfter
	public StreamResponse onAddServers() {
		 	
		System.out.println("====================================>onAddServers");
		
		JSONObject myData = new JSONObject();
		try{
			
			
			for(int i=0; i < 1000 ;i++){

				Server server = new Server();
				server.name = Integer.toString(i);
				server.hostname = Integer.toString(i);
				server.ipAddress = Integer.toString(i);
				server.description = Integer.toString(i);

				
				session.persist(server);
			}
	
			myData.put("result", "success");
			myData.put("message", "Append Server");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}


	/*
	 * Delete Servers
	 */
	@CommitAfter
	public StreamResponse onDeleteServers() {		 	
		
		JSONObject myData = new JSONObject();
		Server server = new Server();
		try{
			
			
			for(int i=0; i < 1000 ;i++){

				server = (Server)session.load(Server.class, Long.parseLong(Integer.toString(i)));	
				try{
				if(server != null)session.delete(server);
				}catch(Exception e){
					
				}
			}
	
			myData.put("result", "success");
			myData.put("message", "Append Server");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}
}
 