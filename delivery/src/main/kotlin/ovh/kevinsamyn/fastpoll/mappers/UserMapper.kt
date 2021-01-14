package ovh.kevinsamyn.fastpoll.mappers

import ovh.kevinsamyn.fastpoll.dto.beans.UserDto
import ovh.kevinsamyn.fastpoll.jpa.entities.UserEntity

/**
 *
 */
object UserMapper : AbstractBaseMapper<UserEntity, UserDto, UserMapper.Options>() {
    override
    fun mapMainFields(source: UserEntity): UserDto {
        return UserDto(
                id = source.id,
                login = source.login
        )
    }

    override
    fun UserDto.mapOptionalsFields(source: UserEntity, options: Options): UserDto {
        return this
    }

    class Options {
        // TODO
    }
}



