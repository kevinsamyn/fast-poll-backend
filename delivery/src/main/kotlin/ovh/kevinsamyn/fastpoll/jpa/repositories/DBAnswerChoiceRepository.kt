package ovh.kevinsamyn.fastpoll.jpa.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerChoiceEntity

interface DBAnswerChoiceRepository : JpaRepository<AnswerChoiceEntity, Long>