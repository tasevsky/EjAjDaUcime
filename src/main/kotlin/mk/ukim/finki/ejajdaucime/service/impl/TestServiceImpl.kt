package mk.ukim.finki.ejajdaucime.service.impl

import mk.ukim.finki.ejajdaucime.model.Test
import mk.ukim.finki.ejajdaucime.repository.TestRepository
import mk.ukim.finki.ejajdaucime.service.TestService
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestServiceImpl(private val testRepository: TestRepository) : TestService {

    override fun findById(id: Long?): Optional<Test?>? {
        return id?.let { this.testRepository.findById(it) }
    }

    override fun isPassedWithId(testId: Long, checkboxValues: Array<String>): Double {
        var total = 0
        val test: Test = findById(testId)!!.get()
        for (s in checkboxValues) {
            val question = s.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].toInt()
            val answer = s.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toInt()
            if (answer == 0 && test.questions[question].answers.isTrueFirstOption) {
                total += 1
            } else if (answer == 1 && test.questions[question].answers.isTrueSecondOption) {
                total += 1
            } else if (answer == 2 && test.questions[question].answers.isTrueThirdOption) {
                total += 1
            } else if (answer == 3 && test.questions[question].answers.isTrueFourthOption) {
                total += 1
            }
        }
        return if (total * 1.0 / test.totalTrueAnswers * 100 > test.minPoints) total * 1.0 / test.totalTrueAnswers * 100 else -1.0
    }
}