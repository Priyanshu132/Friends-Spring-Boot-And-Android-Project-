package com.mindblower.friends.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StorageStrategy {
	
	public String uploadFile(MultipartFile multipartFile) throws IOException;
	
	public ResponseEntity<Object> downloadFile(String fileName) throws Exception;

}
