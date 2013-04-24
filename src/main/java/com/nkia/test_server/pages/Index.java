package com.nkia.test_server.pages;

import org.apache.tapestry5.annotations.Import;

import com.nkia.test_core.annotations.PluginPage;


@PluginPage
@Import(stack="ExtJSStack" )
public class Index {

    public String[] getPageNames()
    {
        return new String[]{"Network"};
    }
}
