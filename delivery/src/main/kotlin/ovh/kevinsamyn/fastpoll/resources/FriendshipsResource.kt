package ovh.kevinsamyn.fastpoll.resources

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import ovh.kevinsamyn.fastpoll.dto.response.DeleteFriendshipsResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.GetFriendshipsResponseDto

@RestController
@Transactional
@RequestMapping("/friendships")
interface FriendshipsResource {

    @GetMapping
    fun get(@RequestParam(required = true) login: String): GetFriendshipsResponseDto

    @DeleteMapping("/usr/{usr_id}")
    fun delete(@PathVariable(name = "usr_id") friendUserId : Long, @RequestParam(required = true) login: String): DeleteFriendshipsResponseDto
}