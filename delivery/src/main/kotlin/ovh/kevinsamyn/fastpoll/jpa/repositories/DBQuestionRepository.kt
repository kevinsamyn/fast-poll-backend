package ovh.kevinsamyn.fastpoll.jpa.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity

interface DBQuestionRepository : JpaRepository<QuestionEntity, Long> {

    @Query("select qst.qst_id from t_e_question_qst qst " +
            "left join t_e_answer_asw asw on qst.qst_id = asw.qst_fk and asw.usr_fk = 2 " +
            "left join t_e_friendship_fsh fsh on fsh.usr_fk_with = qst.usr_fk " +
            "where asw.ach_id is null " +
            "and fsh.usr_fk_with is not null " +
            "and fsh.usr_fk = :userId", nativeQuery = true)
    fun findQuestionIdNotAnsweredAndCreateByFriend(@Param("userId") userId: Long): Long?

    @Query(value = "select qst.qst_id from t_e_question_qst qst " +
            "left join t_e_answer_asw asw on qst.qst_id = asw.qst_fk and asw.usr_fk = :userId " +
            "left join t_e_answer_asw friendAsw on qst.qst_id = friendAsw.qst_fk and friendAsw.usr_fk in (select usr_fk_with from t_e_friendship_fsh fsh where fsh.usr_fk = :userId) " +
            "where asw.ach_id is null and friendAsw.ach_id is not null", nativeQuery = true)
    fun findQuestionIdNotAnsweredAndAnsweredByFriend(@Param("userId") userId: Long): Long?

    @Query(value = "select qst_id, count(*) cnt from t_e_question_qst qst " +
            "    left join t_e_answer_asw asw on qst.qst_id = asw.qst_fk " +
            "    left join t_e_answer_asw userAsw on qst.qst_id = userAsw.qst_fk  and userAsw.usr_fk = :userId " +
            "    where userAsw.ach_id is null " +
            "    group by qst_id " +
            "    order by cnt " +
            "    limit 1", nativeQuery = true)
    fun findQuestionIdNotAnsweredWithMinAnswersCount(@Param("userId") userId: Long): Long?

    @Query(value = "select qst from QuestionEntity qst where qst.user.id = :userId")
    fun findCreatedByUserId(@Param("userId") userId: Long): List<QuestionEntity>

    @Query(value = "select asw.question from AnswerEntity asw where asw.user.id = :userId")
    fun findAnsweredByUserId(@Param("userId") userId: Long): List<QuestionEntity>

}