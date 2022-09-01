package mk.ukim.finki.ejajdaucime.service.impl


import mk.ukim.finki.ejajdaucime.service.ExportPdfService
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


@Service
class ExportPdfServiceImpl(private val templateEngine: TemplateEngine) : ExportPdfService {

    override fun exportReceiptPdf(templateName: String?, data: Map<String, Any>?): ByteArrayInputStream? {
        val context = Context()
        context.setVariables(data)
        val htmlContent: String = templateEngine.process(templateName, context)
        var byteArrayInputStream: ByteArrayInputStream? = null
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            val renderer = ITextRenderer()
            renderer.setDocumentFromString(htmlContent)
            renderer.layout()
            renderer.createPDF(byteArrayOutputStream, false)
            renderer.finishPDF()
            byteArrayInputStream = ByteArrayInputStream(byteArrayOutputStream.toByteArray())
        } catch (_: Exception) {
        }
        return byteArrayInputStream
    }

}