package com.example.wmseasyexpert.models.screen;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InputTag implements Serializable {
    /**
     * X coordinate of the input field control
     */
    private int inputX;
    /**
     * Y coordinate of the inpur field control
     */
    private int inputY;
    /**
     * Maximum number of characters
     */
    private int length;
    /**
     * True if the input field is a password field
     */
    private boolean password;
    /**
     * Default value for input field.
     */
    private String defaultVal;
    /**
     * Regular expresion to validate the input.
     */
    private String inputRegex;
    /**
     * Contains the list of expected keys that will be accepted as input
     */
    private List<String> expectedKeys;

    private ErrorMessageTag errorMessageTag;
}
