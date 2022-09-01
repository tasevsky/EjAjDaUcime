package mk.ukim.finki.ejajdaucime.model

import javax.persistence.*

@Entity
@Table(name = "answers")
data class TestAnswer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val firstOption: String,
    val secondOption: String,
    val thirdOption: String,
    val fourthOption: String,

    val isTrueFirstOption: Boolean,
    val isTrueSecondOption: Boolean,
    val isTrueThirdOption: Boolean,
    val isTrueFourthOption: Boolean,

) {

    fun getAllOptions(): List<String> {
        return listOf(firstOption, secondOption, thirdOption, fourthOption)
    }
}