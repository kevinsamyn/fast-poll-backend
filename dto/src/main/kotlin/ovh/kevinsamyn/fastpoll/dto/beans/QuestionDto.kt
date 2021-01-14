package ovh.kevinsamyn.fastpoll.dto.beans

data class QuestionDto(val id: Long,
                       val label: String) {

    var answersCount: Int? = null
    var choices: List<AnswerChoiceDto>? = null

}
