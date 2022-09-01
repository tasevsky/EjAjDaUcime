package mk.ukim.finki.ejajdaucime.service

import java.io.ByteArrayInputStream


interface ExportPdfService {
    fun exportReceiptPdf(templateName: String?, data: Map<String, Any>?): ByteArrayInputStream?
}