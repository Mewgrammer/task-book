package github.mewgrammer.taskbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskbookApplication

fun main(args: Array<String>) {
    runApplication<TaskbookApplication>(*args)
}
