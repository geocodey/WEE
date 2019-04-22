package com.example.wmseasyexpert;

public class TestXMLs {
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
    public static final String inputXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<screen id=\"1\" height=\"8\" width	=\"20\" type=\"input\" keepInstance=\"false\">\n" +
                    "	<title>Input title</title>\n" +
                    "	<text>\n" +
                    "		<line></line>\n" +
                    "		<line>Introduceti paleta: </line>\n" +
                    "	</text>\n" +
                    "	<input x=\"5\" y=\"4\" length=\"5\" password=\"false\" default=\"123\" regex=\".*.\">\n" +
                    "		<key>F3</key>\n" +
                    "		<key>F2</key>\n" +
                    "		<errorMessage>\n" +
                    "			<line>Only F1, F2, F3</line>\n" +
                    "			<line>keys are allowed.</line>\n" +
                    "			<line>F1 - Help</line>\n" +
                    "			<line>Press F3 if you </line>\n" +
                    "			<line>would like to go bac</line>\n" +
                    "			<line>k to previous screen</line>\n" +
                    "			<line>Press F2 if you have</line>\n" +
                    "			<line>found a damaged </line>\n" +
                    "			<line>pallet</line>\n" +
                    "			<footer>Press enter !</footer>\n" +
                    "		</errorMessage>\n" +
                    "	</input>\n" +
                    "	<footer>F2 : Damaged pallet!</footer>\n" +
                    "	<help key=\"F1\">\n" +
                    "		<line>Please scan the </line>\n" +
                    "		<line>pallet label.</line>\n" +
                    "		<line>Press F3 if you </line>\n" +
                    "		<line>would like to go bac</line>\n" +
                    "		<line>k to previous screen.</line>\n" +
                    "		<line>Press F2 if you have</line>\n" +
                    "		<line>found a damaged </line>\n" +
                    "		<line>pallet.</line>\n" +
                    "		<footer>Press enter !</footer>\n" +
                    "	</help>\n" +
                    "</screen>\n";
    public static final String infoXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<screen id=\"2\" height=\"8\" width	=\"20\" type=\"info\" keepInstance=\"false\">\n" +
                    "	<title>Information</title>\n" +
                    "	<text>\n" +
                    "		<line>This is an informat</line>\n" +
                    "		<line>ion screen used to </line>\n" +
                    "		<line>display instruction</line>\n" +
                    "		<line>, introduction, con</line>\n" +
                    "		<line>firmation, help, ge</line>\n" +
                    "		<line>neral text, etc whi</line>\n" +
                    "		<line>are not required a</line>\n" +
                    "		<line>ny input control k</line>\n" +
                    "		<line>key. The only expe</line>\n" +
                    "		<line>cted input are F1,</line>\n" +
                    "		<line>UP, DOWN or exit.</line>\n" +
                    "	</text>\n" +
                    "	<input>\n" +
                    "		<key>F3</key>\n" +
                    "		<errorMessage>\n" +
                    "			<line>Only F1 or the arrow</line>\n" +
                    "			<line>key are are allowed. </line>\n" +
                    "			<line>Press F3 if you </line>\n" +
                    "			<line>would like to go bac</line>\n" +
                    "			<line>k to previous screen</line>\n" +
                    "			<footer>Press enter !</footer>\n" +
                    "		</errorMessage>\n" +
                    "	</input>\n" +
                    "	<footer>ENTER:Exit|F3:Back</footer>\n" +
                    "	<help key=\"F1\">\n" +
                    "		<line>This is an informati</line>\n" +
                    "		<line>op screen</line>\n" +
                    "		<line>F1 - this screen</line>\n" +
                    "		<line>F3 - previous screen</line>\n" +
                    "		<line>ENTER - next step</line>\n" +
                    "		<footer>Press enter !</footer>\n" +
                    "	</help>\n" +
                    "</screen>\n";
    public static final String menuXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<screen id=\"1\" height=\"8\" width	=\"20\" type=\"menu\" keepInstance=\"true\">\n" +
                    "	<title> M E N U E</title>\n" +
                    "	<node id=\"1\" desc=\"Main\">\n" +
                    "		<node id=\"2\" desc=\"Screen lists\">\n" +
                    "			<leaf id=\"3\" result=\"input\">Input</leaf>\n" +
                    "			<leaf id=\"4\" result=\"info\">Single Options</leaf>\n" +
                    "			<leaf id=\"5\" result=\"options\">Multi Options</leaf>\n" +
                    "			<leaf id=\"6\" result=\"multioptions\">Multi Options</leaf>\n" +
                    "		</node>\n" +
                    "		<leaf id=\"7\" result=\"logout\">Logout</leaf>\n" +
                    "	</node>\n" +
                    "	<input>\n" +
                    "		<key>F1</key>\n" +
                    "		<key>F4</key>\n" +
                    "		<errorMessage>\n" +
                    "			<line>Only menu options </line>\n" +
                    "			<line>are allowed. </line>\n" +
                    "			<footer>Press enter !</footer>" +
                    "		</errorMessage>\n" +
                    "	</input>\n" +
                    "	<footer>Enter your option:</footer>\n" +
                    "	<help key=\"F1\">\n" +
                    "		<line>Type your menu</line>\n" +
                    "		<line>option</line>\n" +
                    "		<line>F1 - this screen</line>\n" +
                    "		<line>F4 - logout</line>\n" +
                    "		<footer>Press enter !</footer>" +
                    "	</help>\n" +
                    "</screen>\n";
}
