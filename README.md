# Android-Kotlin-Ecommerce-App

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![Kotlin](https://img.shields.io/badge/kotlin-1.3.21-blue)
![GitHub repo size](https://img.shields.io/badge/repo%20size-43.9MB-blue)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

<b>An E-Commerce application developed with Android Studio using Gson, a REST API and Kotlin.</b>
<br>

![application screenshot](mealdealgif.gif)

<br>

---

### Main Page
<img src="mainpage.png" width=400>

<br>

---

### Item 
<img src="item.png" width=400>

<br>

---

### Tokens 
<img src="tokens.png" width=400>

<br>

---

### Contact Us 
<img src="contactus.png" width=400>

<br>

---

### About Us 
<img src="aboutus.png" width=400>

<br>

---

## Products Adaptar
```
class ProductsAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.photoUrl).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("title", products[holder.adapterPosition].title)
            intent.putExtra("photo_url", products[holder.adapterPosition].photoUrl)
            intent.putExtra("price", products[holder.adapterPosition].price)
            parent.context.startActivity(intent)
        }
        return holder
    }
```

<br>

---

## JSON Items
```
{
	"name": "Item NameFalafel burgers",
	"photo_url": "Item Image Url",
	"price": 10
}
```

<br>

---

## External API
```
        doAsync {
            val json = URL("https://api.myjson.com/bins/$$$$$$").readText()
            uiThread {
                val products = Gson().fromJson(json, Array<Product>::class.java).toList()
                root.recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products)
                    root.progressBar.visibility = View.GONE
                }
            }
        }
```


