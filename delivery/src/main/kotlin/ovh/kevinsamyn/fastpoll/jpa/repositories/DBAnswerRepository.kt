package ovh.kevinsamyn.fastpoll.jpa.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerEntity
import java.util.*

interface DBAnswerRepository : JpaRepository<AnswerEntity, Long> {

    @Query("select count(asw) from AnswerEntity asw where asw.question.id = :questionId")
    fun countByQuestionId(@Param("questionId") questionId: Long): Int

    @Query("select count(asw) from AnswerEntity asw where asw.question.id = :questionId and asw.choice.id = :answerChoiceId")
    fun countByQuestionIdAndAnswerChoiceId(@Param("questionId") questionId: Long, @Param("answerChoiceId") answerChoiceId: Long): Int

    @Query("select asw from AnswerEntity asw where asw.question.id = :questionId and asw.choice.id = :answerChoiceId and asw.user.id = :userId")
    fun findByQuestionIdAndAnswerChoiceIdAndUserId(@Param("questionId") questionId: Long, @Param("answerChoiceId") answerChoiceId: Long, @Param("userId") userId: Long): Optional<AnswerEntity>
}