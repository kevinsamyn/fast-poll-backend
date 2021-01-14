package ovh.kevinsamyn.fastpoll.resources

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import ovh.kevinsamyn.fastpoll.dto.response.PostAnswerResponseDto

@RestController
@Transactional
@RequestMapping("/answers")
interface AnswersResource {

    @PostMapping("/qst/{qst_id}/ach/{ach_id}")
    fun post(@PathVariable(name = "qst_id") questionId: Long, @PathVariable("ach_id") answerChoiceId: Long, @RequestParam(required = true) login : String): PostAnswerResponseDto
}