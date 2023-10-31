package OnlineCourier
import java.util.Scanner
import java.io.File

class Customer(var name: Name, private var address: Address, private var phoneNumber: String) {
    val sc = Scanner(System.`in`)

    fun setCustomerDetails() {

        println("Enter customer details:")
        print("Enter first name: ")
        val fname:String = sc.next()
        print("Enter last name: ")
        val lname:String = sc.next()
        name = Name(fname,lname)
        println("Address:")
        print("City: ")
        val city = sc.next()
        print("State: ")
        val state = sc.next()
        address = Address( city, state)
        print("Phone Number: ")
        phoneNumber = sc.next()
    }

    fun displayCustomerDetails() {
        println("Customer Details:")
        println("Name: ${name.firstName} ${name.lastName}")
        println("Address:  ${address.city}, ${address.state}")
        println("Phone Number: $phoneNumber")
    }

    private val purchasedProducts = mutableListOf<Product>()

    fun chooseProductToPurchase(product: Product) {
        if (product.checkAvailability()) {
            purchasedProducts.add(product)
            println("Purchased: ${product.name} for $${product.amount}")
        } else {
            println("Product ${product.name} is not available.")
        }
    }

    fun generateBill(delivery: Delivery, deliveryCharges: Double): String{
        if (purchasedProducts.isEmpty()) {
            println("No products purchased.")
            return ""
        }

        val totalAmount = purchasedProducts.sumByDouble { it.amount }+deliveryCharges
        val bill = """
            Order Details:
            Delivery ID: ${delivery.deliveryId}
            Total Bill: $$totalAmount
            
            Customer Details:
            Name: ${name.firstName} ${name.lastName}
            Address: ${address.city}, ${address.state} 
            Phone Number: $phoneNumber
        """

        val fileName = "bill_${delivery.deliveryId}.txt"
        val file = File(fileName)
        file.writeText(bill)

        val data = file.readText()
        println(data)
        return fileName
    }

    fun initiatePayment() {
        println("How would you like to pay?")
        println("1. Pay Online")
        println("2. Cash on Delivery")

        val choice:Int = sc.nextInt()

        when (choice) {
            1 -> {
                println("You chose to pay online. Redirecting to payment gateway...")
                // Add payment processing logic here
                println("Payment successful.")
            }
            2 -> {
                val isSuccessfulDelivery = Delivery.trackDeliveryStatus()
                if (isSuccessfulDelivery) {
                    println("You chose Cash on Delivery (COD). Payment is marked as done upon successful delivery.")
                } else {
                    println("You chose Cash on Delivery (COD). Payment will be collected upon delivery.")
                }
            }
            else -> println("Invalid choice.")
        }
    }

    fun provideReview(review: Review) {
        println("Please provide your review for our courier service:")
        print("Rating (1 to 5 stars): ")
        val rating:Int = sc.nextInt()
        review.rating = rating

        print("Comments: ")
        val com : String = sc.next()
        review.comments = com
    }

}