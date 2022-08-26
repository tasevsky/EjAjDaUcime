package mk.ukim.finki.ejajdaucime.web

import mk.ukim.finki.ejajdaucime.model.User
import mk.ukim.finki.ejajdaucime.model.exception.InvalidUserCredentialsException
import mk.ukim.finki.ejajdaucime.service.AuthService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/login")
class LoginController(private val authService: AuthService) {

    @GetMapping
    fun getLoginPage(model: Model): String? {
        return "login"
    }

    @PostMapping
    fun login(request: HttpServletRequest, model: Model): String? {
        var user: User? = null
        return try {
            user = this.authService.login(
                request.getParameter("username"),
                request.getParameter("password")
            )
            request.session.setAttribute("user", user)
            "redirect:/home"
        } catch (exception: InvalidUserCredentialsException) {
            model.addAttribute("hasError", true)
            model.addAttribute("error", exception)
            "login"
        }
    }

}