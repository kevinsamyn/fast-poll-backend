package ovh.kevinsamyn.fastpoll.dto


data class ErrorDto(
        val code: ErrorCodeDto?,
        val message: String?
)

enum class ErrorCodeDto {
    ACCESS_TOKEN_INVALID_EXCEPTION,
    NO_ANSWERED_QUESTION_NOT_FOUND,
    USER_NOT_FOUND,
    FRIENDSHIP_REQUEST_NOT_FOUND,
    QUESTION_NOT_FOUND,
    ANSWER_CHOICE_NOT_FOUND
}
