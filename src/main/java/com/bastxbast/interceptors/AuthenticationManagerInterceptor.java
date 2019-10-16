/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bastxbast.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

/**
 *
 * @author corsojava
 */
public class AuthenticationManagerInterceptor implements Interceptor {

    public void destroy() {
      
    }

    public void init() {
       
    }

    public String intercept(ActionInvocation ai) throws Exception {
        Map<String,Object> session = ai.getInvocationContext().getSession();
        String user = (String) session.get("profiloUser");
        if (user == null || !(user.equals("MANAGER") || user.equals("ADMIN")))
            return Action.LOGIN;
        return ai.invoke();
    }
    
}
