package ovh.kevinsamyn.fastpoll.services

import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerChoiceEntity
import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaAnswerChoiceRepository

class AnswerChoicesService(private val answerChoiceRepository: JpaAnswerChoiceRepository) {
    fun create(question: QuestionEntity, label: String) : AnswerChoiceEntity{
        return answerChoiceRepository.create(question, label)
    }

}