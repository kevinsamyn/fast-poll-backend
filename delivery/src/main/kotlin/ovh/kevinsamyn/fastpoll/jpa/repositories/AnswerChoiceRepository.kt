package ovh.kevinsamyn.fastpoll.jpa.repositories

import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerChoiceEntity
import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity

interface AnswerChoiceRepository {
    fun findById(answerChoiceId: Long?): AnswerChoiceEntity?
    fun create(question: QuestionEntity, label: String): AnswerChoiceEntity
}
