/**
 *
 */
package com.ankur.resourceforwarding;

import java.io.IOException;

import javax.servlet.ServletException;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

/**
 * @author ankur
 *
 */
public class ResourceForward {
	/**
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getInclude() throws ServletException, IOException {
        WebContext wctx = WebContextFactory.get();
        return wctx.forwardToString("/index.jsp?aa=Ankur");
    }

}
