package ru.skilanov.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр для энкодинга.
 */
public class EncodingFilter implements Filter {

    public static final String UTF_8 = "UTF-8";

    /**
     * Инициализация.
     *
     * @param filterConfig FilterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * Задаем энкодинг utf-8.
     *
     * @param servletRequest  ServletRequest
     * @param servletResponse ServletResponse
     * @param filterChain     FilterChain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(UTF_8);
        servletResponse.setCharacterEncoding(UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Закрытие.
     */
    @Override
    public void destroy() {

    }
}
