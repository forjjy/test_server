package com.nkia.test_server.components;

import java.util.List;

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
public class ServerList extends ServerComponent {
	
	@Inject
	private Session session;

	@Inject
	private Request request;

	/*
	 * server info
	 */
	public StreamResponse onServerInfo() {
		System.out.println("====================================>onServerInfo=====>");

		String id = request.getParameter("id");
		System.out.println("====================================>onServerInfo id=====>"+ id);
		
		Server server = (Server)session.load(Server.class, Long.parseLong(id));

		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(server);

		System.out.println("====================================>onServerInfo result=====>"+json.toString());
		
		return new JsonStreamResponse(json.toString());

	}
	
	/*
	 * Server List
	 */
	public StreamResponse onGetServers() {
		System.out.println("====================================>onGetServers=====>");
		List<Server> list = session.createCriteria(Server.class).list();

		
		//System.out.println("====================================>onGetServers list=====>"+list);
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		net.sf.json.JSONArray jsonArray = null;
		try{
			
			jsonArray = net.sf.json.JSONArray.fromObject(list);			
			
			//System.out.println("====================================>onGetServers jsonArray=====>"+jsonArray);
			
			json.put("root", jsonArray.toString());			

			//System.out.println("====================================>onGetServers json=====>"+json.toString());
			
		}catch(net.sf.json.JSONException e){
			System.out.println("====================================>net.sf.json.JSONException=====>");
			e.printStackTrace();
		}
		return new JsonStreamResponse(jsonArray.toString());

	}

	/*
	 * Delete Server
	 */
	@CommitAfter
	public StreamResponse onDelete() {
		System.out.println("====================================>onDelete=====>");

		String id = request.getParameter("id");
		System.out.println("====================================>onDelete id=====>"+ id);
		
		Server server = (Server)session.load(Server.class, Long.parseLong(id));

		JSONObject json = new JSONObject();
		
		if(server != null){
			session.delete(server);
			json.put("result", "success");
			json.put("message", "Delete Server");
		}else{
			json.put("result","failure");
		}

		System.out.println("====================================>onDelete result=====>"+json.toString());
		return new JsonStreamResponse(json.toString());

	}
	
	/*
	 * Update Server
	 */
	@CommitAfter
	StreamResponse onUpdateServer() {
		 	
		System.out.println("====================================>onUpdateServer");
		
		JSONObject myData = new JSONObject();
		try{


			Server server = (Server)session.load(Server.class, Long.parseLong(request.getParameter("id")));
			
			server.setName(request.getParameter("name"));
			server.setHostname(request.getParameter("hostname"));
			server.setIpAddress(request.getParameter("ipAddress"));
			server.setDescription( request.getParameter("description"));
			
			System.out.println("server====================================>" + server.toString());
			
			session.saveOrUpdate(server);
	
			myData.put("result", "success");
			myData.put("message", "Update Server");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}
}
