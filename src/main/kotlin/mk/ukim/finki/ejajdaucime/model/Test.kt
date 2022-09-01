package mk.ukim.finki.ejajdaucime.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tests")
data class Test(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotBlank
    val name: String,

    @NotBlank
    val minPoints: Long,

    @OneToMany
    val questions: List<TestQuestion>,

    @NotBlank
    val totalTrueAnswers: Long,

) {
}