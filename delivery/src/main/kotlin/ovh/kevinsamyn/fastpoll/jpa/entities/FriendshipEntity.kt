package ovh.kevinsamyn.fastpoll.jpa.entities

import javax.persistence.*

@Entity
@Table(name = "t_e_friendship_fsh")
open class FriendshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fsh_id")
    val id: Long = 0

    @ManyToOne(targetEntity = UserEntity::class, optional = false)
    @JoinColumn(name = "usr_fk")
    lateinit var user: UserEntity

    @ManyToOne(targetEntity = UserEntity::class, optional = false)
    @JoinColumn(name = "usr_fk_with")
    lateinit var friendWith: UserEntity

}