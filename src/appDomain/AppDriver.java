package appDomain;

import java.io.FileReader;
import java.io.IOException;
import implementations.XMLParser;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304
 * F2025 class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
 */
public class AppDriver
{

	/**
	 * Uses command line arguments to determine good user input.
	 *
	 * @author TerrellAW
	 * @author Estefano Campana
	 * @version 1.1
	 * @param args Command line arguments taken from main.
	 * @param config Config class object that contains all arguments from user.
	 */
	public static void argSwitch(String[] args, Config config) {
		//if there is more than 1 argument.
		if(args.length != 1) 
		{
			System.err.println("Usage: java -jar Parjer.jar <file.xml>.");
			System.exit(1);
		}
		//takes the first argument
		String arg = args[0];
		
		//if it is not an XML file.
		if(!arg.toLowerCase().endsWith(".xml")) {
			System.err.println("Error: input file must be an .xml file.");
			System.exit(1);
		}
		
		//set the config path to be this argument.
		config.setFilePath(arg);
	}

	/**
	 *  The main method is the entry point of the application.
	 *
	 *  @param args The input to control the execution of the application.
	 */
	public static void main( String[] args )
	{
		//Creates a new object containing the client's arguments of choice.
		Config config = new Config();
		
		//Switches the methods to sort depending on the input.
		argSwitch(args, config);
		
		//try catch block to ensure that the file exists.
		try(java.io.BufferedReader br = new java.io.BufferedReader(new FileReader(config.getFilePath())))
		{
			String line;
			//Creates a new instance of the XML parser.
			XMLParser parser = new XMLParser();
			//skips first line of the document.
			br.readLine();
			//while there is content, parse each line.
			while((line = br.readLine()) != null)
			{
				parser.parse(line);
				parser.incrementLine();
			}
			//print results or errors.
			parser.checkFile();
		}
		catch(IOException e) 
		{
			System.err.println(e.getMessage());
		}
	}


}
