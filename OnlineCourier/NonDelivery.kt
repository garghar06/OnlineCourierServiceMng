package OnlineCourier

class NonDelivery(val deliveryId: Int, val productId: Int, val customer: Customer, val reason: String) {
    fun displayNonDeliveryDetails() {
        println("Non-Delivery Details for Delivery ID: $deliveryId")
        println("Product ID: $productId")
        println("Customer: ${customer.name}")
        println("Reason for Non-Delivery: $reason")
    }
}