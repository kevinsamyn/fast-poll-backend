package ovh.kevinsamyn.fastpoll.jpa.repositories.impl

import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.DBFriendshipsRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.FriendshipsRepository

open class JpaFriendshipsRepository(private val dbFriendshipRepository: DBFriendshipsRepository) :
        FriendshipsRepository {
    override fun findByUserId(userId: Long): List<FriendshipEntity> {
        return dbFriendshipRepository.findByUserId(userId)
    }

    override fun deleteBetween(userId: Long, friendUserId: Long) {
        dbFriendshipRepository.deleteBetween(userId, friendUserId)
    }

}