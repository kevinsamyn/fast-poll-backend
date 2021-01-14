package ovh.kevinsamyn.fastpoll.jpa.repositories.impl

import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerChoiceEntity
import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.AnswerChoiceRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.DBAnswerChoiceRepository

open class JpaAnswerChoiceRepository(private val dbAnswerChoiceRepository: DBAnswerChoiceRepository) :
        AnswerChoiceRepository {
    override fun findById(answerChoiceId: Long?): AnswerChoiceEntity? {
        if(answerChoiceId != null){
            val optional = dbAnswerChoiceRepository.findById(answerChoiceId)
            if(optional.isPresent){
                return optional.get()
            }
        }
        return null
    }

    override fun create(question: QuestionEntity, label: String): AnswerChoiceEntity {
        val ach = AnswerChoiceEntity()
        ach.label = label
        ach.question= question
        return dbAnswerChoiceRepository.save(ach)
    }

}