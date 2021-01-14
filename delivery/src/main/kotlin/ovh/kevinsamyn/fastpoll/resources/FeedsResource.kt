package ovh.kevinsamyn.fastpoll.resources

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ovh.kevinsamyn.fastpoll.dto.response.GetFeedAnswersResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.GetFeedQuestionsResponseDto

@RestController
@Transactional
@RequestMapping("/feeds")
interface FeedsResource {

    @GetMapping("/questions")
    fun getQuestionsFeed(@RequestParam(required = true) login: String): GetFeedQuestionsResponseDto

    @GetMapping("/answers")
    fun getAnswersFeed(@RequestParam(required = true) login: String): GetFeedAnswersResponseDto
}