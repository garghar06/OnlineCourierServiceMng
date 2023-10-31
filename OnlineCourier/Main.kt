package OnlineCourier

import OnlineCourier.Product.Companion.products
import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    println("-------------- Welcome to the Online Courier Service Management -----------------")

    val customer = Customer(Name("",""), Address("", ""), "")
    customer.setCustomerDetails()
    customer.displayCustomerDetails()

    val selectedProducts = mutableListOf<Product>()

    while (true) {
        // Display available products from Product.productList
        println("Available Products:")
        for ((index, product) in Product.products.withIndex()) {
            println("${index + 1}. ${product.name} - $${product.amount} (Available: ${product.stock} in stock)")
        }

        print("Enter the product number to add to your order (e.g., 1, 2, 3, etc.), or 0 to finish: ")
        val selectedProductIndex:Int = sc.nextInt()

        if (selectedProductIndex == 0) {
            break // Exit the loop if the customer is done adding products
        } else if (selectedProductIndex in 1..Product.products.size) {
            val selectedProduct = Product.products[selectedProductIndex - 1]
            if (selectedProduct.checkAvailability()) {
                selectedProducts.add(selectedProduct)
                println("${selectedProduct.name} added to your order.")
            } else {
                println("Product ${selectedProduct.name} is not available.")
            }
        } else {
            println("Invalid product selection.")
        }
    }

    if (selectedProducts.isNotEmpty()) {
        val deliveryCharges = 5.0 // Set your delivery charges here
        val totalAmount = selectedProducts.sumByDouble { it.amount }
        val delivery = Delivery(1, selectedProducts.map { it.productId },customer)
        val billFileName = customer.generateBill(delivery, deliveryCharges)
        Delivery.isDelivered = true
        customer.initiatePayment()
        println("Bill generated and saved as $billFileName")
        val review = Review()
        customer.provideReview(review)
    } else {
        println("No products were added to your order.")
    }

}