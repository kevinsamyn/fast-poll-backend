package ovh.kevinsamyn.fastpoll.services

import ovh.kevinsamyn.fastpoll.dto.beans.FriendshipRequestDto
import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipRequestEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaFriendshipRequestsRepository
import ovh.kevinsamyn.fastpoll.mappers.FriendshipRequestMapper


class FriendshipRequestsService(private val friendshipRequestsRepository: JpaFriendshipRequestsRepository) {
    fun findByReceiverId(receiverId: Long, options: FriendshipRequestMapper.Options): Pair<List<FriendshipRequestEntity>, List<FriendshipRequestDto>> {
        val frqList = friendshipRequestsRepository.findByReceiverId(receiverId)
        return buildPair(frqList, options)
    }

    fun createBetween(senderId: Long, receiverLogin: String, options: FriendshipRequestMapper.Options): Pair<FriendshipRequestEntity, FriendshipRequestDto> {
        val frq = friendshipRequestsRepository.createBetween(senderId, receiverLogin)
        return buildPair(frq, options)
    }

    fun acceptById(friendshipRequestId: Long) {
        friendshipRequestsRepository.acceptById(friendshipRequestId)
    }

    fun declineById(friendshipRequestId: Long) {
        friendshipRequestsRepository.declineById(friendshipRequestId)
    }

    private fun buildPair(
        friendshipRequest: FriendshipRequestEntity,
        mappingOptions: FriendshipRequestMapper.Options
    ): Pair<FriendshipRequestEntity, FriendshipRequestDto> {
        return Pair(friendshipRequest, FriendshipRequestMapper.map(friendshipRequest, mappingOptions))
    }

    private fun buildPair(friendshipRequests: List<FriendshipRequestEntity>, mappingOptions: FriendshipRequestMapper.Options): Pair<List<FriendshipRequestEntity>, List<FriendshipRequestDto>> {
        val dtos = mutableListOf<FriendshipRequestDto>()
        for (frq in friendshipRequests) {
            dtos.add(FriendshipRequestMapper.map(frq, mappingOptions))
        }
        return Pair(friendshipRequests, dtos)
    }


}