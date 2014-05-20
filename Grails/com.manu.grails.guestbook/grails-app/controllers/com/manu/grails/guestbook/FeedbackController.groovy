package com.manu.grails.guestbook

class FeedbackController {
	
	// only change here
//	def scaffold = true

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [feedbackInstanceList: Feedback.list(params), feedbackInstanceTotal: Feedback.count()]
    }

    def create = {
        def feedbackInstance = new Feedback()
        feedbackInstance.properties = params
        return [feedbackInstance: feedbackInstance]
    }

    def save = {
        def feedbackInstance = new Feedback(params)
        if (feedbackInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'feedback.label', default: 'Feedback'), feedbackInstance.id])}"
            redirect(action: "show", id: feedbackInstance.id)
        }
        else {
            render(view: "create", model: [feedbackInstance: feedbackInstance])
        }
    }

    def show = {
        def feedbackInstance = Feedback.get(params.id)
        if (!feedbackInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), params.id])}"
            redirect(action: "list")
        }
        else {
            [feedbackInstance: feedbackInstance]
        }
    }

    def edit = {
        def feedbackInstance = Feedback.get(params.id)
        if (!feedbackInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [feedbackInstance: feedbackInstance]
        }
    }

    def update = {
        def feedbackInstance = Feedback.get(params.id)
        if (feedbackInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (feedbackInstance.version > version) {
                    
                    feedbackInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'feedback.label', default: 'Feedback')] as Object[], "Another user has updated this Feedback while you were editing")
                    render(view: "edit", model: [feedbackInstance: feedbackInstance])
                    return
                }
            }
            feedbackInstance.properties = params
            if (!feedbackInstance.hasErrors() && feedbackInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'feedback.label', default: 'Feedback'), feedbackInstance.id])}"
                redirect(action: "show", id: feedbackInstance.id)
            }
            else {
                render(view: "edit", model: [feedbackInstance: feedbackInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def feedbackInstance = Feedback.get(params.id)
        if (feedbackInstance) {
            try {
                feedbackInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'feedback.label', default: 'Feedback'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'feedback.label', default: 'Feedback'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), params.id])}"
            redirect(action: "list")
        }
    }
}
