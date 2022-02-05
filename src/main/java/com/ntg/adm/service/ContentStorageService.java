package com.ntg.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ntg.adm.dao.AdmContentStoragePropertiesRepository;
import com.ntg.adm.model.AdmContentStorageProperties;
import com.ntg.adm.util.upload.UploadUtil;

@Service
public class ContentStorageService {
	
	@Value("${content.upload-dir}")
	private String basePath;
	
	@Autowired
	private AdmContentStoragePropertiesRepository admContentStoragePropertiesRepository;
	
	/**
	 * 
	 * @param file
	 * @param userId
	 * @param contentType
	 * @return
	 */
	public String storeContent(MultipartFile file, Integer userId, String contentType) {
		String[] output = UploadUtil.storeFile(basePath, file, userId, contentType);
		
		//return new String[] {originalFileName, fileExtension, fileFullName, contentType};
		AdmContentStorageProperties admContentStorageProperties = new AdmContentStorageProperties();
		admContentStorageProperties.setContentName(output[0]);
		admContentStorageProperties.setContentFormat(output[1]);
		admContentStorageProperties.setContentType(contentType.toUpperCase());
		admContentStorageProperties.setContentDirectory(output[2]);
		
		admContentStoragePropertiesRepository.save(admContentStorageProperties);
		
		return output[2];
	}
	
	/**
	 * 
	 * @param fileName
	 * @param docType
	 * @return
	 */
	public Resource downloadContent(Long fileId) {
		Resource resource = null;
		try {
			AdmContentStorageProperties admContentStorageProperties = admContentStoragePropertiesRepository.getFileByContentId(fileId);
			resource = UploadUtil.loadFileAsResource(admContentStorageProperties.getContentDirectory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resource;
	}
}
