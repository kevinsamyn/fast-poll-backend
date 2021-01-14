package ovh.kevinsamyn.fastpoll.jpa.repositories.impl

import ovh.kevinsamyn.fastpoll.exceptions.impl.FriendshipRequestNotFound
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipEntity
import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipRequestEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.DBFriendshipRequestRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.DBFriendshipsRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.DBUserRepository
import ovh.kevinsamyn.fastpoll.jpa.repositories.FriendshipRequestsRepository

open class JpaFriendshipRequestsRepository(private val dbFriendshipRequestRepository: DBFriendshipRequestRepository, private val dbUserRepository: DBUserRepository, private val dbFriendshipsRepository: DBFriendshipsRepository) :
        FriendshipRequestsRepository {
    override fun findByReceiverId(receiverId: Long): List<FriendshipRequestEntity> {
        return dbFriendshipRequestRepository.findByReceiverId(receiverId)
    }

    override fun createBetween(senderId: Long, receiverLogin: String): FriendshipRequestEntity {

        val sender = dbUserRepository.findById(senderId)
        val receiver = dbUserRepository.findOneByLogin(receiverLogin)

        if (!sender.isPresent || null == receiver)
            throw UserNotFound()

        val frq = FriendshipRequestEntity()
        frq.sender = sender.get()
        frq.receiver = receiver

        return dbFriendshipRequestRepository.save(frq)
    }

    override fun declineById(friendshipRequestId: Long) {
        dbFriendshipRequestRepository.deleteById(friendshipRequestId)
    }

    override fun acceptById(friendshipRequestId: Long) {

        val frq = dbFriendshipRequestRepository.findById(friendshipRequestId)
        if (!frq.isPresent)
            throw FriendshipRequestNotFound()

        val fshA = FriendshipEntity()
        fshA.user = frq.get().sender
        fshA.friendWith = frq.get().receiver
        dbFriendshipsRepository.save(fshA)

        val fshB = FriendshipEntity()
        fshB.user = frq.get().receiver
        fshB.friendWith = frq.get().sender
        dbFriendshipsRepository.save(fshB)

        dbFriendshipRequestRepository.deleteById(friendshipRequestId)
    }

}