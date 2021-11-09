import com.mvs.di.KtorDeliveryProvider

fun main(args: Array<String>) {
    val delivery = KtorDeliveryProvider().provide()
    delivery.start(args)
}