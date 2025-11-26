package implementations;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import exceptions.XMLError;

/**
 * Class that holds all methods for the XML parser.
 * @author Estefano Campana
 * @version 1.0
 */
public class XMLParser 
{
	/**
	 * The underlying data structure used for parsing.
	 */
	private Deque<String> stack = new ArrayDeque<>();
	/**
	 * A queue of errors for the parser.
	 */
	private Queue<XMLError> errorQ = new LinkedList<>();
	/**
	 * A queue for extra tags found.
	 */
	private Queue<XMLError> extrasQ = new LinkedList<>();
	/**
	 * Integer for tracking the current line number.
	 */
	private int currentLine = 1;
	
	/**
	 * Method to parse a single XML line.
	 * @param line A string containing XML content.
	 * @author Estefano Campana
	 * @version 1.0
	 */
		public void parse(String line)
		{			
			int i = 0;
			//while i is less than the length of the line.
			while (i < line.length()) 
			{
				//search for the start of the tag.
				int start = line.indexOf('<', i);
				//if there is no starting character, break.
				if (start == -1) break;
				
				//search for the end of the tag.
				int end = line.indexOf('>',start);
				//if there is no end, break.
				if (end == -1) break;
				
				//gets the whole string inside the <> characters.
				String tag = line.substring(start + 1, end).trim();
				
				//checks if it is self closing or if it is a closing tag.
				processTag(tag);
				
				//increases the counter, so it can end after parsing the line.
				i = end + 1;
			}
		}
		/**
		 * Method used to check if a tag is self closing or if it a closing tag.
		 * @param tag A string containing the tag that is checked.
		 * @author Estefano Campana
		 * @version 1.0
		 */
		private void processTag(String tag) {
			//if the tag is self closing.
			boolean selfClosing = tag.endsWith("/");
			//if the tag is a closing tag.
			boolean endTag = tag.startsWith("/");
			//if it is self closing, ignore.
			if (selfClosing) 
			{
				return;
			}
			//if it is closing.
			if(endTag) 
			{
				//take the name of the tag, without the slash character.
				String tagName = tag.substring(1).trim();
				//add it into errorQ or extrasQ
				handleEndTag(tagName);
				return;
			}
			//gets the name of the tag if it neither a closing tag or a self closing tag.
			String tagName = getTagName(tag);
			//pushes it to stack.
			stack.push(tagName);
		}
		/**
		 * Method used to get the name of the tag, without any special characters.
		 * @param tag A string the tag.
		 * @return A string with only the name of the tag, without any special characters.
		 * @author Estefano Campana
		 * @version 1.0
		 */
		private String getTagName(String tag) 
		{
			//if the tag is a closing tag.
			if (tag.endsWith("/"))
			{
				//take all of the string until it reaches the slash character.
				tag = tag.substring(0, tag.length() - 1).trim();
			}
			//searches for the index where the attributes are.
			int spaceIndex = tag.indexOf(' ');
			//if it does not have any attribute, return.
			if(spaceIndex == -1) {
				return tag;
			}
			//otherwise, take the name until the index of the start of the attributes.
			return tag.substring(0, spaceIndex);
		}
		/**
		 * Method used to check if a tag should be input into errorQ or extrasQ.
		 * @param tag A string containing the name of the tag.
		 * @author Estefano Campana
		 * @version 1.0
		 */
		private void handleEndTag(String tag) 
		{
			//If stack is not empty and the head matches the tag.
			if(!stack.isEmpty() && stack.peek().equals(tag)) 
			{
				//removes the head.
				stack.pop();
				return;
			}

			//If the stack is empty.
			if(stack.isEmpty())
			{
				//Add the tag into the error queue.
				errorQ.add(new XMLError(tag, currentLine));
				return;
			}
			//If stack already contains this tag.
			if(stack.contains(tag)) 
			{
				//while the stack is not empty, and the head does not equal to the tag.
				while(!stack.isEmpty() && !stack.peek().equals(tag)) 
				{
					//Add the head of the stack into error queue.
					String unclosedTag = stack.pop();
					errorQ.add(new XMLError(unclosedTag, currentLine));
				}
				//if the stack is not empty.
				if(!stack.isEmpty()) 
				{
					//remove the head.
					stack.pop();
				}
			}
			//else add the tag to the extra queue.
			else 
			{
				errorQ.add(new XMLError(tag, currentLine));
			}
		}

	/**
	 * Method to increment the current line number.
	 * @author TerrellAW
	 */
		public void incrementLine() {
			currentLine++;
		}

	/**
	 * Method used to print the errors or the correct building of the XML document.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public void checkFile()
	{
		//while the stack is not empty.
		while(!stack.isEmpty()) 
		{
			//add all items into error queue.
			String unclosedTag = stack.pop();
			errorQ.add(new XMLError(unclosedTag, currentLine));
		}
		//if both the error queue and extras queue is empty.
		if(errorQ.isEmpty() && extrasQ.isEmpty()) 
		{
			 System.out.println("XML document is constructed correctly.");
			 return;
		}

		// print all errors
		while(!errorQ.isEmpty()) {
			System.out.println(errorQ.poll());
		}

	}
}
