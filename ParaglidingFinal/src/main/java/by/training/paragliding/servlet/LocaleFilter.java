package by.training.paragliding.servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    private static final String DEFAULT_LOCALE = "defaultLocale";
    private String defaultLang;
    private static final String LOCALE_ATTR = "locale";

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        defaultLang = filterConfig.getInitParameter(DEFAULT_LOCALE);
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute(LOCALE_ATTR);

        if (locale == null) {
            String localeName = null;
            var cookies = ((HttpServletRequest) req).getCookies();
            for (var c : cookies) {
                if (c.getName().equals(LOCALE_ATTR)) {
                    localeName = c.getName();
                    break;
                }
            }
            if (localeName != null) {
                session.setAttribute(LOCALE_ATTR, localeName);
                filterChain.doFilter(req, resp);
                return;
            }
            session.setAttribute(LOCALE_ATTR, defaultLang);
        }
        filterChain.doFilter(req, resp);
    }

}
