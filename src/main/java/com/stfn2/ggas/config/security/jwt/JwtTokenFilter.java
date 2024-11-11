package com.stfn2.ggas.config.security.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.stfn2.ggas.config.exceptions.AppExceptionHandle;
import com.stfn2.ggas.config.exceptions.utils.StandardError;
import com.stfn2.ggas.tools.ToolStr;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final static String ROTA_LOGIN = "/api/auth/signin";

    @Autowired
    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String roteCurrent = ((HttpServletRequest) request).getRequestURI();

        if (roteCurrent.equals(ROTA_LOGIN)) {
            chain.doFilter(request, response);
        } else {
            try {
                String token = tokenProvider.resolveToken((HttpServletRequest) request);
                if (token != null && tokenProvider.validateToken(token)) {
                    Authentication auth = tokenProvider.getAuthentication(token);
                    if (auth != null) {
                        Boolean temPermissao = true;//chamar service passando a rota atual
                        if (temPermissao) {
                            SecurityContextHolder.getContext().setAuthentication(auth);
                            chain.doFilter(request, response);
                        } else {
                            customThrowReturn(response, HttpServletResponse.SC_FORBIDDEN, AppExceptionHandle.acessoNaoPermitido(roteCurrent));
                        }
                    }
                }
            } catch (TokenExpiredException | SignatureVerificationException e) {
                customThrowReturn(response, HttpServletResponse.SC_FORBIDDEN, AppExceptionHandle.tokenExpirado(roteCurrent));
            } catch (Exception e) {
                customThrowReturn(response, HttpServletResponse.SC_FORBIDDEN, AppExceptionHandle.erroNaoMapeado(roteCurrent));
            }
        }
    }

    private void customThrowReturn(ServletResponse response, Integer codResponse, StandardError error) throws IOException {
        // Define o tipo de conteúdo antes de escrever o corpo da resposta
        ((HttpServletResponse) response).setContentType("text/plain; charset=UTF-8");

        // Define o status de resposta como 403 (Acesso Proibido)
        ((HttpServletResponse) response).setStatus(codResponse);

        // Escreve o conteúdo no corpo da resposta
        response.getWriter().write(ToolStr.toJson(error));
    }
}
