package com.ntg.adm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ntg.adm.util.upload.UploadUtil;

@Service
public class ContentStorageService {
	
	@Value("${file.upload-dir}")
	private String basePath;
	
	public String storeFile(MultipartFile file, Integer userId, String docType) {
		return UploadUtil.storeFile(basePath, file, userId, docType);
	}
}
