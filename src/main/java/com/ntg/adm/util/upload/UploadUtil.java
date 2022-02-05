package com.ntg.adm.util.upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {

	
	/**
	 * 
	 * @param basePath
	 * @param file
	 * @param userId
	 * @param docType
	 * @return
	 */
	public static String storeFile(String basePath, MultipartFile file, Integer userId, String docType) {

		Path fileStorageLocation = createPath(basePath);
				
		// Normalize file name
		String oldOriginalFilename = file.getOriginalFilename();
		String originalFileName = StringUtils.cleanPath(oldOriginalFilename);

		String fileName = "";
		String fileExtension = "";
		
		try {
			try {
				fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			} catch (Exception e) {
				fileExtension = "";
			}

			fileName = userId + "_" + docType + fileExtension;

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = fileStorageLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		
			return fileName;

		} catch (IOException ex) {
			throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	/**
	 * 
	 * @param basePath
	 * @return
	 */
	private static Path createPath(String basePath) {
		Path fileStorageLocation = Paths.get(basePath).toAbsolutePath().normalize();
			
        try {
            return Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
	}
}
