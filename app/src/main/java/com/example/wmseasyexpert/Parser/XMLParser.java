package com.example.wmseasyexpert.Parser;

import android.util.Log;

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
    private static final String HELP_TAG   = "help";

    public static final String optionsXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<screen id=\"1\" height=\"8\" width	=\"20\" type=\"options\" keepInstance=\"true\">\n" +
            "	<title>OPTIONS</title>\n" +
            "	<options>\n" +
            "		<option value=\"0\" text=\"zero\" selected=\"true\"/>\n" +
            "		<option value=\"1\" text=\"one\"/>\n" +
            "		<option value=\"2\" text=\"two\"/>\n" +
            "		<option value=\"3\" text=\"three\" selected=\"true\"/>\n" +
            "		<option value=\"4\" text=\"four\"/>\n" +
            "		<option value=\"5\" text=\"five\"/>\n" +
            "		<option value=\"6\" text=\"six\" selected=\"true\"/>\n" +
            "		<option value=\"7\" text=\"seven\"/>\n" +
            "		<option value=\"8\" text=\"eight\"/>\n" +
            "		<option value=\"9\" text=\"nine\"/>\n" +
            "		<option value=\"10\" text=\"ten\"/>\n" +
            "		<option value=\"11\" text=\"eleven\"/>\n" +
            "		<option value=\"12\" text=\"twelve\"/>\n" +
            "		<option value=\"13\" text=\"thirteen\"/>\n" +
            "	</options>\n" +
            "	<input>\n" +
            "		<key>F1</key>\n" +
            "		<key>F2</key>\n" +
            "		<errorMessage>\n" +
            "			<line>Only the IDs  of the</line>\n" +
            "			<line>option are allowed. </line>\n" +
            "			<line>Press F1 for help</line>\n" +
            "			<footer>Press enter !</footer>\" +\n" +
            "		</errorMessage>\n" +
            "	</input>\n" +
            "	<footer>Enter your option:</footer>\n" +
            "	<help key=\"F1\">\n" +
            "		<line>Type the number of the</line>\n" +
            "		<line>selected option</line>\n" +
            "		<line>F1 - this screen</line>\n" +
            "		<footer>Press enter !</footer>\n" +
            "	</help>\n" +
            "</screen>\n";

    public static void parseDoc() {
        Document doc = initDocument(optionsXML);
        String type = getScreenType(doc);
        switch (type){
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
                Log.e(TAG,"Screen type not found : " + type);
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
        if(doc == null){
            return "";
        }
        NodeList entries = doc.getElementsByTagName(SCREEN_TAG);
        Node node = entries.item(0);
        String type = node.getAttributes().getNamedItem("type").getNodeValue();
        Log.d(TAG, "Type of screen : " + type);
        return type;
    }

    private static String getTitle(Document doc){
        if(doc == null){
            return "";
        }
        NodeList entries = doc.getElementsByTagName("title");
        Node node = entries.item(0);
        String title = node.getTextContent();
        Log.d(TAG, "Title : " + title);
        return title;
    }

    private static String getHelpDetails(Document doc){
        NodeList entries = doc.getElementsByTagName(HELP_TAG);
        Node node = entries.item(0);
        String key = node.getAttributes().getNamedItem("key").getNodeValue();
        StringBuilder sb = new StringBuilder();
        NodeList lines = node.getChildNodes();
        for(int i=0;i<lines.getLength();i++){
            Node e = lines.item(i);
            sb.append(e.getTextContent());
        }
        Log.d(TAG,key);
        Log.d(TAG, String.valueOf(sb));
        return sb.toString();
    }

    private static void parseOptionsScreen(Document doc){
        OptionsScreenData optionsScreenData = new OptionsScreenData();
        optionsScreenData.setScreenTag(getScreenTag(doc));
        optionsScreenData.setOptions(getOptionsList(doc));
        optionsScreenData.setTitle(getTitle(doc));
        getHelpDetails(doc);
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
        for(int i=0;i<entries.getLength();i++){
            Option option = new Option();
            Node e = entries.item(i);
            NamedNodeMap attributes = e.getAttributes();
            option.setValue(attributes.getNamedItem("value").getNodeValue());
            option.setText(attributes.getNamedItem("text").getNodeValue());
            if(attributes.getNamedItem("selected") != null){
                option.setSelected(Boolean.parseBoolean(attributes.getNamedItem("selected").getNodeValue()));
            }
            options.add(option);
        }
        return options;
    }

    private static void parseInfoScreen(Document doc){
        Log.d(TAG,"parseInfoScreen");
    }

    private static void parseInputScreen(Document doc){
        Log.d(TAG,"parseInputScreen");
    }

    private static void parseMenuScreen(Document doc){
        Log.d(TAG,"parseMenuScreen");
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
