package com.dsm.rosa

import com.dsm.rosa.global.security.dto.AppProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class RosaApplication

fun main(args: Array<String>) {
    runApplication<RosaApplication>(*args)
}
