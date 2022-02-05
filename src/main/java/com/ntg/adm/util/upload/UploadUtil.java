package com.ntg.adm.util.upload;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
	public static String[] storeFile(String basePath, MultipartFile file, Integer userId, String contentType) {

		Path fileStorageLocation = createBaseDirectoryBath(basePath);
				
		// Normalize file name
		String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

		String fileFullName = "";
		String fileExtension = "";
		
		try {
			try {
				fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			} catch (Exception e) {
				fileExtension = "";
			}

			fileFullName = userId + "_" + contentType + fileExtension;

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = fileStorageLocation.resolve(fileFullName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		
			return new String[] {fileFullName, fileExtension.replace(".", ""), targetLocation.toString(), contentType};

		} catch (IOException ex) {
			throw new RuntimeException("Could not store file " + fileFullName + ". Please try again!", ex);
		}
	}
	
	/**
	 * 
	 * @param basePath
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Resource loadFileAsResource(String fileDirectory) throws Exception {
		Path filePath = null;
        try {

            filePath = getFilePath(fileDirectory).normalize();
            
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + filePath);
            }

        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + filePath);
        }
    }

	/**
	 * 
	 * @param basePath
	 * @return
	 */
	private static Path createBaseDirectoryBath(String path) {
		Path fileStorageLocation = Paths.get(path).toAbsolutePath().normalize();
			
        try {
            return Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
	}
	
	private static Path getFilePath(String fileDirectory) {
		Path fileStorageLocation = Path.of(fileDirectory);
		
		return fileStorageLocation;
	}
	
	/**
	 * 
	 * @param request
	 * @param resource
	 * @return
	 */
	public static String getFileContent(HttpServletRequest request, Resource resource) {
		String contentType = null;

		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return contentType;
	}
}
