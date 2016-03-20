/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author ahmed elnazer
 */
public class LogFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public LogFilter() {
    }    

    @Override
    public void init(FilterConfig filterConfig)  {
       
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  {
        try {
            // Get the IP address of client machine.
            String ipAddress = request.getRemoteAddr();
            
            // Log the IP address and current timestamp.
            System.out.println("IP "+ ipAddress + ", Time "
                    + new Date().toString());
            
            // Pass request back down the filter chain
            chain.doFilter(request,response);
        } catch (IOException ex) {
            Logger.getLogger(LogFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(LogFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy() {
        
    }
    
   
    
}
