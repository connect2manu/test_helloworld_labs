package grails.cookie.demo

import javax.servlet.http.Cookie

class CookieExampleController {

    def cookieService

    def index() {
        def cookieValueFromService = cookieService.getCookie('username')
        def cookieValueFromResponse = request.getCookie('username')
        [
                cookieValueFromService: cookieValueFromService,
                cookieValueFromResponse: cookieValueFromResponse
        ]
    }

    def setCookie1(String cookieValue) {
        cookieService.setCookie('username', cookieValue)
        redirect(action: 'index')
    }

    def setCookie2(String cookieValue) {
        Cookie cookie = new Cookie('username', cookieValue)
        cookieService.setCookie(cookie)
        redirect(action: 'index')
    }

    def setCookie3(String cookieValue) {
        response.setCookie('username', cookieValue, 36000)
        redirect(action: 'index')
    }

    def deleteCookie1() {
        cookieService.deleteCookie('username')
        redirect(action: 'index')
    }

    def deleteCookie2() {
        response.deleteCookie('username')
        redirect(action: 'index')
    }

}
