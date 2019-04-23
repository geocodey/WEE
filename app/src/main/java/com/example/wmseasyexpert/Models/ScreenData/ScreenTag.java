package com.example.wmseasyexpert.Models.ScreenData;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScreenTag {
    /**
     * Screen id (taken from the database)
     */
    private long id;
    /**
     * Maximum number of rows.
     */
    private int height;
    /**
     * Maximum number of columns.
     */
    private int width;

    /**
     * screen type (options,info,input,menu)
     */
    private String type;
    /**
     *  screen is not hidden
     */
    private boolean keepInstance;
}
