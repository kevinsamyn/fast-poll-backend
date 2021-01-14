package ovh.kevinsamyn.fastpoll.resources

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import ovh.kevinsamyn.fastpoll.dto.request.PostQuestionRequestDto
import ovh.kevinsamyn.fastpoll.dto.response.GetQuestionResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PostQuestionResponseDto

@RestController
@Transactional
@RequestMapping("/questions")
interface QuestionsResource {

    @GetMapping
    fun get(@RequestParam(required = true) login: String): GetQuestionResponseDto

    @PostMapping
    fun post(@RequestBody request: PostQuestionRequestDto, @RequestParam(required = true) login: String): PostQuestionResponseDto
}