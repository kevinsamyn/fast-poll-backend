package ovh.kevinsamyn.fastpoll.jpa.repositories.impl

import ovh.kevinsamyn.fastpoll.jpa.entities.UserEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.DBUserRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.UserRepository

open class JpaUserRepository(private val dbUserRepository: DBUserRepository) :
        UserRepository {
    override fun findById(userId: Long): UserEntity? {
        val optional = dbUserRepository.findById(userId)
        if (optional.isPresent) {
            return optional.get()
        }
        return null
    }

    override fun findOneByLogin(login: String): UserEntity? {
        return dbUserRepository.findOneByLogin(login)
    }


    override fun save(entity: UserEntity): UserEntity {
        return dbUserRepository.save(entity)
    }

    fun create(login: String): UserEntity {
        val usr = UserEntity()
        usr.login = login
        return dbUserRepository.save(usr)
    }
}