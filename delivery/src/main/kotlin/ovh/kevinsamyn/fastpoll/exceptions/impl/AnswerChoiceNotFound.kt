package ovh.kevinsamyn.fastpoll.exceptions.impl

import ovh.kevinsamyn.fastpoll.dto.ErrorCodeDto
import ovh.kevinsamyn.fastpoll.dto.ErrorDto
import ovh.kevinsamyn.fastpoll.exceptions.AbstractNotFoundException

class AnswerChoiceNotFound: AbstractNotFoundException(
        ErrorDto(
                ErrorCodeDto.ANSWER_CHOICE_NOT_FOUND,
                "Answer choice not found"
        )
)