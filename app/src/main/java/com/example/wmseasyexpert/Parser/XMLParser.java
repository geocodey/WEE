package com.example.wmseasyexpert.Parser;

public class XMLParser {

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
    
}
