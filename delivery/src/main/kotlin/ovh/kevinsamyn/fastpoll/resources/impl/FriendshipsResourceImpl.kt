package ovh.kevinsamyn.fastpoll.resources.impl

import ovh.kevinsamyn.fastpoll.dto.response.DeleteFriendshipsResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.GetFriendshipsResponseDto
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.jpa.repositories.UserRepository
import ovh.kevinsamyn.fastpoll.mappers.FriendshipMapper
import ovh.kevinsamyn.fastpoll.resources.FriendshipsResource
import ovh.kevinsamyn.fastpoll.services.FriendshipsService

class FriendshipsResourceImpl(val friendshipsService: FriendshipsService, val userRepo: UserRepository
) : FriendshipsResource {
    override fun get(login: String): GetFriendshipsResponseDto {
        val loggedUser = userRepo.findOneByLogin(login) ?: throw UserNotFound()
        val friendships = friendshipsService.findByUserId(loggedUser.id, FriendshipMapper.Options())
        return GetFriendshipsResponseDto(friendships.second)
    }

    override fun delete(friendUserId: Long, login: String): DeleteFriendshipsResponseDto {
        val loggedUser = userRepo.findOneByLogin(login) ?: throw UserNotFound()
        friendshipsService.deleteBetween(loggedUser.id, friendUserId)
        val response = DeleteFriendshipsResponseDto()
        response.success= true
        return response
    }

}