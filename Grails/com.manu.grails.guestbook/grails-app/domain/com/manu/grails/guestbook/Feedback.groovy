package com.manu.grails.guestbook

class Feedback {

	String title
	String feedback
	Date dateCreated // Predefined names by Grails will be filled automatically
	Date lastUpdated // Predefined names by Grails will be filled automatically

	// Relationsship to the other classes
	User user
	static hasMany=[comments:Comment]

	// Contrains are defined as static
	static constraints = {
		title(blank:false, nullable: false, size:3..80)
		feedback(blank:false, nullable:false,size:3..500)
		user(nullable:false)
	}
}
