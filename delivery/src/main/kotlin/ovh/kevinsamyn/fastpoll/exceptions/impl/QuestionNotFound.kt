package ovh.kevinsamyn.fastpoll.exceptions.impl

import ovh.kevinsamyn.fastpoll.dto.ErrorCodeDto
import ovh.kevinsamyn.fastpoll.dto.ErrorDto
import ovh.kevinsamyn.fastpoll.exceptions.AbstractNotFoundException

class QuestionNotFound: AbstractNotFoundException(
        ErrorDto(
                ErrorCodeDto.QUESTION_NOT_FOUND,
                "No question found"
        )
)