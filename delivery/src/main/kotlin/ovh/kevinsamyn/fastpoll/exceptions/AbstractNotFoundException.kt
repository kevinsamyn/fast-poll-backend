package ovh.kevinsamyn.fastpoll.exceptions

import ovh.kevinsamyn.fastpoll.dto.ErrorDto

abstract class AbstractNotFoundException(error: ErrorDto) : AbstractBusinessException(error)