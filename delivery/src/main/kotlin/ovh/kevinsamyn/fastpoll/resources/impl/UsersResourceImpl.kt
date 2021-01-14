package ovh.kevinsamyn.fastpoll.resources.impl

import ovh.kevinsamyn.fastpoll.dto.request.PostUsersSignInRequestBodyDto
import ovh.kevinsamyn.fastpoll.dto.request.PostUsersSignUpRequestBodyDto
import ovh.kevinsamyn.fastpoll.dto.response.PostUsersSignInResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PostUsersSignOutResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PostUsersSignUpResponseDto
import ovh.kevinsamyn.fastpoll.exceptions.impl.UserNotFound
import ovh.kevinsamyn.fastpoll.mappers.UserMapper
import ovh.kevinsamyn.fastpoll.resources.UsersResource
import ovh.kevinsamyn.fastpoll.services.UserService

class UsersResourceImpl(private val userService: UserService) : UsersResource {

    override fun signUp(request: PostUsersSignUpRequestBodyDto): PostUsersSignUpResponseDto {
        val user = userService.create(request.login)
        val response = PostUsersSignUpResponseDto()
        response.user = user.second
        return response
    }

    override fun signIn(request: PostUsersSignInRequestBodyDto): PostUsersSignInResponseDto {
        // On vérifie l'identité de l'utilisateur
        val user = userService.findOneByLogin(request.login, UserMapper.Options()) ?: throw UserNotFound()
        return PostUsersSignInResponseDto(UserMapper.map(user.first, null))
    }

    override fun signOut(): PostUsersSignOutResponseDto {
        return PostUsersSignOutResponseDto()
    }


}