package com.mindblower.friends.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Value;
import  com.mindblower.friends.Dto.ServiceAccount;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.net.HttpHeaders;
import com.mindblower.friends.config.AppConstants;
import com.mindblower.friends.services.StorageStrategy;

@Service
public class FirebaseStorageStrategy implements StorageStrategy {

	
	    private StorageOptions storageOptions;
	    private String bucketName;
	    private String projectId;
	   

	   
	    
	    
	    @PostConstruct
	    private void initializeFirebase() throws Exception {
	        bucketName = AppConstants.FIREBASE_BUCKET_NAME;
	        projectId = AppConstants.FIREBASE_PROJECT_ID;
	   
	        InputStream firebaseCredential = createFirebaseCredential();
//	        FileInputStream serviceAccount =
//	                new FileInputStream("/home/priyanshu/Downloads/friends-2166b-firebase-adminsdk-86841-1b82e503bf.json");

	        this.storageOptions = StorageOptions.newBuilder()
	                .setProjectId(projectId)
	                .setCredentials(GoogleCredentials.fromStream(firebaseCredential)).build();

	    }
	    

	    public String uploadFile(MultipartFile multipartFile) throws IOException {
	        File file = convertMultiPartToFile(multipartFile);
	        java.nio.file.Path filePath = file.toPath();
	        String objectName = generateFileName(multipartFile);

	        Storage storage =  storageOptions.getService();

	        BlobId blobId = BlobId.of(bucketName, objectName);
	       // BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(Map.of("firebaseStorageDownloadTokens","randomAccessToken")).build();
	        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
	        Blob blob = ((com.google.cloud.storage.Storage) storage).create(blobInfo, Files.readAllBytes(filePath));

	     // "fileUrl",blob.getMediaLink().concat("&token=randomAccessToken"
	        return objectName;
	    }
	    
	    private File convertMultiPartToFile(MultipartFile file) throws IOException {
	        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
	        FileOutputStream fos = new FileOutputStream(convertedFile);
	        fos.write(file.getBytes());
	        fos.close();
	        return convertedFile;
	    }

	    private String generateFileName(MultipartFile multiPart) {
	        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
	    }
	    
	    private InputStream createFirebaseCredential() throws Exception {
	       ServiceAccount firebaseCredential = new ServiceAccount();
	        
	        firebaseCredential.setType(AppConstants.FIREBASE_TYPE);
	        firebaseCredential.setProject_id(AppConstants.FIREBASE_PROJECT_ID);
	        firebaseCredential.setPrivate_key_id(AppConstants.FIREBASE_PRIVATE_KEY_ID);
	        firebaseCredential.setPrivate_key(AppConstants.FIREBASE_PRIVATE_KEY);
	        firebaseCredential.setClient_email(AppConstants.FIREBASE_CLIENT_EMAIL);
	        firebaseCredential.setClient_id(AppConstants.FIREBASE_CLIENT_ID);
	        firebaseCredential.setAuth_uri(AppConstants.FIREBASE_AUTH_URI);
	        firebaseCredential.setToken_uri(AppConstants.FIREBASE_TOKEN_URI);
	        firebaseCredential.setAuth_provider_x509_cert_url(AppConstants.FIREBASE_AUTH_PROVIDER_X509_CERT_URL);
	        firebaseCredential.setClient_x509_cert_url(AppConstants.FIREBASE_CLIENT_EMAIL);

	        ObjectMapper mapper = new ObjectMapper();
	        String jsonString = mapper.writeValueAsString(firebaseCredential);

	        //convert jsonString string to InputStream using Apache Commons
	        return IOUtils.toInputStream(jsonString);
	    }


		@Override
		public ResponseEntity<Object> downloadFile(String fileName) throws Exception {
			
			 Storage storage = storageOptions.getService();

		        Blob blob = storage.get(BlobId.of(bucketName, fileName));
		        ReadChannel reader = blob.reader();
		        InputStream inputStream = Channels.newInputStream(reader);

		        byte[] content = null;
		        System.out.println("File downloaded successfully.");

		        content = IOUtils.toByteArray(inputStream);

		        final ByteArrayResource byteArrayResource = new ByteArrayResource(content);

		        return ResponseEntity
		                .ok()
		                .contentLength(content.length)
		                .header("Content-type", "application/octet-stream")
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
		                .body(byteArrayResource);
			
		}

}
