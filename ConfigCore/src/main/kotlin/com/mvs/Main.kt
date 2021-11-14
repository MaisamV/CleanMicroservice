import com.mvs.di.GrpcDeliveryProvider
import com.mvs.di.KtorDeliveryProvider
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    thread {
        val delivery = GrpcDeliveryProvider().provide()
        delivery.start(args)
    }
    thread {
        val delivery = KtorDeliveryProvider().provide()
        delivery.start(args)
    }
}