package test;
import static org.junit.Assert.*;


import app.Settings;
import org.junit.Test;

public class MenuTest {
    

    @Test
    public void Login_checkpasscorrect_loginsuccessfull(){
        //arrange
        Settings settings = Settings.getInstanceTest();
        String input = "bomama";
        //act
        Boolean condition = settings.login(input);
        
        //assert
        assertTrue(condition);
    }
    @Test
    public void Login_checkpasswordincorrect_loginNotsuccessfull(){
        //arrange
        Settings settings = Settings.getInstanceTest();
        String input = "bomama123";
        //act
        Boolean condition = settings.login(input);
        
        //assert
        assertFalse(condition);
    }
    @Test
    public void Login_checkpasscorrect_loginsuccesAndRemembersUser(){
        //arrange
        Settings settings = Settings.getInstanceTest();
        String input = "bomama";
        //act
        Boolean condition = settings.login(input);
        Boolean condition2 = settings.login();
        
        //assert
        assertTrue(condition);
        assertTrue(condition2);
    }
    
}
