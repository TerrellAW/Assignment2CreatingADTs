package implementations;

public class XMLParser {
	/**
	 * Method to create an arrayList containing objects that extend class ShapeObject based on a file.
	 * @author Estefano Campana
	 * @version 13-10-2025
	 * @param filePath An String containing the relative path to a file in the system.
	 * @return shapeObjects ArrayList that held objects of type ShapeObject.
	 */
		public static ArrayList<ShapeObject> readFile(String filePath)
		{
			//Uses the path to find the file.
			File shapesData = new File(filePath);
			Scanner content = null;
			//Initializes arrayList.
			ArrayList<ShapeObject> shapeObjects = new ArrayList<ShapeObject>();

			try
			{
				content = new Scanner(shapesData);
				//Skips first value on the list.
				content.nextLine();
			}
			//If there is no file found.
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			//While there is content in the file.
			while (content.hasNextLine())
			{
}
