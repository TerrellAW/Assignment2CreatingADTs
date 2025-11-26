package exceptions;

/**
 *
 */
public class XMLError
{
	String tagName;
	int lineNumber;

	public String getTagName() {
		return tagName;
	}

	/**
	 *
	 */
	public XMLError(String tagName, int lineNumber) {
		this.tagName = tagName;
		this.lineNumber = lineNumber;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Error at line " + lineNumber + ": <" + tagName + "> is not constructed correctly.";
	}
}
