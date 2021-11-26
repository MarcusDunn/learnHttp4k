import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun myServer6(request: Request): Response {
    return Response(Status.OK)
}


fun myServer7(request: Request): Response = Response(Status.OK)


fun myServer8(request: Request) = Response(Status.OK)


fun main() {

    val greetHandler: HttpHandler = { Response(Status.OK).body("hello\n") }
    val farewellHandler: HttpHandler = { Response(Status.OK).body("goodbye\n") }
    val defaultHandler: HttpHandler = { Response(Status.NOT_FOUND).body("try greet or goodbye\n") }

    val myServer: HttpHandler = routes(
        "/greet" bind Method.GET to greetHandler,
        "/farewell" bind Method.GET to farewellHandler,
        "/" bind Method.GET to defaultHandler,
    )

    myServer
        .asServer(SunHttp()) // give the server a 'backend'
        .start() // start the server
        .also { println("server started on ${it.port()}") } // let everyone know the server has started
        .block() // block so we don't exit main
}

