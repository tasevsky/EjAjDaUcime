package mk.ukim.finki.ejajdaucime

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/login")
class LoginController {

    @GetMapping
    fun getLoginPage(model: Model): String? {
        model.addAttribute("bodyContent", "login")
        return "login"
    }

}