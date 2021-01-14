package ovh.kevinsamyn.fastpoll.resources

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import ovh.kevinsamyn.fastpoll.dto.response.DeleteFriendshipRequestsResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.GetFriendshipRequestsResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PostFriendshipRequestsResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PutFriendshipRequestsResponseDto

@RestController
@Transactional
@RequestMapping("/friendship-requests")
interface FriendshipRequestsResource {

    @GetMapping
    fun get(@RequestParam(required = true) login: String): GetFriendshipRequestsResponseDto

    @PostMapping("/usr/{usr_login}")
    fun post(@PathVariable("usr_login") usr_login: String, @RequestParam(required = true) login: String) : PostFriendshipRequestsResponseDto

    @PutMapping("/{frq_id}")
    fun put(@PathVariable(name = "frq_id") friendshipRequestId : Long) : PutFriendshipRequestsResponseDto

    @DeleteMapping("/{frq_id}")
    fun delete(@PathVariable(name = "frq_id") friendshipRequestId : Long) : DeleteFriendshipRequestsResponseDto
}