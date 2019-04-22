package com.example.wmseasyexpert.Parser;

import android.util.Log;
import android.util.Xml;

import com.example.wmseasyexpert.Screen.ScreenType;
import com.example.wmseasyexpert.Utils.LogUtil;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.extern.flogger.Flogger;

@Flogger
public class XMLParser {
    private static final String TAG = XMLParser.class.getName();
    private static final String ns = null;
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
            "		<footer>Press enter !</footer>\" +\n" +
            "	</help>\n" +
            "</screen>\n";
    private static final String SCREEN_TAG = "screen";


    public static void parseDoc(){

        LogUtil lu = LogUtil.getLogger();
        lu.log("Textt");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db;
        try {
            InputStream xmlStream  = new ByteArrayInputStream(optionsXML.getBytes());
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlStream);
            NodeList entries = doc.getElementsByTagName("option");
            int num = entries.getLength();

            for (int i=0; i<num; i++) {
                Element node = (Element) entries.item(i);
                listAllAttributes(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listAllAttributes(Element element) {

        Log.d(TAG,"List attributes for node: " + element.getNodeName());

        // get a map containing the attributes of this node
        NamedNodeMap attributes = element.getAttributes();

        // get the number of nodes in this map
        int numAttrs = attributes.getLength();

        for (int i = 0; i < numAttrs; i++) {
            Attr attr = (Attr) attributes.item(i);

            String attrName = attr.getNodeName();
            String attrValue = attr.getNodeValue();

            Log.d(TAG,"Found attribute: " + attrName + " with value: " + attrValue);

        }
    }
    public static void parse() {
        StringReader xmlReader = new StringReader(optionsXML);
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(xmlReader);
            parser.nextTag();
            String type = getScreenType(parser);
            Log.d(TAG,parser.getName() + " type " + type);
            if (type.equals(ScreenType.OPTIONS)) {
                readOptions(parser);
            }

        } catch (Exception e) {
            Log.e(TAG,"Error when parsing.");
        }
    }

    private static void readOptions(XmlPullParser parser) throws XmlPullParserException, IOException {
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("option")) {
                Log.d(TAG,parser.getText());
            }
        }

    }

    private static String getScreenType(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "screen");
        if (Objects.equals(parser.getName(), SCREEN_TAG)) {
            return parser.getAttributeValue(ns, "type");
        }
        return "";
    }

    public static void parseTestXml() {
        StringReader xmlReader = new StringReader(optionsXML);
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(xmlReader);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if (eventType == XmlPullParser.START_TAG) {
                    System.out.println("Start tag " + parser.getName());
                } else if (eventType == XmlPullParser.END_TAG) {
                    System.out.println("End tag " + parser.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    System.out.println("Text " + parser.getText());
                }
                eventType = parser.next();
            }
            System.out.println("End document");

        } catch (XmlPullParserException e) {
            Log.e(TAG,"Xml file could not be parsed.");
        } catch (IOException e) {
            Log.e(TAG,"Interface");
        }
    }
}
