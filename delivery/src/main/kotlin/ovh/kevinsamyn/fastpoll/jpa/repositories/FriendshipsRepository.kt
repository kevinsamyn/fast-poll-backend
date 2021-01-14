package ovh.kevinsamyn.fastpoll.jpa.repositories

import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipEntity

interface FriendshipsRepository {
    fun findByUserId(userId: Long): List<FriendshipEntity>
    fun deleteBetween(userId: Long, friendUserId: Long)
}
