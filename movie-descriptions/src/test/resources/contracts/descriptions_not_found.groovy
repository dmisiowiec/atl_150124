import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "movie description not found"
    request {
        method 'GET'
        url '/descriptions/1'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 404
    }
}
