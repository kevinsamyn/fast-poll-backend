package ovh.kevinsamyn.fastpoll.jpa.repositories

import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipRequestEntity

interface FriendshipRequestsRepository {
    fun findByReceiverId(receiverId: Long): List<FriendshipRequestEntity>
    fun createBetween(senderId: Long, receiverLogin: String): FriendshipRequestEntity
    fun declineById(friendshipRequestId: Long)
    fun acceptById(friendshipRequestId: Long)
}
