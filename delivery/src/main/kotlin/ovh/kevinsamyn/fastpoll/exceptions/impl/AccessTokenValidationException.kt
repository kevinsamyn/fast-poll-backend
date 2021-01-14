package ovh.kevinsamyn.fastpoll.exceptions.impl

import ovh.kevinsamyn.fastpoll.dto.ErrorCodeDto
import ovh.kevinsamyn.fastpoll.dto.ErrorDto
import ovh.kevinsamyn.fastpoll.exceptions.AbstractNotFoundException

class AccessTokenValidationException : AbstractNotFoundException(
        ErrorDto(
                ErrorCodeDto.ACCESS_TOKEN_INVALID_EXCEPTION,
                "Access Token do not match valid session"
        )
)