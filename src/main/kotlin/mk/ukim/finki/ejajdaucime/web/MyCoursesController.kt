package mk.ukim.finki.ejajdaucime.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/myCourses")
class MyCoursesController {

    @GetMapping
    fun getMyCourses(model: Model, req: HttpServletRequest): String?
    {
//        val username: String = req.remoteUser
//        model.addAttribute("user", username)
        return "myCourses"
    }
}