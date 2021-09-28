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
		"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">",
		"	<style>",
		"		p{font-size: 1em; font-family: \"Consolas\", \"Courier New\", monospaced;}",
		"		.literal{color : #0000cd;}",
		"		.comment{color: #00b300;}",
		"		.reservedword {font-weight:bold;}" //strong tags can also be used.
		,
		 "	</style>",
		"</head>",
		"<body class=\"d-flex flex-column min-vh-100\">",
		"	<div class=\"container\">",
		"		<div class=\"bg-light p-5 rounded-lg m-3\">",
		"			<p>"
	);

	public static String htmlCloser = String.join("\n",
		"			</p>",
		"		</div>",
		"	</div>",
		"	<footer class=\"footer mt-auto py-3 bg-light\">",
		"	  <div class=\"d-flex justify-content-between\">",
		"	    <span class=\"text-muted px-5\">A. Rivera, A. Cornejo y K. Corrales</span>",
		"	    <span class=\"text-muted px-5\">Compiladores e interpretes - ITCR - 2021</span>",
		"	  </div>",
		"	</footer>",
		"</body>",
		"</html>"
	);

}
