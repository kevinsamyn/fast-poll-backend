package ovh.kevinsamyn.fastpoll.jpa.repositories

interface AnswerRepository {
    fun countByQuestionId(questionId: Long): Int
    fun countByQuestionIdAndAnswerChoiceId(questionId: Long, answerChoiceId: Long): Int
    fun existByQuestionIdAndAnswerChoiceIdAndUserId(questionId: Long, answerChoiceId: Long, userId: Long): Boolean
}
