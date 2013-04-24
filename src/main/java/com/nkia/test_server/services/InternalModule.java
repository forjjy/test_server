package com.nkia.test_server.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.services.LibraryMapping;

public class InternalModule {
	public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("test_server", "com.nkia.test_server"));
	}
	
	public static void contributeHibernateEntityPackageManager(Configuration<String> configuration)
	{
		configuration.add("com.nkia.test_server.entities");
	}
}
