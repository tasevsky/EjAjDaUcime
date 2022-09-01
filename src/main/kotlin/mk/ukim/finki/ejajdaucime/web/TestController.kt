package mk.ukim.finki.ejajdaucime.web

import mk.ukim.finki.ejajdaucime.service.TestService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
@RequestMapping("/tests")
class TestController(private val testService: TestService) {

    @GetMapping("/eksperti")
    fun getTestExperts(model: Model): String? {
        val range = listOf(0, 1, 2, 3)
        model.addAttribute("range", range)
        model.addAttribute("questions", this.testService.findById(2L)!!.get().questions)
        println(this.testService.findById(2L)!!.get().questions)
        return "testEksperti"
    }

    @PostMapping("/certificate")
    fun getCertificate(
        @RequestParam(value = "checkboxName", required = false) checkboxValue: Array<String>?,
        model: Model
    ): String? {
        var result = -1.0
        if (checkboxValue != null) {
            result = testService.isPassedWithId(2L, checkboxValue)
        }
        if (result != -1.0) {
            model.addAttribute("passed", true)
            model.addAttribute("points", result.toInt())
        } else {
            model.addAttribute("passed", false)
        }
        return "certificates"
    }

}