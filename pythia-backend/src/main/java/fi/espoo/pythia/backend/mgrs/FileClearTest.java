package fi.espoo.pythia.backend.mgrs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;

public class FileClearTest {

	public static void main(String[] args) {

		String dirPath = System.getProperty("user.dir");
		System.out.println(dirPath);

		File[] files = getFileList(dirPath);

		for (File f : files) {
			System.out.println(f.getName());
			 try {
				Files.deleteIfExists(f.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	private static File[] getFileList(String dirPath) {
		File dir = new File(dirPath);

		File[] fileList = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".pdf") || name.endsWith(".dwg");
			}
		});
		return fileList;
	}
}
