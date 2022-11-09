package skit.project.bill_payment.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import skit.project.bill_payment.helper.JwtUtil;
import skit.project.bill_payment.serviceImpl.CustomerDetailService;

@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter{
   
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Qualifier("customerdetailservice")
    CustomerDetailService customerDetailService;
   
   
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String userName=null;
        String jwtToken = null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){

            jwtToken = requestTokenHeader.substring(7);

            try{
                userName = this.jwtUtil.extractUsername(jwtToken);

            }
            catch(Exception e){
                e.printStackTrace();
            }

            UserDetails userDetails = this.customerDetailService.loadUserByUsername(userName);
            if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken );
            }else{
                System.out.println("Token is not validated");
            }

        }
        filterChain.doFilter(request, response);
    }
}
