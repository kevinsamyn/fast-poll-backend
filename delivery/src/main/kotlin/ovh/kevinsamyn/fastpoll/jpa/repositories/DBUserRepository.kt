package ovh.kevinsamyn.fastpoll.jpa.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ovh.kevinsamyn.fastpoll.jpa.entities.UserEntity

interface DBUserRepository : JpaRepository<UserEntity, Long> {
    fun findOneByLogin(login: String): UserEntity?
}