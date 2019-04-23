package com.example.wmseasyexpert.Models.ScreenData;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseScreenData {

    /**
     * Attributes from screen tag
     */
    private ScreenTag screenTag;
    /**
     * Screen title, displayed on the top.
     */
    private String title;
    /**
     * Screen footer, displayed on the botop.
     */
    private String footer;
    /**
     * Text lines to be displayed in normal mode.
     */
    private List<String> textLines;
    /**
     * Message lines that will be displayed in case of invalid
     * input occurs.
     */
    private List<String> invalidInputLines;
    /**
     * Contains the list of expected keys that will be accepted as input
     */
    private List<String> expectedKeys;
    /**
     * Regular expresion to validate the input.
     */
    private String inputRegex;
    /**
     * X coordinate of the input field control
     */
    private int inputX;
    /**
     * Y coordinate of the inpur field control
     */
    private int inputY;
    /**
     * Default value for input field.
     */
    private String defaultVal;
    /**
     * Maximum number of characters
     */
    private int length;
    /**
     * True if the input field is a password field
     */
    private boolean password;
    /**
     * Footer will be displayed on the screen displaying the
     * invalid input message.
     */
    private String invalidInputFooter;

    /**
     * help tag
     */
    private HelpTag helpTag;
}
