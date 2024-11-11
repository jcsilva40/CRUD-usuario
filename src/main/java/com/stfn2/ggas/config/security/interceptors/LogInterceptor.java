package com.stfn2.ggas.config.security.interceptors;

import com.stfn2.ggas.config.security.jwt.JwtTokenProvider;
import com.stfn2.ggas.core.utils.Log;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Component
public class LogInterceptor implements HandlerInterceptor {

   private JwtTokenProvider tokenProvider;
   private Log log = new Log(this.getClass());

   public LogInterceptor(JwtTokenProvider tokenProvider){
      this.tokenProvider = tokenProvider;
      log.info("Interceptor Log intialized! ");
   }

   @Override
   public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
      String user = "";
      if(!Objects.isNull(request.getUserPrincipal())){
          if(!Objects.isNull(request.getUserPrincipal().getName())){
              user = request.getUserPrincipal().getName();
          }
      }        
      String URI = request.getRequestURI();
      String parametros = "";

      for(String parametro : request.getParameterMap().keySet() ){
         String value = request.getParameter(parametro);;
         parametros = parametros + "::" + parametro + ": " + value;
      }
      log.info("Requisição: Usuer:" + user + " Uri: " + request.getMethod() + ":" +  URI + " " +
              "Parametros: " + parametros);
      return true;
   }

   @Override
   public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {

   }

   @Override
   public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {

   }

   private String obterUser(HttpServletRequest request){
      String token = tokenProvider.resolveToken((HttpServletRequest) request);
      Authentication auth = tokenProvider.getAuthentication(token);
      return  auth.getName();
   }
}
