package ovh.kevinsamyn.fastpoll.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EntityScan(basePackages = ["ovh.kevinsamyn.fastpoll.jpa.entities"])
@EnableJpaRepositories(basePackages = ["ovh.kevinsamyn.fastpoll.jpa.repositories"])
class DBConfig