package ovh.kevinsamyn.fastpoll.jpa.entities

import javax.persistence.*

@Entity
@Table(name = "t_e_answer_asw", uniqueConstraints = [ UniqueConstraint(columnNames = [ "qst_fk", "usr_fk" ]) ])
open class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ach_id")
    val id: Long = 0

    @ManyToOne(targetEntity = QuestionEntity::class, optional = false)
    @JoinColumn(name = "qst_fk")
    lateinit var question: QuestionEntity

    @ManyToOne(targetEntity = AnswerChoiceEntity::class, optional = true)
    @JoinColumn(name = "ach_fk")
    var choice: AnswerChoiceEntity? = null

    @ManyToOne(targetEntity = UserEntity::class, optional = false)
    @JoinColumn(name = "usr_fk")
    lateinit var user: UserEntity
}