package com.manu.grails.guestbook

class HelloController {

	def index() { 
		render "Hello World"
	}
	def hi() { 
		render "How are you?"
		redirect(action: "index")
	}
}
