package ovh.kevinsamyn.fastpoll.services

import ovh.kevinsamyn.fastpoll.dto.beans.FriendshipDto
import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipEntity
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.JpaFriendshipsRepository
import ovh.kevinsamyn.fastpoll.mappers.FriendshipMapper


class FriendshipsService(private val friendshipsRepository: JpaFriendshipsRepository) {
    fun findByUserId(userId: Long, options: FriendshipMapper.Options): Pair<List<FriendshipEntity>, List<FriendshipDto>> {
        val frqList = friendshipsRepository.findByUserId(userId)
        return buildPair(frqList, options)
    }

    fun deleteBetween(userId: Long, friendUserId : Long){
        friendshipsRepository.deleteBetween(userId, friendUserId)
    }

    private fun buildPair(friendships: List<FriendshipEntity>, mappingOptions: FriendshipMapper.Options): Pair<List<FriendshipEntity>, List<FriendshipDto>> {
        val dtos = mutableListOf<FriendshipDto>()
        for (fsh in friendships) {
            dtos.add(FriendshipMapper.map(fsh, mappingOptions))
        }
        return Pair(friendships, dtos)
    }
}