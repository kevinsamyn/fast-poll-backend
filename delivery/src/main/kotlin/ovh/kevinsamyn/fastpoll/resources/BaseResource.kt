package ovh.kevinsamyn.fastpoll.resources

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller de base de l'app.
 */
@RestController
@Transactional
@RequestMapping("/")
interface BaseResource {

    /**
     * Déconnexion
     */
    @GetMapping
    fun hello(): String

}