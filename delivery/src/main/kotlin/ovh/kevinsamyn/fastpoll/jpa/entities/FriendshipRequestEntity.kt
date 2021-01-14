package ovh.kevinsamyn.fastpoll.jpa.entities

import javax.persistence.*

@Entity
@Table(name = "t_e_friendship_request_frq")
open class FriendshipRequestEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "frq_id")
        var id: Long = 0

        @ManyToOne(targetEntity = UserEntity::class)
        @JoinColumn(name = "usr_fk_sender")
        lateinit var sender: UserEntity

        @ManyToOne(targetEntity = UserEntity::class)
        @JoinColumn(name = "usr_fk_receiver")
        lateinit var receiver: UserEntity
}