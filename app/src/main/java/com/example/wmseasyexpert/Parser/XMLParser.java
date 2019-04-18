package com.example.wmseasyexpert.Parser;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;

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


    public static void parse() {
        StringReader xmlReader = new StringReader(optionsXML);
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(xmlReader);
            parser.nextTag();
            String type = getScreenType(parser);
            Log.e(TAG,type);

        } catch (Exception e){
            Log.d(TAG, "Error when parsing.");
        }
        }

    private static String getScreenType(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "screen");
        if(Objects.equals(parser.getName(), SCREEN_TAG)){
            return parser.getAttributeValue(ns,"type");
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
                if(eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if(eventType == XmlPullParser.START_TAG) {
                    System.out.println("Start tag "+parser.getName());
                } else if(eventType == XmlPullParser.END_TAG) {
                    System.out.println("End tag "+parser.getName());
                } else if(eventType == XmlPullParser.TEXT) {
                    System.out.println("Text "+parser.getText());
                }
                eventType = parser.next();
            }
            System.out.println("End document");

        } catch (XmlPullParserException e) {
            Log.d(TAG,"Xml file could not be parsed.");
        } catch (IOException e) {
            Log.d(TAG,"Interface");
        }
    }
}
