package ovh.kevinsamyn.fastpoll.services

import ovh.kevinsamyn.fastpoll.dto.beans.QuestionDto
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaQuestionRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaUserRepository
import ovh.kevinsamyn.fastpoll.mappers.QuestionMapper

class QuestionsService(private val questionRepository: JpaQuestionRepository, private val userRepository: JpaUserRepository, private val answerChoicesService: AnswerChoicesService, private val answersService: AnswersService) {


    private fun findById(questionId: Long?): Pair<QuestionEntity, QuestionDto?>? {
        if (null != questionId) {
            val question = questionRepository.findById(questionId)
            if (question != null) {
                return buildPair(question, QuestionMapper.Options())
            }
        }
        return null
    }

    fun create(userId: Long, label: String, answers: List<String>): Pair<QuestionEntity, QuestionDto?>? {
        val user = userRepository.findById(userId) ?: throw UserNotFound()
        val question = questionRepository.create(user, label)

        for (answer in answers) {
            answerChoicesService.create(question, answer)
        }

        return findById(question.id)
    }

    fun findNotAnsweredAndCreateByFriend(userId: Long, mappingOptions: QuestionMapper.Options): Pair<QuestionEntity, QuestionDto?>? {
        val question = questionRepository.findNotAnsweredAndCreateByFriend(userId) ?: return null
        return buildPair(question, mappingOptions)
    }

    fun findNotAnsweredAndAnsweredByFriend(userId: Long, mappingOptions: QuestionMapper.Options): Pair<QuestionEntity, QuestionDto?>? {
        val question = questionRepository.findNotAnsweredAndAnsweredByFriend(userId) ?: return null
        return buildPair(question, mappingOptions)
    }

    fun findNotAnsweredWithMinAnswersCount(userId: Long, mappingOptions: QuestionMapper.Options): Pair<QuestionEntity, QuestionDto?>? {
        val question = questionRepository.findNotAnsweredWithMinAnswersCount(userId) ?: return null
        return buildPair(question, mappingOptions)
    }

    fun findCreatedByUserId(userId: Long, mappingOptions: QuestionMapper.Options): Pair<List<QuestionEntity>, List<QuestionDto>> {
        val questions = questionRepository.findCreatedByUserId(userId)
        return buildPair(questions, mappingOptions)
    }

    fun findAnsweredByUserId(userId: Long, mappingOptions: QuestionMapper.Options): Pair<List<QuestionEntity>, List<QuestionDto>> {
        val questions = questionRepository.findAnsweredByUserId(userId)
        return buildPair(questions, mappingOptions)
    }

    private fun buildPair(questions: List<QuestionEntity>, mappingOptions: QuestionMapper.Options): Pair<List<QuestionEntity>, List<QuestionDto>> {
        val dtos = mutableListOf<QuestionDto>()
        for (question in questions) {
            val questionDto = QuestionMapper.map(question, mappingOptions)
            completeMappingDto(mappingOptions, questionDto, question)
            dtos.add(questionDto)
        }
        return Pair(questions, dtos)
    }

    private fun buildPair(question: QuestionEntity, mappingOptions: QuestionMapper.Options): Pair<QuestionEntity, QuestionDto> {
        val pair = Pair(question, QuestionMapper.map(question, mappingOptions))
        val questionDto = pair.second
        completeMappingDto(mappingOptions, questionDto, question)
        return pair
    }

    private fun completeMappingDto(mappingOptions: QuestionMapper.Options, questionDto: QuestionDto, question: QuestionEntity) {
        if (mappingOptions.answersCount) {
            questionDto.answersCount = answersService.countByQuestionId(question.id)
        }

        if (mappingOptions.choices) {
            val choicesOptions = mappingOptions.choicesOptions
            if (null != choicesOptions) {

                val questionDtoChoices = questionDto.choices
                if (null != questionDtoChoices) {

                    for (ach in questionDtoChoices) {
                        if (choicesOptions.percent) {
                            ach.percent = answersService.percentByQuestionIdAndAnswerChoiceId(question.id, ach.id).toInt()
                        }
                        if (null != choicesOptions.checkBy) {
                            ach.check = answersService.existByQuestionIdAndAnswerChoiceIdAndUserId(question.id, ach.id, choicesOptions.checkBy!!)
                        }
                    }
                }
            }
        }
    }


}