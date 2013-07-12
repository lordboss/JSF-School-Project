package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import services.Auth;

public class LogoutFilter implements Filter{
	protected FilterConfig config = null;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!Auth.isLoggedIn()) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("login.xhtml");
            return;
        } else {
        	chain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;
	}

}
