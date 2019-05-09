package com.example.wmseasyexpert.menu;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("WeakerAccess")
@Getter
@Setter
public class MenuLeaf extends MenuItem {
    /**
     * Contains the ID of the screen to be
     * opened when this leaf is choosed.
     */
    private String result;

    public MenuLeaf(long id, String desc, String result, MenuItem parent) {
        super(id, desc, parent);
        this.result = result;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public String getNextScreen() {
        return result;
    }

    @Override
    public boolean isNode() {
        return false;
    }

    /**
     * Creates a new menu leaf
     * @param currentXMLNode xml
     * @param parent parentNode
     */
    public static MenuItem createLeaf(Node currentXMLNode, MenuNode parent){

        // get nodes attributes
        NamedNodeMap attributesMaps = currentXMLNode.getAttributes();

        // get node id
        long id = Long.valueOf(attributesMaps.getNamedItem(MENUITEM_ID).getNodeValue());
        // get leaf result (screen to be opened)
        String result = attributesMaps.getNamedItem(MENULEAF_RESULT).getNodeValue();
        // get LEAF description
        String desc = currentXMLNode.getTextContent();

        // create the leaf
        return new MenuLeaf(id, desc, result, parent);
    }
}
