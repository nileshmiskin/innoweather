package com.innoweather.test.functional

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import spock.lang.Shared
import spock.lang.Specification


class AppSpec extends Specification {
    @Shared
    def http = new HTTPBuilder('http://localhost:8080', ContentType.JSON)

    def setupSpec() {
        http.handler.success = { resp, data ->
            [responseInfo: resp, data: data]
        }
        http.handler.failure = { resp, error ->
            [responseInfo: resp, error: error]
        }
    }
}