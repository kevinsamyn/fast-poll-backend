package ovh.kevinsamyn.fastpoll.jpa.entities

import javax.persistence.*

@Entity
@Table(name = "t_e_question_qst")
data class QuestionEntity(@Column(name = "qst_label") var label: String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qst_id")
    val id: Long = 0

    @OneToMany(mappedBy = "question", targetEntity = AnswerChoiceEntity::class, fetch = FetchType.EAGER)
    val choices = listOf<AnswerChoiceEntity>()

    @ManyToOne(targetEntity = UserEntity::class, optional = false)
    @JoinColumn(name = "usr_fk")
    lateinit var user: UserEntity
}