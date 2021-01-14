package ovh.kevinsamyn.fastpoll.exceptions

import ovh.kevinsamyn.fastpoll.dto.ErrorDto

abstract class AbstractBusinessException(val error: ErrorDto) : RuntimeException()