package com.learning.youtubeclone.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service implements FileService {

	private final AmazonS3Client s3Client;
	private static final String BUCKET_NAME = "video-app-learning";

	public S3Service(AmazonS3Client s3Client) {
		this.s3Client = s3Client;
	}

	@Override
	public String uploadFile(MultipartFile file) {
		String fileNameExtension = StringUtils
				.getFilenameExtension(file.getOriginalFilename());

		String fileKey = UUID.randomUUID().toString() + "." + fileNameExtension;
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentLength(file.getSize());
		metaData.setContentType(file.getContentType());

		try {
			this.s3Client.putObject(BUCKET_NAME, fileKey, file.getInputStream(),
					metaData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception uploading the file");

		}

		this.s3Client.setObjectAcl(BUCKET_NAME, fileKey,
				CannedAccessControlList.PublicRead);

		return this.s3Client.getResourceUrl(BUCKET_NAME, fileKey);

	}

}
