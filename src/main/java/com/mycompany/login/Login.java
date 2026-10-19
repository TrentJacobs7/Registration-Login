package com.mycompany.login;

public class Login {

    private String regUsername;
    private String regPassword;
    private String regCell;
    private String userFirstName;
    private String userLastName;
    private boolean loggedIn = false;

    public boolean checkUserName(String user) {
        return user != null && user.contains("_") && user.length() <= 5;
    }

    public String getUsernameCaptureMessage(String user) {
        if (checkUserName(user)) {
            return "Username successfully captured.";
        }
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

   
    public boolean checkPasswordComplexity(String pass) {
        if (pass == null || pass.length() < 8) {
            return false;
        }

      
        boolean upper = false;
        boolean number = false;
        boolean special = false;

        
        for (int i = 0; i < pass.length(); i++) {
            char ch = pass.charAt(i);
            if (Character.isUpperCase(ch)) upper = true;
            else if (Character.isDigit(ch)) number = true;
            else if (!Character.isLetterOrDigit(ch)) special = true;
        }

        return upper && number && special;
    }

    public String getPasswordCaptureMessage(String pass) {
        if (checkPasswordComplexity(pass)) {
            return "Password successfully captured.";
        }
        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    public boolean checkCellPhoneNumber(String cell) {
        return cell != null && cell.matches("^\\+27\\d{9}$");
    }

    public String getCellPhoneCaptureMessage(String cell) {
        if (checkCellPhoneNumber(cell)) {
            return "Cell number successfully captured.";
        }
        return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }

    public String registerUser(String user, String pass, String cell, String first, String last) {
        if (!checkUserName(user)) return getUsernameCaptureMessage(user);
        if (!checkPasswordComplexity(pass)) return getPasswordCaptureMessage(pass);
        if (!checkCellPhoneNumber(cell)) return getCellPhoneCaptureMessage(cell);

        this.regUsername = user;
        this.regPassword = pass;
        this.regCell = cell;
        this.userFirstName = first;
        this.userLastName = last;

        return "User has been registered successfully.";
    }

    public boolean loginUser(String user, String pass) {
        if (regUsername == null || regPassword == null) return false;

        boolean success = user.equals(regUsername) && pass.equals(regPassword);
        loggedIn = success;
        return success;
    }

    public String returnLoginStatus(String user, String pass) {
        if (loginUser(user, pass)) {
            return "Welcome " + userFirstName + " " + userLastName + ", it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}