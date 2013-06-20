package com.nkia.test_server.components;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.hibernate.Session;

import com.nkia.test_server.base.ServerComponent;

@Import(stack = "ExtJSStack")
public class ExtGrid extends ServerComponent
{
	
	@Inject
	private Session session;

	@Inject
	private Request request;
	
}
