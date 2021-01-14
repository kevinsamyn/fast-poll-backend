package ovh.kevinsamyn.fastpoll.resources

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ovh.kevinsamyn.fastpoll.dto.request.PostUsersSignInRequestBodyDto
import ovh.kevinsamyn.fastpoll.dto.request.PostUsersSignUpRequestBodyDto
import ovh.kevinsamyn.fastpoll.dto.response.PostUsersSignInResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PostUsersSignOutResponseDto
import ovh.kevinsamyn.fastpoll.dto.response.PostUsersSignUpResponseDto

/**
 * Controller de la resource Author.
 * L'ensemble des fonctions retournent soit un auteur, soit une liste d'auteur, soit un type primitif.
 */
@RestController
@Transactional
@RequestMapping("/users")
interface UsersResource {

    /**
     * Inscription
     */
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: PostUsersSignUpRequestBodyDto): PostUsersSignUpResponseDto

    /**
     * Connexion
     */
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: PostUsersSignInRequestBodyDto): PostUsersSignInResponseDto

    /**
     * Déconnexion
     */
    @PostMapping("/sign-out")
    fun signOut(): PostUsersSignOutResponseDto


}