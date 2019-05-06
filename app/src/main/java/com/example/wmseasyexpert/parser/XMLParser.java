package com.example.wmseasyexpert.parser;

import android.util.Log;

import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.models.screen.ErrorMessageTag;
import com.example.wmseasyexpert.models.screen.HelpTag;
import com.example.wmseasyexpert.models.screen.InputTag;
import com.example.wmseasyexpert.models.screen.Option;
import com.example.wmseasyexpert.models.screen.OptionsScreenData;
import com.example.wmseasyexpert.models.screen.ScreenTag;
import com.example.wmseasyexpert.screen.ScreenType;

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

    public static BaseScreenData parseDoc(String xml) {
        BaseScreenData screenData = null;
        Document doc = initDocument(xml);
        if (doc == null) {
            return null;
        }
        String type = getScreenType(doc);
        switch (type) {
            case ScreenType.OPTIONS:
                screenData = getBaseScreenData(new OptionsScreenData(), doc);
                ((OptionsScreenData) screenData).setOptions(getOptionsList(doc));
                break;
            case ScreenType.INFO:
                screenData = getBaseScreenData(new BaseScreenData(), doc);
                screenData.setTextLines(getTextLines(doc));
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
        return screenData;
    }

    private static List<String> getTextLines(Document doc) {
        List<String> lines = new ArrayList<>();
        NodeList textTag = doc.getElementsByTagName(Tags.TEXT_TAG);
        Node node = textTag.item(0);
        NodeList nodeChilds = node.getChildNodes();
        for (int i = 0; i < nodeChilds.getLength(); i++) {
            Node e = nodeChilds.item(i);
            lines.add(e.getTextContent());
        }
        return lines;
    }

    private static BaseScreenData getBaseScreenData(BaseScreenData baseScreenData, Document doc) {
        baseScreenData.setScreenTag(getScreenTag(doc));
        baseScreenData.setTitle(getTitle(doc));
        baseScreenData.setInputTag(getInputTag(doc));
        baseScreenData.setHelpTag(getHelpTag(doc));
        baseScreenData.setFooter(getFooterTag(doc));
        return baseScreenData;

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
        NodeList entries = doc.getElementsByTagName(Tags.SCREEN_TAG);
        Node node = entries.item(0);
        String type = node.getAttributes().getNamedItem(Tags.TYPE_TAG).getNodeValue();
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
        NodeList entries = doc.getElementsByTagName(Tags.HELP_TAG);
        Node node = entries.item(0);
        helpTag.setHelpKey(node.getAttributes().getNamedItem("key").getNodeValue());
        NodeList nodeChilds = node.getChildNodes();
        for (int i = 0; i < nodeChilds.getLength(); i++) {
            Node e = nodeChilds.item(i);
            if (e.getNodeName().equals(Tags.FOOTER_TAG)) {
                helpTag.setHelpFooter(e.getTextContent());
            }
            lines.add(e.getTextContent());
        }
        helpTag.setHelpLines(lines);
        Log.d(TAG, helpTag.toString());
        return helpTag;
    }

    private static ScreenTag getScreenTag(Document doc) {
        ScreenTag screenTag = new ScreenTag();
        NodeList entries = doc.getElementsByTagName(Tags.SCREEN_TAG);
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
        NodeList entries = doc.getElementsByTagName(Tags.OPTION_TAG);
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

    private static InputTag getInputTag(Document doc) {
        InputTag inputTag = new InputTag();
        ErrorMessageTag errorMessageTag = new ErrorMessageTag();
        ArrayList<String> keys = new ArrayList<>();
        NodeList entries = doc.getElementsByTagName(Tags.INPUT_TAG);
        Node node = entries.item(0);
        NodeList nodeChilds = node.getChildNodes();
        for (int i = 0; i < nodeChilds.getLength(); i++) {
            Node e = nodeChilds.item(i);
            if (e.getNodeName().equals(Tags.KEY_TAG)) {
                keys.add(e.getTextContent());
            } else if (e.getNodeName().equals(Tags.ERROR_MESSAGE_TAG)) {
                errorMessageTag = getErrorMessageTag(e);

            }
        }
        inputTag.setExpectedKeys(keys);
        inputTag.setErrorMessageTag(errorMessageTag);
        Log.d(TAG, inputTag.toString());
        return inputTag;
    }

    private static ErrorMessageTag getErrorMessageTag(Node node) {
        List<String> lines = new ArrayList<>();
        String footer = null;
        NodeList nodeChilds = node.getChildNodes();
        for (int i = 0; i < nodeChilds.getLength(); i++) {
            Node e = nodeChilds.item(i);
            String nodeName = e.getNodeName();
            String content = e.getTextContent();
            if (nodeName.equals(Tags.LINE_TAG)) {
                lines.add(content);
            } else if (nodeName.equals(Tags.FOOTER_TAG)) {
                footer = content;
            }
        }
        ErrorMessageTag errorMessageTag = new ErrorMessageTag();
        errorMessageTag.setInvalidInputLines(lines);
        errorMessageTag.setInvalidInputFooter(footer);
        return errorMessageTag;
    }

    private static String getFooterTag(Document doc) {
        NodeList docNodes = doc.getChildNodes();
        Node screenNode = docNodes.item(0);
        NodeList sceenNodeChilds = screenNode.getChildNodes();
        for (int i = 0; i < sceenNodeChilds.getLength(); i++) {
            Node e = sceenNodeChilds.item(i);
            String tag = e.getNodeName();
            String content = e.getTextContent();
            if (tag.equals(Tags.FOOTER_TAG)) {
                return content;
            }
        }
        return null;
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
