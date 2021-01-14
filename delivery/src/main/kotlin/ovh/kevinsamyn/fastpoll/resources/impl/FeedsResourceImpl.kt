package ovh.kevinsamyn.fastpoll.resources.impl

import ovh.kevinsamyn.fastpoll.dto.response.GetFeedAnswersResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.GetFeedQuestionsResponseDto
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.jpa.repositories.UserRepository
import ovh.kevinsamyn.fastpoll.mappers.AnswerChoiceMapper
import ovh.kevinsamyn.fastpoll.mappers.QuestionMapper
import ovh.kevinsamyn.fastpoll.resources.FeedsResource
import ovh.kevinsamyn.fastpoll.services.QuestionsService

class FeedsResourceImpl(val questionsService: QuestionsService, val userRepo: UserRepository
) : FeedsResource {
    override fun getQuestionsFeed(login: String): GetFeedQuestionsResponseDto {
        val loggedUser = userRepo.findOneByLogin(login) ?: throw UserNotFound()

        val mappingOptions = QuestionMapper.Options()
        mappingOptions.choices = true
        mappingOptions.choicesOptions = AnswerChoiceMapper.Options(pCheckBy = loggedUser.id, pPercent = true)
        mappingOptions.answersCount = true

        val questions = questionsService.findCreatedByUserId(loggedUser.id, mappingOptions)
        return GetFeedQuestionsResponseDto(questions.second)
    }

    override fun getAnswersFeed(login: String): GetFeedAnswersResponseDto {
        val loggedUser = userRepo.findOneByLogin(login) ?: throw UserNotFound()

        val mappingOptions = QuestionMapper.Options()
        mappingOptions.choices = true
        mappingOptions.choicesOptions = AnswerChoiceMapper.Options(pCheckBy = loggedUser.id, pPercent = true)
        mappingOptions.answersCount = true

        val questions = questionsService.findAnsweredByUserId(loggedUser.id, mappingOptions)
        return GetFeedAnswersResponseDto(questions.second)
    }

}