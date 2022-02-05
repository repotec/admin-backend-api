package com.ntg.adm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ntg.adm.service.ContentStorageService;

@RestController("content")
public class ContentController {

	@Autowired
	private ContentStorageService contentStorageService;

	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, 
						     @RequestParam("userId") Integer UserId, 
						     @RequestParam("docType") String docType) {

		String fileName = contentStorageService.storeFile(file, UserId, docType);

		return fileName;
	}
}
