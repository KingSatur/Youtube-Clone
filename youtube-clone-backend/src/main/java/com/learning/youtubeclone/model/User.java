package com.learning.youtubeclone.model;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String fullName;
	private Set<String> suscribedToUsers;
	private Set<String> suscribers;
	private List<String> videoHistory;
	private Set<String> likedVideos;
	private Set<String> dislikedVideos;

}
