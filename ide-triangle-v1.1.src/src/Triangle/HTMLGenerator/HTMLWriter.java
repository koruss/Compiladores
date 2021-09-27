package Triangle.HTMLGenerator;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/*
* @author Andres
 */
public class HTMLWriter {

	public String fileName, fileBuffer;
	
	public HTMLWriter() {
		fileName = "";
		// Add the metadata and head elements to the html buffer.
		fileBuffer = htmlOpener;
	}

	public HTMLWriter(String _fname) {
		// Get the absolute filename without the ".tri", and change it to ".html".
		fileName = (_fname.substring(0, _fname.length() - 4)) + ".html";
		// Add the metadata and head elements to the html buffer.
		fileBuffer = htmlOpener;
	}

	public void writeFile() {
		try {
			// Add the needed tags to end the html document.
			fileBuffer += htmlCloser;
			// Write the file.
			FileWriter htmlFile = new FileWriter(fileName);
			htmlFile.write(fileBuffer);
			htmlFile.close();
			// Clear the buffer.
			fileBuffer = "";
		} catch (IOException e) {
			System.err.println("ERROR: An error has occurred while creating the html source file.");
			e.printStackTrace();
		}
	}

	public void writeWord(String w) {
		fileBuffer += w;
	}

	public void writeSpace() {
		fileBuffer += " ";
	}

	public void writeReservedWord(String resW) {
		fileBuffer += "<span class='reservedword'>" + resW + "</span>";
	}

	public void writeLiteral(String lit) {
		fileBuffer += "<span class='literal'>" + lit + "</span>";
	}

	public void writeComment(String cmnt) {
		fileBuffer += "<span class='comment'>!" + cmnt + "</span><br>";
	}

	public void writeSpacingChar(char chr) {
		switch (chr) {
			case '\n': {
				fileBuffer += "<br>";
			}
			break;
			case '\t': {
				fileBuffer += "&emsp;";
			}
			break;
			default:
				fileBuffer += "&nbsp;";

		}

	}

	public static String htmlOpener = String.join("\n",
		"<!DOCTYPE html>",
		"<html>",
		"</head>",
		"	<meta charset=\"utf-8\">",
		"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">",
		"	<style>",
		"		p{font-size: 1em; font-family: \"Courier New\", monospaced;}",
		"		.literal{color : #004080;}",
		"		.comment{color: #009933;}",
		"		.reservedword {font-weight:bold;}" //strong tags can also be used.
		,
		 "	</style>",
		"</head>",
		"<body>",
		"	<p>"
	);

	public static String htmlCloser = String.join("\n",
		"	<p>",
		"</body>",
		"</html>"
	);

}
