package com.springbootjpa.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenHelperTest {

    //@InjectMocks
    //private JwtTokenHelper jwtTokenHelper;

    /**
     * @BeforeEach void setUp(){
     * MockitoAnnotations.openMocks(this);
     * }
     */

    /**
    @Disabled
    @Test
    void doGenerateTokenTestSuccess() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method doGenerateToken = JwtTokenHelper.class.getDeclaredMethod("doGenerateToken", String.class);
        doGenerateToken.setAccessible(true);
        Map<String, Object> claims = new HashMap<>();
        claims.put("test", "test");

        String token = (String) doGenerateToken.invoke(jwtTokenHelper, claims, "subject");

        assertNotNull(token);

    }
    */
}