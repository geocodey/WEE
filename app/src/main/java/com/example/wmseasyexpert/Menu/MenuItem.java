package com.example.wmseasyexpert.Menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class MenuItem implements MenuConstants {
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

    @Override
    public String toString() {
        return this.desc;
    }
}
