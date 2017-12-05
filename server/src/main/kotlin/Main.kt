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

    val pusher = Pusher("PUSHER_APP_ID", "PUSHER_APP_KEY", "PUSHER_APP_SECRET")
    pusher.setCluster("PUSHER_APP_CLUSTER")

    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/{message}") {
            val i = call.parameters["message"]!!
            pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", i))
            call.respond("response sent")
        }

    }
}
