package ovh.kevinsamyn.fastpoll.services

import ovh.kevinsamyn.fastpoll.dto.beans.UserDto
import ovh.kevinsamyn.fastpoll.jpa.entities.UserEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaUserRepository
import ovh.kevinsamyn.fastpoll.mappers.UserMapper

class UserService(private val userRepository: JpaUserRepository) {

    fun create(login: String):  Pair<UserEntity, UserDto> {
        return buildPair(userRepository.create(login), UserMapper.Options())
    }

    fun findOneByLogin(login: String, mappingOptions: UserMapper.Options): Pair<UserEntity, UserDto>? {
        val user =userRepository.findOneByLogin(login) ?: return null
        return buildPair(user, mappingOptions)
    }

    private fun buildPair(user: UserEntity, mappingOptions: UserMapper.Options): Pair<UserEntity, UserDto> {
        return Pair(user, UserMapper.map(user, mappingOptions))
    }
}