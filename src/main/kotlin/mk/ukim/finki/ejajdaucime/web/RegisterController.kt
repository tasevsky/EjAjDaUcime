package mk.ukim.finki.ejajdaucime.web

import mk.ukim.finki.ejajdaucime.model.Role
import mk.ukim.finki.ejajdaucime.model.exception.InvalidArgumentsException
import mk.ukim.finki.ejajdaucime.model.exception.PasswordsDoNotMatchException
import mk.ukim.finki.ejajdaucime.service.AuthService
import mk.ukim.finki.ejajdaucime.service.impl.UserServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
@RequestMapping("/register")
class RegisterController(private val userService: UserServiceImpl) {
    @GetMapping
    fun getRegisterPage(@RequestParam(required = false) error: String?, model: Model): String {
        if (error != null && error.isNotEmpty()) {
            model.addAttribute("hasError", true)
            model.addAttribute("error", error)
        }
        return "register"
    }

    @PostMapping
    fun register(
        @RequestParam username: String?,
        @RequestParam email: String?,
        @RequestParam password: String?,
        @RequestParam repeatedPassword: String?
    ): String {
        return try {
            println(username)
            println(email)
            println(password)
            println(repeatedPassword)
            if (email != null) {
                userService.register(username, email, password!!, repeatedPassword, Role.ROLE_USER)
            }
            "redirect:/login"
        } catch (exception: InvalidArgumentsException) {
            "redirect:/register?error=$exception"
        } catch (exception: PasswordsDoNotMatchException) {
            "redirect:/register?error=$exception"
        }
    }
}
