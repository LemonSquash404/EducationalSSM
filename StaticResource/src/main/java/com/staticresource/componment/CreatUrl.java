package com.staticresource.componment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatUrl implements Serializable {

    private String filePath ;
    private String baseUrl ;

    @Bean
    public CreatUrl getRealPath(){
     return new CreatUrl();
    }

    public String CreatBaseUrl(HttpServletRequest servletRequest){
        String AllUrl = servletRequest.getRequestURL().toString();
        String projectAndMethod = servletRequest.getRequestURI();
        String project = servletRequest.getContextPath();
        String method = projectAndMethod.replace(project,"");
        String baseUrl = AllUrl.replace(method, "");
        return baseUrl;
    }

    public String CreatFilePath(HttpServletRequest servletRequest,String RootPath){
        return servletRequest.getSession().getServletContext().getRealPath(RootPath);
    }
}
