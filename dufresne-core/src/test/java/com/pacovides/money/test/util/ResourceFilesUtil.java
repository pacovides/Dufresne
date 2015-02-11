package com.pacovides.money.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

/**
 * Simple utilities to manage resource files.
 * 
 * @author Francisco
 *
 */
public class ResourceFilesUtil {

	/**
	 * Gets the path to the resource folder
	 * 
	 * @return
	 */
	public static String getResourceFolderPath() {
		return ResourceFilesUtil.class.getResource("/").getPath();
	}

	/**
	 * returns a file object from the resources folder. It does not guarantee
	 * the file exists.
	 * 
	 * @param relativePath
	 * @return
	 */
	public static File getResourceFile(String relativePath) {
		return new File(getResourceFolderPath(), relativePath);
	}

	/**
	 * returns a single string with the contents of a resource file
	 * 
	 * @param relativePath
	 * @return
	 * @throws IOException
	 */
	public static String getResourceFileAsString(String relativePath) throws IOException {
		return getResourceFileAsString(getResourceFile(relativePath));
	}

	/**
	 * returns a single string with the contents of a resource file
	 * 
	 * @param relativePath
	 * @return
	 * @throws IOException
	 */
	public static String getResourceFileAsString(File file) throws IOException {
		if (file == null) {
			throw new IOException("cannot read null file");
		}
		return IOUtils.toString(file.toURI());
	}

}
