package ovh.kevinsamyn.fastpoll.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ovh.kevinsamyn.fastpoll.exceptions.AbstractBusinessException
import ovh.kevinsamyn.fastpoll.exceptions.AbstractNotFoundException
import ovh.kevinsamyn.fastpoll.exceptions.AbstractValidationException
import ovh.kevinsamyn.fastpoll.exceptions.impl.AccessTokenValidationException

@ControllerAdvice
@RestController
class ExceptionHandlerConfig : ResponseEntityExceptionHandler() {
    @ExceptionHandler(AbstractNotFoundException::class)
    fun notFound(ex: AbstractNotFoundException) =
            ResponseEntity(ex.error, HttpStatus.NOT_FOUND)

    @ExceptionHandler(AbstractValidationException::class)
    fun validation(ex: AbstractValidationException) =
            ResponseEntity(ex.error, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(AbstractBusinessException::class)
    fun business(ex: AbstractBusinessException) =
            ResponseEntity(ex.error, HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(AccessTokenValidationException::class)
    fun authentication(ex: AccessTokenValidationException) =
            ResponseEntity(ex.error, HttpStatus.UNAUTHORIZED)

}
