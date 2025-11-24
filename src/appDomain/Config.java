package appDomain;

/**
 * Contains fields and methods for configuring the program with command line arguments.
 *
 * @author TerrellAW
 * @version 13-10-2025
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