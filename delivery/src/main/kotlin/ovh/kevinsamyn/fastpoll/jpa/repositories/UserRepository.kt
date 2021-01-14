package ovh.kevinsamyn.fastpoll.jpa.repositories

import ovh.kevinsamyn.fastpoll.jpa.entities.UserEntity

interface UserRepository {
    fun findById(userId: Long): UserEntity?
    fun findOneByLogin(login: String): UserEntity?
    fun save(entity: UserEntity): UserEntity
}
