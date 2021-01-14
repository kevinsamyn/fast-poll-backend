package ovh.kevinsamyn.fastpoll.exceptions

import ovh.kevinsamyn.fastpoll.dto.ErrorDto


abstract class AbstractValidationException(error: ErrorDto) : AbstractBusinessException(error)