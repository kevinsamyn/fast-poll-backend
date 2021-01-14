package ovh.kevinsamyn.fastpoll.mappers

import ovh.kevinsamyn.fastpoll.dto.beans.FriendshipDto
import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipEntity

/**
 *
 */
object FriendshipMapper : AbstractBaseMapper<FriendshipEntity, FriendshipDto, FriendshipMapper.Options>() {
    override
    fun mapMainFields(source: FriendshipEntity): FriendshipDto {
        return FriendshipDto(
                id = source.id,
                user = UserMapper.map(source.user, null),
                with = UserMapper.map(source.friendWith, null)
        )
    }

    override
    fun FriendshipDto.mapOptionalsFields(source: FriendshipEntity, options: Options): FriendshipDto {
        return this
    }

    class Options {
        // TODO
    }
}



