package com.nkia.test_server.components;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.hibernate.Session;

import com.nkia.test_core.common.JsonStreamResponse;
import com.nkia.test_server.base.ServerComponent;
import com.nkia.test_server.entities.Server;

@Import(stack = "ExtJSStack")
public class AddServer extends ServerComponent {
	
	@Inject
	private Session session;

	@Inject
	private Request request;


	/*
	 * Add Server
	 */
	@CommitAfter
	public StreamResponse onAddServer() {
		 	
		System.out.println("====================================>onAdd");
		
		JSONObject myData = new JSONObject();
		Server server = new Server();
		try{
			
			server.name = request.getParameter("name");
			server.hostname = request.getParameter("hostname");
			server.ipAddress = request.getParameter("ipAddress");
			server.description = request.getParameter("description");

			System.out.println("server====================================>" + server.toString());
			
			session.persist(server);
	
			myData.put("result", "success");
			myData.put("message", "Append Server");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}


	/*
	 * Add Servers
	 */
	@CommitAfter
	public StreamResponse onAddServers() {
		 	
		System.out.println("====================================>onAddServers");
		
		JSONObject myData = new JSONObject();
		Server server = null;
		try{
			
			
			for(int i=0; i < 10000 ;i++){

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
		Server server = null;
		try{
			
			
			for(int i=0; i < 10000 ;i++){

				server = (Server)session.load(Server.class, Long.parseLong(Integer.toString(i)));				
				session.delete(server);
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
