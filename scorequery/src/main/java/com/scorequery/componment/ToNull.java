package com.scorequery.componment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToNull {

    private String str1;


    @Bean
    public ToNull CreatToNull() {
        return new ToNull();
    }

    public String NullToString(String string){
        if (string.length() == 0){
            return null;
        }
        return string;
    }

    public BigInteger NullToBigInteger(String string){
        if (string.length() == 0){
            return null;
        }
        return new BigInteger(string);
    }
}
