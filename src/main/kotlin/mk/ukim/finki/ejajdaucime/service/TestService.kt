package mk.ukim.finki.ejajdaucime.service

import mk.ukim.finki.ejajdaucime.model.Test
import java.util.*

interface TestService {
    fun findById(id: Long?): Optional<Test?>?
    fun isPassedWithId(testId: Long, checkboxValues: Array<String>): Double
}