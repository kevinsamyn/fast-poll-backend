package ovh.kevinsamyn.fastpoll.dto.request

class PostQuestionRequestDto {
    lateinit var label: String
    val answers = listOf<String>()
}