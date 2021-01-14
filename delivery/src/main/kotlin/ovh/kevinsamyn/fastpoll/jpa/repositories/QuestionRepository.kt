package ovh.kevinsamyn.fastpoll.jpa.repositories

import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity

interface QuestionRepository {
    fun findNotAnsweredAndCreateByFriend(userId: Long): QuestionEntity?
    fun findNotAnsweredAndAnsweredByFriend(userId: Long): QuestionEntity?
    fun findNotAnsweredWithMinAnswersCount(userId: Long): QuestionEntity?
    fun findCreatedByUserId(userId: Long): List<QuestionEntity>
    fun findAnsweredByUserId(userId: Long): List<QuestionEntity>
}
