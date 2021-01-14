package ovh.kevinsamyn.fastpoll.jpa.repositories.impl

import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerChoiceEntity
import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerEntity
import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity
import ovh.kevinsamyn.fastpoll.jpa.entities.UserEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.AnswerRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.DBAnswerRepository

open class JpaAnswerRepository(private val dbAnswerRepository: DBAnswerRepository) :
        AnswerRepository {

    fun create(question: QuestionEntity, answerChoice: AnswerChoiceEntity?, user: UserEntity) {
        val asw = AnswerEntity()
        asw.question = question
        asw.choice = answerChoice
        asw.user = user

        dbAnswerRepository.save(asw)
    }

    override fun countByQuestionId(questionId: Long): Int {
        return dbAnswerRepository.countByQuestionId(questionId)
    }

    override fun countByQuestionIdAndAnswerChoiceId(questionId: Long, answerChoiceId: Long) : Int {
        return dbAnswerRepository.countByQuestionIdAndAnswerChoiceId(questionId, answerChoiceId)
    }

    override fun existByQuestionIdAndAnswerChoiceIdAndUserId(questionId: Long, answerChoiceId: Long, userId: Long) : Boolean {
        val optional = dbAnswerRepository.findByQuestionIdAndAnswerChoiceIdAndUserId(questionId, answerChoiceId, userId)
        return optional.isPresent
    }

}