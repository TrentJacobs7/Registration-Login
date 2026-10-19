package com.mycompany.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    Login login = new Login();

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("wrong", "wrongpass"));
    }

    @Test
    public void testRegisterUsernameIncorrectMessage() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals(expected, login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976"));
    }

    @Test
    public void testRegisterPasswordIncorrectMessage() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals(expected, login.registerUser("kyl_1", "password", "+27838968976"));
    }

    @Test
    public void testRegisterUsernameCorrectMessage() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username successfully captured"));
    }

    @Test
    public void testRegisterPasswordCorrectMessage() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Password successfully captured"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String expected = "Welcome User Name, it is great to see you again.";
        assertEquals(expected, login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatusFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, login.returnLoginStatus("wrong", "wrong"));
    }
}