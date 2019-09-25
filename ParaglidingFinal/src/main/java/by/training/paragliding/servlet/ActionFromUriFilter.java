//package by.training.paragliding.servlet;
//
//import by.training.paragliding.controller.command.Executable;
//import by.training.paragliding.controller.command.sportsman.ViewAllSportsmen;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//public class ActionFromUriFilter implements Filter {
//	private static Logger logger = LogManager.getLogger("main");
//
//	private static Map<String, Executable> actions = new ConcurrentHashMap<>();
//
//	static {
//		actions.put("/sportsmen/all", new ViewAllSportsmen());
//
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		if(request instanceof HttpServletRequest) {
//			HttpServletRequest httpRequest = (HttpServletRequest)request;
//			String contextPath = httpRequest.getContextPath();
//			String uri = httpRequest.getRequestURI();
//			var startMsg = String.format(
//					"Starting of processing of request for URI \"%s\"", uri);
//			logger.debug(startMsg);
//			int beginAction = contextPath.length();
//			int endAction = uri.lastIndexOf('.');
//			String actionName;
//			if(endAction >= 0) {
//				actionName = uri.substring(beginAction, endAction);
//			} else {
//				actionName = uri.substring(beginAction);
//			}
//			var action = actions.get(actionName);
//			var actionMsg = String.format("Action: %s", action);
//			logger.debug(actionMsg);
//			httpRequest.setAttribute("action", action);
//			chain.doFilter(request, response);
//		} else {
//			logger.error("It is impossible to use HTTP filter");
//			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
//		}
//	}
//
//	@Override
//	public void destroy() {}
//}
