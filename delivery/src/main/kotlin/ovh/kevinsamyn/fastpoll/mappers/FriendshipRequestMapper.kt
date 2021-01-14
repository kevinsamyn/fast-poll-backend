package ovh.kevinsamyn.fastpoll.mappers

import ovh.kevinsamyn.fastpoll.dto.beans.FriendshipRequestDto
import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipRequestEntity

/**
 *
 */
object FriendshipRequestMapper : AbstractBaseMapper<FriendshipRequestEntity, FriendshipRequestDto, FriendshipRequestMapper.Options>() {
    override
    fun mapMainFields(source: FriendshipRequestEntity): FriendshipRequestDto {
        return FriendshipRequestDto(
                id = source.id,
                user = UserMapper.map(source.sender, null)
        )
    }

    override
    fun FriendshipRequestDto.mapOptionalsFields(source: FriendshipRequestEntity, options: Options): FriendshipRequestDto {
        return this
    }

    class Options {
        // TODO
    }
}



