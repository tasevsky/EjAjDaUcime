package mk.ukim.finki.ejajdaucime.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/logout")
class LogoutController {

    @GetMapping
    fun logout(request: HttpServletRequest): String {
        request.session.invalidate()
        return "redirect:/login"
    }
}
