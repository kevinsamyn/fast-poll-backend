package ovh.kevinsamyn.fastpoll.jpa.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipRequestEntity

interface DBFriendshipRequestRepository : JpaRepository<FriendshipRequestEntity, Long> {

    @Query("select frq from FriendshipRequestEntity frq where frq.receiver.id = :receiverId")
    fun findByReceiverId(@Param("receiverId") receiverId: Long): List<FriendshipRequestEntity>

}