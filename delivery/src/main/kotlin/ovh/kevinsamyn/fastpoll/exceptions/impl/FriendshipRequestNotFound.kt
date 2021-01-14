package ovh.kevinsamyn.fastpoll.exceptions.impl

import ovh.kevinsamyn.fastpoll.dto.ErrorCodeDto
import ovh.kevinsamyn.fastpoll.dto.ErrorDto
import ovh.kevinsamyn.fastpoll.exceptions.AbstractNotFoundException

class FriendshipRequestNotFound: AbstractNotFoundException(
        ErrorDto(
                ErrorCodeDto.FRIENDSHIP_REQUEST_NOT_FOUND,
                "No friendship request found"
        )
)