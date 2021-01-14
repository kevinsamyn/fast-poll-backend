package ovh.kevinsamyn.fastpoll.resources.impl

import ovh.kevinsamyn.fastpoll.dto.response.PostAnswerResponseDto
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.jpa.repositories.UserRepository
import ovh.kevinsamyn.fastpoll.resources.AnswersResource
import ovh.kevinsamyn.fastpoll.services.AnswersService

class AnswersResourceImpl(val answersService: AnswersService, val userRepo: UserRepository
) : AnswersResource {
    override fun post(questionId: Long, answerChoiceId: Long, login: String): PostAnswerResponseDto {
        val loggedUser = userRepo.findOneByLogin(login) ?: throw UserNotFound()
        // TODO vérifier que la réponse n'a pas déjà été donnée
        answersService.create(questionId, answerChoiceId, loggedUser.id)
        val response = PostAnswerResponseDto()
        response.success = true
        return response
    }

}