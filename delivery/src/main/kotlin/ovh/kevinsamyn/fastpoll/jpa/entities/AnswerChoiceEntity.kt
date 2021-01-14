package ovh.kevinsamyn.fastpoll.jpa.entities

import javax.persistence.*

@Entity
@Table(name = "t_e_answer_choice_ach")
data class AnswerChoiceEntity(@Column(name = "ach_label") var label: String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ach_id")
    val id: Long = 0

    @ManyToOne(targetEntity = QuestionEntity::class)
    @JoinColumn(name = "qst_fk")
    lateinit var question: QuestionEntity
}