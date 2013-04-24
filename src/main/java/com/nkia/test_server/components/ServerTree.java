package com.nkia.test_server.components;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.collections.list.TreeList;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.tree.TreeNode;
import org.hibernate.Session;

import com.nkia.test_core.common.JsonStreamResponse;
import com.nkia.test_server.base.ServerComponent;
import com.nkia.test_server.entities.Server;

@Import(stack = "ExtJSStack")
public class ServerTree extends ServerComponent
{
	
	@Inject
	private Session session;

	@Inject
	private Request request;

	@Parameter
	@Property
	private String title;	
	
	public StreamResponse onGetLeafs(){
		
		System.out.println("====================================>onGetLeafs=====>" + request.getParameter("id"));
		
		return onGetServers();
	}

	
	/*
	 * Server List
	 */
	public StreamResponse onGetServers() {
		System.out.println("====================================>onGetServers=====>");
		List<Server> list = session.createCriteria(Server.class).list();
		TreeList treeList = new TreeList();

		
		//System.out.println("====================================>onGetServers list=====>"+list);
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		net.sf.json.JSONArray jsonArray = null;
		try{
			
			if(list != null){
				
				int size = list.size();
				for (Iterator<Server> iterator = list.iterator(); iterator.hasNext(); ){
					Server server = iterator.next();
					TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
					
					treeMap.put("text", server.name);
					treeMap.put("data", net.sf.json.JSONObject.fromObject(server));
					//treeMap.put("leaf", false);
					
					treeList.add(treeMap);
				}
			}
			
			jsonArray = net.sf.json.JSONArray.fromObject(treeList);			
			
			json.put("items", jsonArray.toString());			

			//System.out.println("====================================>onGetServers json=====>"+json.toString());
			
		}catch(net.sf.json.JSONException e){
			System.out.println("====================================>net.sf.json.JSONException=====>");
			e.printStackTrace();
		}
		return new JsonStreamResponse(jsonArray.toString());

	}	

	/*
	 * server info
	 */
	public StreamResponse onServerInfo() {
		System.out.println("====================================>onServerInfo=====>");

		String id = request.getParameter("id");
		System.out.println("====================================>onServerInfo id=====>"+ id);

		TreeList treeList = new TreeList();
		Server server = (Server)session.load(Server.class, Long.parseLong(id));
		

		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		
		treeMap.put("text", server.name);
		treeMap.put("value", server.name);
		treeMap.put("leaf", true);
		treeList.add(treeMap);
		
		treeMap.put("text", server.hostname);
		treeMap.put("value", server.hostname);
		treeMap.put("leaf", true);
		treeList.add(treeMap);
		
		treeMap.put("text", server.ipAddress);
		treeMap.put("value", server.ipAddress);
		treeMap.put("leaf", true);
		treeList.add(treeMap);
		
		treeMap.put("text", server.description);
		treeMap.put("value", server.description);
		treeMap.put("leaf", true);
		treeList.add(treeMap);

		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(treeList);

		System.out.println("====================================>onServerInfo result=====>"+json.toString());
		
		return new JsonStreamResponse(json.toString());

	}
	
}
