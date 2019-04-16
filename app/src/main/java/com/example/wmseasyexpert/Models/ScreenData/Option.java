package com.example.wmseasyexpert.Models.ScreenData;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unused")
@Setter
@Getter
public class Option {

    public Option(String value, String text) {
        this.value = value;
        this.text = text;
        this.selected = false;
    }
    public Option(String value, String text, boolean selected) {
        this.value = value;
        this.text = text;
        this.selected = selected;
    }

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
