package com.learning.youtubeclone.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.youtubeclone.dto.UploadVideoResponse;
import com.learning.youtubeclone.dto.VideoDto;
import com.learning.youtubeclone.model.Video;
import com.learning.youtubeclone.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService {

	private final S3Service s3service;
	private final VideoRepository videoRepository;

	public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
		String videoUrl = this.s3service.uploadFile(multipartFile);
		Video video = new Video();
		video.setVideoUrl(videoUrl);
		video = this.videoRepository.save(video);
		return new UploadVideoResponse(video.getId(), videoUrl);
	}

	public VideoDto editVideo(VideoDto data) {
		// Find the video
		Video videoDb = videoRepository.findById(data.getId())
				.orElseThrow(() -> new IllegalArgumentException(
						"Cannot find video with that id"));

		videoDb.setTitle(data.getTitle());
		videoDb.setDescription(data.getDescription());
		videoDb.setTags(data.getTags());
		videoDb.setThumbailUrl(data.getThumbailUrl());
		videoDb.setVideoStatus(data.getVideoStatus());

		this.videoRepository.save(videoDb);
		return data;

		// TODO Auto-generated method stub
	}

	public String uploadThumbnail(MultipartFile image, String videoId) {
		Video videoDb = videoRepository.findById(videoId)
				.orElseThrow(() -> new IllegalArgumentException(
						"Cannot find video with that id"));
		String thumbnailUrl = this.s3service.uploadFile(image);
		videoDb.setThumbailUrl(thumbnailUrl);
		this.videoRepository.save(videoDb);
		return thumbnailUrl;

	}

}
