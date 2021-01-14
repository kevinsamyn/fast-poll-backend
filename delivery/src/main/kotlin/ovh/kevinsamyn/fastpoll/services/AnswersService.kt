package ovh.kevinsamyn.fastpoll.services

import ovh.kevinsamyn.fastpoll.exceptions.impl.AnswerChoiceNotFound
import ovh.kevinsamyn.fastpoll.exceptions.impl.QuestionNotFound
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerChoiceEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaAnswerChoiceRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaAnswerRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaQuestionRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaUserRepository

class AnswersService(private val answerRepository: JpaAnswerRepository, private val questionRepository: JpaQuestionRepository, private val answerChoiceRepository: JpaAnswerChoiceRepository, private val userRepository: JpaUserRepository) {

    fun create(questionId: Long, answerChoiceId: Long, userId: Long) {
        val user = userRepository.findById(userId) ?: throw UserNotFound()
        val question = questionRepository.findById(questionId) ?: throw QuestionNotFound()

        var answerChoice: AnswerChoiceEntity? = null
        if (answerChoiceId > 0) {
            answerChoice = answerChoiceRepository.findById(answerChoiceId) ?: throw AnswerChoiceNotFound()
        }
        
        answerRepository.create(
                question,
                answerChoice,
                user
        )
    }

    fun countByQuestionId(questionId: Long): Int {
        return answerRepository.countByQuestionId(questionId)
    }

    fun percentByQuestionIdAndAnswerChoiceId(questionId: Long, answerChoiceId: Long): Float {

        val answersCount = countByQuestionId(questionId)
        if (answersCount == 0) {
            return 0.0F
        }

        return countByQuestionIdAndAnswerChoiceId(questionId, answerChoiceId) / (answersCount * 1.0F) * 100.0F
    }

    private fun countByQuestionIdAndAnswerChoiceId(questionId: Long, answerChoiceId: Long): Int {
        return answerRepository.countByQuestionIdAndAnswerChoiceId(questionId, answerChoiceId)
    }

    fun existByQuestionIdAndAnswerChoiceIdAndUserId(questionId: Long, answerChoiceId: Long, userId: Long): Boolean {
        return answerRepository.existByQuestionIdAndAnswerChoiceIdAndUserId(questionId, answerChoiceId, userId)
    }

}