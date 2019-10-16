package by.training.paragliding.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EncodingFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(StandardCharsets.UTF_8.name());
		chain.doFilter(request, response);
	}

}
