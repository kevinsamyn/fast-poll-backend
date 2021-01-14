package ovh.kevinsamyn.fastpoll.resources.impl

import ovh.kevinsamyn.fastpoll.dto.request.PostQuestionRequestDto
import ovh.kevinsamyn.fastpoll.dto.response.GetQuestionResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PostQuestionResponseDto
import ovh.kevinsamyn.fastpoll.exceptions.impl.NoAnsweredQuestionNotFound
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.jpa.repositories.UserRepository
import ovh.kevinsamyn.fastpoll.mappers.AnswerChoiceMapper
import ovh.kevinsamyn.fastpoll.mappers.QuestionMapper
import ovh.kevinsamyn.fastpoll.resources.QuestionsResource
import ovh.kevinsamyn.fastpoll.services.QuestionsService

class QuestionsResourceImpl(val questionsService: QuestionsService, val userRepo: UserRepository
) : QuestionsResource {
    /**
     * Retourne une question ou null.
     * La question est sans réponse de l'utilisateur connecté et en prioriré
     * 1 - Une question posée par un ami
     * 2 - Une question a laquelle à répondu un ami
     * 3 - Une question avec le moins de réponse à l'instant t.
     */
    override fun get(login: String): GetQuestionResponseDto {
        val loggedUser = userRepo.findOneByLogin(login) ?: throw UserNotFound()

        // 1 - Une question posée par un ami
        val mappingOptions = QuestionMapper.Options()
        mappingOptions.choices = true
        mappingOptions.choicesOptions = AnswerChoiceMapper.Options(pCheckBy = null, pPercent = false)
        mappingOptions.answersCount = true

        var questionPair = questionsService.findNotAnsweredAndCreateByFriend(loggedUser.id, mappingOptions)

        // 2 - Une question a laquelle à répondu un ami
        //questionPair = null
        if (questionPair == null)
            questionPair = questionsService.findNotAnsweredAndAnsweredByFriend(loggedUser.id, mappingOptions)

        // 3 - Une question avec le moins de réponse à l'instant t.
        if (questionPair == null)
            questionPair = questionsService.findNotAnsweredWithMinAnswersCount(loggedUser.id, mappingOptions)

        // Aucune question trouvée...
        if (questionPair == null)
            throw NoAnsweredQuestionNotFound()

        return GetQuestionResponseDto(questionPair.second!!)
    }

    override fun post(request: PostQuestionRequestDto, login: String): PostQuestionResponseDto {
        val loggedUser = userRepo.findOneByLogin(login) ?: throw UserNotFound()
        val question = questionsService.create(loggedUser.id, request.label, request.answers)
        val response = PostQuestionResponseDto()
        response.success = true
        response.questionId = question!!.first.id
        return response
    }

}