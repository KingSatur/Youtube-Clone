package com.learning.youtubeclone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.youtubeclone.dto.UploadVideoResponse;
import com.learning.youtubeclone.dto.VideoDto;
import com.learning.youtubeclone.service.VideoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
//With this annotation we allow this controller to listen to request from any origin. Using WebMvcConfig is also possible to configure Cors.
//@CrossOrigin("*")
public class VideoController {

	private final VideoService videoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UploadVideoResponse uploadVideo(
			@RequestParam("file") MultipartFile video) {
		return this.videoService.uploadVideo(video);
	}

	@PostMapping("/thumbnail")
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadThumbnail(@RequestParam("file") MultipartFile video,
			@RequestParam("videoId") String videoId) {
		return this.videoService.uploadThumbnail(video, videoId);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public VideoDto editVideoMetadata(@RequestBody VideoDto data) {
		return this.videoService.editVideo(data);
	}

}
