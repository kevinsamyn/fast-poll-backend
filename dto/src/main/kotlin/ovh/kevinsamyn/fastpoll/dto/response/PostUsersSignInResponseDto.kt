package ovh.kevinsamyn.fastpoll.dto.response

import ovh.kevinsamyn.fastpoll.dto.beans.UserDto

class PostUsersSignInResponseDto() {

    var user: UserDto? = null

    constructor(
            user: UserDto) : this() {
        this.user = user
    }


}