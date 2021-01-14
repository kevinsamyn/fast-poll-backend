package ovh.kevinsamyn.fastpoll.jpa.entities

import javax.persistence.*

@Entity
@Table(name = "t_e_users_usr")
open class UserEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    var id: Long = -1

    @Column(name = "usr_login", length = 256, nullable = false, unique = true)
    var login: String = ""

    @OneToMany(fetch = FetchType.EAGER, targetEntity = FriendshipEntity::class, mappedBy = "user")
    var friendships: Set<FriendshipEntity> = HashSet()

    constructor(user: UserEntity) : this() {
        id = user.id
        login = user.login
    }
}