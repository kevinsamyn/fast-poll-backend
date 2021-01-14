package ovh.kevinsamyn.fastpoll

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication(scanBasePackages = [
    "ovh.kevinsamyn.fastpoll.config",
    "ovh.kevinsamyn.fastpoll.resources.impl"
])
class App : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}
