package com.example.wmseasyexpert.Models.ScreenData;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ErrorMessageTag {
    /**
     * Message lines that will be displayed in case of invalid
     * input occurs.
     */
    private List<String> invalidInputLines;
    /**
     * Footer will be displayed on the screen displaying the
     * invalid input message.
     */
    private String invalidInputFooter;
}
