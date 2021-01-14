package ovh.kevinsamyn.fastpoll.jpa.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ovh.kevinsamyn.fastpoll.jpa.entities.FriendshipEntity

interface DBFriendshipsRepository : JpaRepository<FriendshipEntity, Long> {
    @Query("select fsh from FriendshipEntity fsh where fsh.user.id = :userId")
    fun findByUserId(@Param("userId") userId: Long): List<FriendshipEntity>

    @Modifying
    @Query("delete from FriendshipEntity fsh where (fsh.user.id = :userId and fsh.friendWith.id = :friendUserId) or (fsh.user.id = :friendUserId and fsh.friendWith.id = :userId)")
    fun deleteBetween(@Param("userId") userId: Long, @Param("friendUserId") friendUserId: Long)
}