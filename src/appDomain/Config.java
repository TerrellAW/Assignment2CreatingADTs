package appDomain;

/**
 * Contains fields and methods for configuring the program with command line arguments.
 * @author TerrellAW
 * @author Estefano Campana
 * @version 1.1
 */
public class Config {
	private String filePath;

	// Setters

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	// Getters

	public String getFilePath() {
		return filePath;
	}

}