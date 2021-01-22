package com.pa.amt.util;

import com.pa.amt.config.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenCheck {

    private static GlobalProperties cofig;

    @Autowired
    public TokenCheck(GlobalProperties configProperties) {
        this.cofig = configProperties;
    }

    @Autowired
    public static  boolean updateAuth(String token){
        String validToken = cofig.getUpdaeToken();
        System.out.println("user.update.token = " + validToken);
        return token != null && validToken.equals(token);
    }

    @Autowired
    public static  boolean deleteAuth(String token){
        String validToken = cofig.getDeleteToken();
        System.out.println("user.delete.token = " + validToken);
        return token != null && validToken.equals(token);
    }
}
