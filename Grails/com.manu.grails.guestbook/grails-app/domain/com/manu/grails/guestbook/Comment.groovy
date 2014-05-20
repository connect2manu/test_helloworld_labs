package com.manu.grails.guestbook

class Comment {

	String comment
	Date dateCreated // Predefined names by Grails will be filled automatically
	Date lastUpdated // Predefined names by Grails will be filled automatically

	User user;
	// This will make sure that all comments for a feedback are deleted in case the feedback item is deleted
	static belongsTo=[feedback:Feedback]

	static constraints = {
		comment (blank:false, nullable: false, size:5..500)
		user (nullable: true) // Comments are allowed without a user
	}

	String toString(){
		if (comment.size()>20){
			return comment.substring(0,19);
		} else
			return comment;
	}
}
