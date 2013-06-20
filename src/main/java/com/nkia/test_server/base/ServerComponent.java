package com.nkia.test_server.base;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class ServerComponent implements ClientElement
{
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String clientId;

    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String initMethod;

    private String defaultInitMethod;

    @Inject
    private JavaScriptSupport support;

    @Inject
    private ComponentResources resources; 

	@Property
    private String url = resources.getPageName() + "." + resources.getId();

    //DIV ID
	@Property
	private String divId = Long.toString(RandomUtils.nextLong());	

    protected final void setDefaultMethod(String methodName)
    {
        this.defaultInitMethod = methodName;
    }

    public void setInitMethod(String initMethod)
    {
        this.initMethod = initMethod;
    }

    public String getInitMethod()
    {
        return (initMethod == null) ? defaultInitMethod : initMethod;
    }

    public String getClientId()
    {
        if (clientId == null)
        {
            clientId = support.allocateClientId(resources);
        }

        return this.clientId;
    }
}
