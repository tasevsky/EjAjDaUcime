package mk.ukim.finki.ejajdaucime.web

import mk.ukim.finki.ejajdaucime.service.ExportPdfService
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
@RequestMapping("/certificates")
class CertificatesController(private val exportPdfService: ExportPdfService) {

    @GetMapping
    fun getCertificates(model: Model, req: HttpServletRequest): String?
    {
//        val username: String = req.remoteUser
//        model.addAttribute("user", username)
        return "certificates"
    }

    @GetMapping("/downloadBasicCertificate")
    @Throws(IOException::class)
    fun downloadReceipt(response: HttpServletResponse) {
        val data: Map<String, Any> = createTestData()
        val exportedData = exportPdfService.exportReceiptPdf("certificate-basic", data)
        response.contentType = "application/octet-stream"
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf")
        IOUtils.copy(exportedData, response.outputStream)
    }

    fun createTestData(): Map<String, Any> {
        val data: Map<String, Any> = HashMap()
        return data
    }
}