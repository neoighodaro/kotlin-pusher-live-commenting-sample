import com.pusher.rest.Pusher
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.get
import java.util.*

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/{message}") {
            val i = call.parameters["message"]!!
            val pusher = Pusher("411734", "f1dc462a7b662a89ec08", "811b93edfe80894558f3")
            pusher.setCluster("eu")
            pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", i))
            call.respond("response sent")
        }

    }

}