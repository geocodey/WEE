package com.example.wmseasyexpert.Models.Menu;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import lombok.Getter;

@SuppressWarnings({"WeakerAccess", "unused"})
@Getter
public class MenuNode extends MenuItem {
    /**
     * All child nodes.
     */
    private List<MenuItem> childs;
    /**
     * @param id Database id
     * @param desc Nodescription in apropiate language
     */
    public MenuNode(long id, String desc, MenuItem parent) {
        super(id, desc, parent);
        childs = new ArrayList<>();
    }

    /**
     * Add a new child fot this node.
     */
    public void addChild(MenuItem child){
        this.childs.add(child);
    }

    /**
     * @return	The text lines of the current node
     * 			which will be displayed on the screen.
     */
    public List<String> getLines(){
        // get number of lines
        int size = (getParent() == null)?childs.size() + 1:childs.size();
        Vector<String> lines = new Vector<>(size);
        String line;

        if (getParent() != null){
            // if it has a parent then add the parrent with option 0
            line = "+0 " + getParent().getDesc();
        }else{
            // add the description of the current node
            // but without 0 option
            line = "   " + getDesc();
        }
        lines.add(line);
        for (int i = 0; i < childs.size(); i++){
            line = "";
            // add all the description for childs
            MenuItem menuItem = childs.get(i);
            if (menuItem.isNode()) {
                line += "+"; // for nodes with put a +
            }else{
                line += " "; // for nodes with put a +
            }
            line += i + 1; // add item option
            line += " ";
            line += menuItem.getDesc(); // done description
            lines.add(line);
        }
        return lines;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        List<String> lines = getLines();
        for (int i = 0; i < lines.size(); i++){
            sb.append(lines.get(i));
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isNode() {
        return true;
    }

    /**
     * Builds the Regular Expression to validate the
     * input for this node.
     * @return	"[0..childsNo]" in case it has a parrent
     * 			"[1..childsNo]" in case it doesn't have parrent
     */
    public String getRegex(){
        String regex = "[";

        if (getParent() == null)
            regex += "1";
        else
            regex += "0";
        regex += "-";
        regex += childs.size();

        regex += "]";

        return regex;
    }

    /**
     * Loads the menu hierarchy from an XML node and creates
     * a new node with it.
     * @param currentXMLNode xml
     * @param parent parent node
     */
    public static MenuItem createNode(Node currentXMLNode, MenuNode parent){

        // get nodes attributes
        NamedNodeMap attributesMaps = currentXMLNode.getAttributes();

        // get node id
        long id = Long.valueOf(attributesMaps.getNamedItem(MENUITEM_ID).getNodeValue());
        // get node description
        String desc = attributesMaps.getNamedItem(MENUITEM_DESC).getNodeValue();

        // create the node
        MenuNode currentMenuNode = new MenuNode(id, desc, parent);

        // create childs
        NodeList childsXMLList = currentXMLNode.getChildNodes();

        for (int i = 0; i < childsXMLList.getLength(); i++){
            Node node = childsXMLList.item(i);
            MenuItem newMenuItem = null;
            if (node.getNodeName().equals(MENUNODE_NAME)){
                // we have a new node
                newMenuItem = MenuNode.createNode(node, currentMenuNode);
            }
            else if (node.getNodeName().equals(MENULEAF_NAME)){
                // we have a new leaf
                newMenuItem = MenuLeaf.createLeaf(node, currentMenuNode);
            }
            if (newMenuItem != null)
                // add the new item to the list
                currentMenuNode.addChild(newMenuItem);
        }

        return currentMenuNode;
    }
}
