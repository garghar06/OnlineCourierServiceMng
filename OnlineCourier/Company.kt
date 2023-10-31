package OnlineCourier

class Company(val name: String, val dealer: String) {
    private val productsProduced = mutableListOf<Product>()

    fun raiseTicket(product: Product) {
        println("Raising a ticket to the company dealer '$dealer' for the product '${product.name}'")
        // Check if the stock is zero, then increase it and notify the dealer
        if (product.stock == 0) {
            product.stock++
            println("Stock for product '${product.name}' increased to 1.")
        }
    }

    fun addProduct(product: Product) {
        product.company = this
        productsProduced.add(product)
    }

    fun displayProducts() {
        println("Products produced by $name:")
        for (product in productsProduced) {
            println("Name: ${product.name}, Description: ${product.description}, Stock: ${product.stock}")
        }
    }

    companion object {
        val companyDetails = Company("ABC Company", "John Doe")
    }
}