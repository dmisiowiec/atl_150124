import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "movie description by id"
    request {
        method 'GET'
        url '/descriptions/43'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status OK()
        body(
                id: anyNumber(),
                description: anyNonBlankString(),
        )
        headers {
            contentType(applicationJson())
        }
    }
}
