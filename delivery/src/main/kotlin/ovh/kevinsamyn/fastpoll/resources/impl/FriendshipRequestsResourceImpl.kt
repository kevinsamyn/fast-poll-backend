package ovh.kevinsamyn.fastpoll.resources.impl

import ovh.kevinsamyn.fastpoll.dto.response.DeleteFriendshipRequestsResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.GetFriendshipRequestsResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PostFriendshipRequestsResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PutFriendshipRequestsResponseDto
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.jpa.repositories.UserRepository
import ovh.kevinsamyn.fastpoll.mappers.FriendshipRequestMapper
import ovh.kevinsamyn.fastpoll.resources.FriendshipRequestsResource
import ovh.kevinsamyn.fastpoll.services.FriendshipRequestsService

class FriendshipRequestsResourceImpl(val friendshipRequestsService: FriendshipRequestsService, val userRepo: UserRepository
) : FriendshipRequestsResource {
    override fun get(login: String): GetFriendshipRequestsResponseDto {
        val loggedUser = userRepo.findOneByLogin(login) ?: throw UserNotFound()
        val requests = friendshipRequestsService.findByReceiverId(loggedUser.id, FriendshipRequestMapper.Options())
        return GetFriendshipRequestsResponseDto(requests.second)
    }

    override fun post(usr_login: String, login: String): PostFriendshipRequestsResponseDto {
        val loggedUser = userRepo.findOneByLogin(usr_login) ?: throw UserNotFound()
        val createBetween = friendshipRequestsService.createBetween(loggedUser.id, usr_login, FriendshipRequestMapper.Options())
        val response = PostFriendshipRequestsResponseDto()
        response.friendshipRequestId = createBetween.first.id
        response.success = true
        return response
    }

    override fun put(friendshipRequestId: Long): PutFriendshipRequestsResponseDto {
        friendshipRequestsService.acceptById(friendshipRequestId)
        val response = PutFriendshipRequestsResponseDto()
        response.success = true
        return response
    }

    override fun delete(friendshipRequestId: Long): DeleteFriendshipRequestsResponseDto {
        friendshipRequestsService.declineById(friendshipRequestId)
        val response = DeleteFriendshipRequestsResponseDto()
        response.success = true
        return response
    }

}