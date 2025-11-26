package exceptions;

/**
 * Class to handle XML parsing errors.
 *
 * @author TerrellAW
 */
public class XMLError
{
	String tagName;
	int lineNumber;

	/**
	 * Getter to get tag name from this class.
	 *
	 * @return Name of the XML tag.
	 * @author TerrellAW
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * Constructor that takes parameters to instantiate <code>XMLError</code>.
	 *
	 * @param tagName The name of the XML tag that caused the error.
	 * @param lineNumber The line number where the XML error occured.
	 * @author TerrellAW
	 */
	public XMLError(String tagName, int lineNumber) {
		this.tagName = tagName;
		this.lineNumber = lineNumber;
	}

	/**
	 * Overriden <code>toString</code> method to print error message.
	 *
	 * @return String containing error message with line number and tag.
	 * @author TerrellAW
	 */
	@Override
	public String toString() {
		return "Error at line " + lineNumber + ": <" + tagName + "> is not constructed correctly.";
	}
}
