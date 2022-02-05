package com.ntg.adm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ntg.adm.service.ContentStorageService;
import com.ntg.adm.util.upload.UploadUtil;

@RestController("content")
public class ContentController {

	@Autowired
	private ContentStorageService contentStorageService;

	
	@PostMapping("/upload")
	public String uploadContent(@RequestParam("file") MultipartFile file, 
						        @RequestParam("userId") Integer UserId, 
						        @RequestParam("docType") String docType) {

		String fileName = contentStorageService.storeContent(file, UserId, docType);

		return fileName;
	}
	
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam("fileId") Long fileId,
												 HttpServletRequest request) {
		
		Resource resource = contentStorageService.downloadContent(fileId);
		String contentType = UploadUtil.getFileContent(request, resource);
		
		return ResponseEntity.ok()
				             .contentType(MediaType.parseMediaType(contentType))
				             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				             .body(resource);
	}
}
