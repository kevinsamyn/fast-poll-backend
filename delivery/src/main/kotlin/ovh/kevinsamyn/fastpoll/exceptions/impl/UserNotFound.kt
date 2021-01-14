package ovh.kevinsamyn.fastpoll.exceptions.impl

import ovh.kevinsamyn.fastpoll.dto.ErrorCodeDto
import ovh.kevinsamyn.fastpoll.dto.ErrorDto
import ovh.kevinsamyn.fastpoll.exceptions.AbstractNotFoundException

class UserNotFound: AbstractNotFoundException(
        ErrorDto(
                ErrorCodeDto.USER_NOT_FOUND,
                "No user found"
        )
)