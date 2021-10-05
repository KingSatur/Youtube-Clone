package com.learning.youtubeclone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.youtubeclone.model.Video;

public interface VideoRepository extends MongoRepository<Video, String> {

}
