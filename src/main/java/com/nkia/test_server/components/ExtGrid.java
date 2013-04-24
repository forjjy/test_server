package com.nkia.test_server.components;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.hibernate.Session;

import com.nkia.test_core.common.JsonStreamResponse;
import com.nkia.test_server.base.ServerComponent;
import com.nkia.test_server.entities.Server;

@Import(stack = "ExtJSStack")
public class ExtGrid extends ServerComponent
{
	
	@Inject
	private Session session;

	@Inject
	private Request request;
	
	@Parameter
	@Property
	private String id;

	@Parameter
	@Property
	private String title;

	/*
	 * Add Server
	 */
	@CommitAfter	
	StreamResponse onAdd() {
		 	
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
	
}
