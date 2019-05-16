package com.example.wmseasyexpert.screen;

public interface ResultType {
    int UNDEFINED = 0;
    /**
     * The result of an action screen is a key
     */
    int KEY = 1;
    /**
     * The result of an action screen is an input value entered by the user
     */
    int VALUE = 2;
    /**
     * The result is an option from an single option screen
     */
    int OPTION = 3;
    /**
     * The result is an array from a multi option screen
     */
    int MULTI_OPTION = 4;
    /**
     * When we have this type of result there is no process page
     * This is the result of an menu item.
     */
    int FINAL_RESULT = 5;
    /**
     * Special for hidden screen
     * For process action there is no user input to set,
     * only to call the peocess procedure
     */
    int HIDDEN = 6;
}

