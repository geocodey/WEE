package com.example.wmseasyexpert.Parser;

import android.util.Log;

import com.example.wmseasyexpert.Models.ScreenData.HelpTag;
import com.example.wmseasyexpert.Models.ScreenData.Option;
import com.example.wmseasyexpert.Models.ScreenData.OptionsScreenData;
import com.example.wmseasyexpert.Models.ScreenData.ScreenTag;
import com.example.wmseasyexpert.Screen.ScreenType;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {
    private static final String TAG = XMLParser.class.getName();
    private static final String SCREEN_TAG = "screen";
    private static final String OPTION_TAG = "option";
    private static final String HELP_TAG = "help";
    private static final String FOOTER_TAG = "footer";

    public static void parseDoc(String xml) {
        Document doc = initDocument(xml);
        String type = getScreenType(doc);
        switch (type) {
            case ScreenType.OPTIONS:
                parseOptionsScreen(doc);
                break;
            case ScreenType.INFO:
                parseInfoScreen(doc);
                break;
            case ScreenType.INPUT:
                parseInputScreen(doc);
                break;
            case ScreenType.MENU:
                parseMenuScreen(doc);
                break;
            default:
                Log.e(TAG, "Screen type not found : " + type);
        }
    }

    private static Document initDocument(String xmlString) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            InputStream xmlStream = new ByteArrayInputStream(xmlString.getBytes());
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(xmlStream);
        } catch (ParserConfigurationException e) {
            Log.e(TAG, "ParserConfigurationException when parsing " + xmlString);
        } catch (SAXException e) {
            Log.e(TAG, "SAXException when parsing " + xmlString);
        } catch (IOException e) {
            Log.e(TAG, "IOException when parsing " + xmlString);
        }
        return null;
    }

    private static String getScreenType(Document doc) {
        if (doc == null) {
            return "";
        }
        NodeList entries = doc.getElementsByTagName(SCREEN_TAG);
        Node node = entries.item(0);
        String type = node.getAttributes().getNamedItem("type").getNodeValue();
        Log.d(TAG, "Type of screen : " + type);
        return type;
    }

    private static String getTitle(Document doc) {
        if (doc == null) {
            return "";
        }
        NodeList entries = doc.getElementsByTagName("title");
        Node node = entries.item(0);
        String title = node.getTextContent();
        Log.d(TAG, "Title : " + title);
        return title;
    }

    private static HelpTag getHelpTag(Document doc) {
        HelpTag helpTag = new HelpTag();
        ArrayList<String> lines = new ArrayList<>();
        NodeList entries = doc.getElementsByTagName(HELP_TAG);
        Node node = entries.item(0);
        helpTag.setHelpKey(node.getAttributes().getNamedItem("key").getNodeValue());
        NodeList nodeChilds = node.getChildNodes();
        for (int i = 0; i < nodeChilds.getLength(); i++) {
            Node e = nodeChilds.item(i);
            if(e.getNodeName().equals(FOOTER_TAG)){
                helpTag.setHelpFooter(e.getTextContent());
            }
            lines.add(e.getTextContent());
        }
        helpTag.setHelpLines(lines);
        Log.d(TAG, helpTag.toString());
        return helpTag;
    }

    private static void parseOptionsScreen(Document doc) {
        OptionsScreenData optionsScreenData = new OptionsScreenData();
        optionsScreenData.setScreenTag(getScreenTag(doc));
        optionsScreenData.setOptions(getOptionsList(doc));
        optionsScreenData.setTitle(getTitle(doc));
        optionsScreenData.setHelpTag(getHelpTag(doc));
        optionsScreenData.setFooter(getFooterTag(doc));
        Log.d(TAG, String.valueOf(optionsScreenData));
    }

    private static ScreenTag getScreenTag(Document doc) {
        ScreenTag screenTag = new ScreenTag();
        NodeList entries = doc.getElementsByTagName(SCREEN_TAG);
        Node node = entries.item(0);
        screenTag.setId(Long.parseLong(node.getAttributes().getNamedItem("id").getNodeValue()));
        screenTag.setHeight(Integer.parseInt(node.getAttributes().getNamedItem("height").getNodeValue()));
        screenTag.setWidth(Integer.parseInt(node.getAttributes().getNamedItem("width").getNodeValue()));
        screenTag.setType(node.getAttributes().getNamedItem("type").getNodeValue());
        screenTag.setKeepInstance(Boolean.parseBoolean(node.getAttributes().getNamedItem("keepInstance").getNodeValue()));

        Log.d(TAG, "Screen tag - " + screenTag);
        return screenTag;
    }

    private static List<Option> getOptionsList(Document doc) {
        List<Option> options = new ArrayList<>();
        NodeList entries = doc.getElementsByTagName(OPTION_TAG);
        for (int i = 0; i < entries.getLength(); i++) {
            Option option = new Option();
            Node e = entries.item(i);
            NamedNodeMap attributes = e.getAttributes();
            option.setValue(attributes.getNamedItem("value").getNodeValue());
            option.setText(attributes.getNamedItem("text").getNodeValue());
            if (attributes.getNamedItem("selected") != null) {
                option.setSelected(Boolean.parseBoolean(attributes.getNamedItem("selected").getNodeValue()));
            }
            options.add(option);
        }
        return options;
    }

    private static String getFooterTag(Document doc) {
        NodeList docNodes = doc.getChildNodes();
        Node screenNode = docNodes.item(0);
        NodeList sceenNodeChilds = screenNode.getChildNodes();
        for (int i = 0; i < sceenNodeChilds.getLength(); i++) {
            Node e = sceenNodeChilds.item(i);
            String tag = e.getNodeName();
            String content = e.getTextContent();
            if (tag.equals(FOOTER_TAG)) {
                return content;
            }
        }
        return null;
    }

    private static void parseInfoScreen(Document doc) {
        Log.d(TAG, "parseInfoScreen");
    }

    private static void parseInputScreen(Document doc) {
        Log.d(TAG, "parseInputScreen");
    }

    private static void parseMenuScreen(Document doc) {
        Log.d(TAG, "parseMenuScreen");
    }

    private static void listAllAttributes(Element element) {

        Log.d(TAG, "List attributes for node: " + element.getNodeName());

        // get a map containing the attributes of this node
        NamedNodeMap attributes = element.getAttributes();

        // get the number of nodes in this map
        int numAttrs = attributes.getLength();

        for (int i = 0; i < numAttrs; i++) {
            Attr attr = (Attr) attributes.item(i);

            String attrName = attr.getNodeName();
            String attrValue = attr.getNodeValue();

            Log.d(TAG, "Found attribute: " + attrName + " with value: " + attrValue);

        }
    }


}
