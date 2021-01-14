package ovh.kevinsamyn.fastpoll.dto.beans

data class FriendshipDto(
        val id: Long,
        val user: UserDto,
        val with: UserDto
)
