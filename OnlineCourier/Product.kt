package OnlineCourier


class Product(val productId: Int,val name: String, val description: String, var stock: Int = 0,var amount : Double = 0.0) {
    var company: Company? = null
    fun checkAvailability(): Boolean {
        return stock > 0
    }

    fun buyProduct(): Boolean {
        if (stock > 0) {
            stock--
            return true
        }
        return false
    }

    companion object {

        val products = listOf(
            Product(1,"Laptop", "ASUS laptop I7 processor", 10,87000.97),
            Product(2,"My glam Lipstick mini pop up set", "Description: contains 3 different shades", 5,250.4),
            Product(3,"Phone", "Description: Samsung S21 FE", 5,40000.4),
            Product(4,"Sunscreen", "Description: No white cast , light weight , ordourless", 5,250.4)
            // Add more static products as needed
        )

        fun displayAvailableProducts() {
            println("Available Products:")
            for (product in products) {
                if (product.checkAvailability()) {
                    println("Name: ${product.name}, Description: ${product.description}, Stock: ${product.stock}")
                }
            }
        }
    }
}