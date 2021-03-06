package com.example.wmseasyexpert.models.screen;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseScreenData implements Serializable {

    /**
     * Attributes from screen tag
     */
    private ScreenTag screenTag;
    /**
     * Screen title, displayed on the top.
     */
    private String title;
    /**
     * Text lines to be displayed in normal mode.
     */
    private List<String> textLines;
    /**
     * input tag
     */
    private InputTag inputTag;
    /**
     * Screen footer, displayed on the botop.
     */
    private String footer;
    /**
     * help tag
     */
    private HelpTag helpTag;
}
