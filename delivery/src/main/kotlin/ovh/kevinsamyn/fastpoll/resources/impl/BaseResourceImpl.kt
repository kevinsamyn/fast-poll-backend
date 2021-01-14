package ovh.kevinsamyn.fastpoll.resources.impl

import ovh.kevinsamyn.fastpoll.resources.BaseResource

class BaseResourceImpl : BaseResource {
    override fun hello(): String {
        return "Welcome on Fastpoll backend"
    }
}