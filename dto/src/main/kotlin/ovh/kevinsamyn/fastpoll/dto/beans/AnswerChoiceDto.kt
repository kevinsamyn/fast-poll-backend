package ovh.kevinsamyn.fastpoll.dto.beans

data class AnswerChoiceDto(
        val id: Long,
        val label: String
){
    var percent: Int? = null
    var check: Boolean? = null
}
