package com.example.wmseasyexpert.models.screen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("unused")
@Setter
@Getter
@ToString
public class Option {

    /**
     * Option value (send to the DB session)
     */
    private String value;
    /**
     * Text to be displayed.
     */
    private String text;
    /**
     * True is the option is selected by default
     * False otherwise
     */
    private boolean selected;
}
