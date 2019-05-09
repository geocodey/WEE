package com.example.wmseasyexpert.menu;

import androidx.annotation.NonNull;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class MenuItem implements MenuConstants, Serializable {
    /**
     * Object ID (from database)
     */
    private long id;
    /**
     * Description displayed in the screen.
     */
    private String desc;
    /**
     * The parrent of the item.
     */
    private MenuItem parent;

    public abstract boolean isNode();

    public abstract boolean isLeaf();

    public abstract String getNextScreen();

    @Override
    @NonNull
    public String toString() {
        return this.desc;
    }
}
