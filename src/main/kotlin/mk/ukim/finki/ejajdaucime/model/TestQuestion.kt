package mk.ukim.finki.ejajdaucime.model

import javax.persistence.*


@Entity
@Table(name = "questions")
data class TestQuestion (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val question: String,

    @OneToOne
    val answers: TestAnswer,

){}