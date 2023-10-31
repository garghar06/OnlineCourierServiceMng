package OnlineCourier

class Delivery(val deliveryId: Int,val productIds: List<Int>, val customer: Customer) {

    companion object {
        var isDelivered: Boolean = false

        fun trackDeliveryStatus(): Boolean {
            println("Tracking the status of the delivery...")
            return isDelivered
        }
    }
}