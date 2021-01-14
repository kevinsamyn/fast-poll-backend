package ovh.kevinsamyn.fastpoll.exceptions.impl

import ovh.kevinsamyn.fastpoll.dto.ErrorCodeDto
import ovh.kevinsamyn.fastpoll.dto.ErrorDto
import ovh.kevinsamyn.fastpoll.exceptions.AbstractNotFoundException

class NoAnsweredQuestionNotFound: AbstractNotFoundException(
        ErrorDto(
                ErrorCodeDto.NO_ANSWERED_QUESTION_NOT_FOUND,
                "No question without answer found for user"
        )
)