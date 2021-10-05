package com.learning.youtubeclone.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Embbeded document
public class Comment {

	@Id
	private String id;
	private String text;
	private String userId;
	private Integer likeCount;
	private Integer dislikeCount;
}
