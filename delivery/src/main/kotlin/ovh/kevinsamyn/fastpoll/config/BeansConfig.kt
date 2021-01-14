package ovh.kevinsamyn.fastpoll.config

import com.google.gson.Gson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ovh.kevinsamyn.fastpoll.jpa.repositories.*
import ovh.kevinsamyn.fastpoll.jpa.repositories.impl.*
import ovh.kevinsamyn.fastpoll.resources.impl.*
import ovh.kevinsamyn.fastpoll.services.*

@Configuration
class BeansConfig {

    @Bean
    fun gson() = Gson()

    @Bean
    fun baseResourceImp() = BaseResourceImpl()

    @Bean
    fun usersResourceImp(
            userService: UserService
    ) = UsersResourceImpl(userService)

    @Bean
    fun feedsResourceImp(
            questionsService: QuestionsService,
            userRepository: JpaUserRepository
    ) = FeedsResourceImpl(questionsService, userRepository)

    @Bean
    fun questionsResourceImp(
            questionsService: QuestionsService,
            userRepository: JpaUserRepository
    ) = QuestionsResourceImpl(questionsService, userRepository)

    @Bean
    fun answersResourceImp(
            answersService: AnswersService,
            userRepository: JpaUserRepository
    ) = AnswersResourceImpl(answersService, userRepository)


    @Bean
    fun friendshipRequestsResourceImp(
            friendshipRequestsService: FriendshipRequestsService,
            userRepository: JpaUserRepository
    ) = FriendshipRequestsResourceImpl(friendshipRequestsService, userRepository)


    @Bean
    fun friendshipsResourceImp(
            friendshipsService: FriendshipsService,
            userRepository: JpaUserRepository
    ) = FriendshipsResourceImpl(friendshipsService, userRepository)

    @Bean
    fun friendshipsService(
            friendshipsRepository: JpaFriendshipsRepository
    ) = FriendshipsService(friendshipsRepository)

    @Bean
    fun friendshipRequestsService(
            friendshipRequestsRepository: JpaFriendshipRequestsRepository
    ) = FriendshipRequestsService(friendshipRequestsRepository)

    @Bean
    fun usersService(userRepository: JpaUserRepository) = UserService(userRepository)

    @Bean
    fun answersService(answerRepository: JpaAnswerRepository, questionRepository: JpaQuestionRepository, answerChoiceRepository: JpaAnswerChoiceRepository, userRepository: JpaUserRepository) = AnswersService(answerRepository, questionRepository, answerChoiceRepository, userRepository)

    @Bean
    fun answerChoicesService(answerChoiceRepository: JpaAnswerChoiceRepository) = AnswerChoicesService(answerChoiceRepository)

    @Bean
    fun questionsService(questionRepository: JpaQuestionRepository, userRepository: JpaUserRepository, answerChoicesService: AnswerChoicesService, answersService: AnswersService) = QuestionsService(questionRepository, userRepository, answerChoicesService, answersService)

    /**
     * Repositories
     */
    @Bean
    fun friendshipsRepository(dbFriendshipsRepository: DBFriendshipsRepository) = JpaFriendshipsRepository(dbFriendshipsRepository)

    @Bean
    fun friendshipRequestsRepository(dbFriendshipRequestRepository: DBFriendshipRequestRepository, dbUserRepository: DBUserRepository, dbFriendshipsRepository: DBFriendshipsRepository) = JpaFriendshipRequestsRepository(dbFriendshipRequestRepository, dbUserRepository, dbFriendshipsRepository)

    @Bean
    fun userRepository(dbUserRepository: DBUserRepository) = JpaUserRepository(dbUserRepository)

    @Bean
    fun answerRepository(dbAnswerRepository: DBAnswerRepository) = JpaAnswerRepository(dbAnswerRepository)

    @Bean
    fun answerChoiceRepository(dbAnswerChoiceRepository: DBAnswerChoiceRepository) = JpaAnswerChoiceRepository(dbAnswerChoiceRepository)

    @Bean
    fun questionRepository(dbQuestionRepository: DBQuestionRepository) = JpaQuestionRepository(dbQuestionRepository)
}