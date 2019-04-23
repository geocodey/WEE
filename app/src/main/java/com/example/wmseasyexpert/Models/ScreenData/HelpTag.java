package com.example.wmseasyexpert.Models.ScreenData;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelpTag {

    /**
     * Key used to display the help screen when the users asks.
     */
    private String helpKey;
    /**
     * Help lines to be displayed in case F1 is pressed.
     */
    private List<String> helpLines;
    /**
     * Footer will be displayed on the screen displaying the
     * help screen message.
     */
    private String helpFooter;
}
