package ovh.kevinsamyn.fastpoll.jpa.repositories.impl

import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity
import ovh.kevinsamyn.fastpoll.jpa.entities.UserEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.DBQuestionRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.QuestionRepository

open class JpaQuestionRepository(private val dbQuestionRepository: DBQuestionRepository) :
        QuestionRepository {

    fun create(pUser: UserEntity, pLabel: String): QuestionEntity {
        val question = QuestionEntity()
        question.user = pUser
        question.label = pLabel
        return dbQuestionRepository.save(question)
    }

    override fun findNotAnsweredAndCreateByFriend(userId: Long): QuestionEntity? {
        val questionId = dbQuestionRepository.findQuestionIdNotAnsweredAndCreateByFriend(userId)
        return findById(questionId)
    }

    override fun findNotAnsweredAndAnsweredByFriend(userId: Long): QuestionEntity? {
        val questionId = dbQuestionRepository.findQuestionIdNotAnsweredAndAnsweredByFriend(userId)
        return findById(questionId)
    }

    override fun findNotAnsweredWithMinAnswersCount(userId: Long): QuestionEntity? {
        val questionId = dbQuestionRepository.findQuestionIdNotAnsweredWithMinAnswersCount(userId)
        return findById(questionId)
    }

    override fun findCreatedByUserId(userId: Long): List<QuestionEntity> {
        return dbQuestionRepository.findCreatedByUserId(userId)
    }

    override fun findAnsweredByUserId(userId: Long): List<QuestionEntity> {
        return dbQuestionRepository.findAnsweredByUserId(userId)
    }

    fun findById(questionId: Long?): QuestionEntity? {
        if (null != questionId) {
            val optional = dbQuestionRepository.findById(questionId)
            if(optional.isPresent) {
                return optional.get()
            }
        }
        return null
    }



}