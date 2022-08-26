package mk.ukim.finki.ejajdaucime.web

import mk.ukim.finki.ejajdaucime.model.Role
import mk.ukim.finki.ejajdaucime.model.User
import mk.ukim.finki.ejajdaucime.model.exception.InvalidArgumentsException
import mk.ukim.finki.ejajdaucime.model.exception.PasswordsDoNotMatchException
import mk.ukim.finki.ejajdaucime.security.UserDetailsImpl
import mk.ukim.finki.ejajdaucime.service.impl.UserServiceImpl
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/profile")
class ProfileController(private val userService: UserServiceImpl)
{

    @GetMapping
    fun profile(request: HttpServletRequest, model: Model, @AuthenticationPrincipal user: UserDetailsImpl): String {
        model.addAttribute("user", user)
        return "profile"
    }

    @PostMapping
    fun save(
        @AuthenticationPrincipal user: UserDetailsImpl,
        @RequestParam username: String?,
        @RequestParam email: String?,
        @RequestParam password: String?,
        @RequestParam repeatedPassword: String?
    ) : String {
        return try {
            println(username)
            println(email)
            println(password)
            println(repeatedPassword)
            if (email != null) {
                userService.edit(user.id, username!!, email, password!!, repeatedPassword, Role.ROLE_USER)
            }
            "redirect:/login"
        } catch (exception: InvalidArgumentsException) {
            "redirect:/register?error=$exception"
        } catch (exception: PasswordsDoNotMatchException) {
            "redirect:/register?error=$exception"
        }
    }


}