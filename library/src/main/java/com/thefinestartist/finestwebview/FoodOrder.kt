package com.thefinestartist.finestwebview

class FoodOrder private constructor(
  val bread: String?,
  val condiments: String?,
  val meat: String?,
  val fish: String?
) {

  data class Builder(
    var bread: String? = null,
    var condiments: String? = null,
    var meat: String? = null,
    var fish: String? = null
  ) {

    fun bread(bread: String) = apply { this.bread = bread }
    fun condiments(condiments: String) = apply { this.condiments = condiments }
    fun meat(meat: String) = apply { this.meat = meat }
    fun fish(fish: String) = apply { this.fish = fish }
    fun build() = FoodOrder(bread, condiments, meat, fish)
  }

  // Usage example
  val foodOrder =
    FoodOrder.Builder()
      .bread("white bread")
      .meat("bacon")
      .condiments("olive oil")
      .build()
}

data class FoodOrder2(
  var bread: String = "Bread",
  var condiments: String? = null,
  var meat: String? = null,
  var fish: String? = null
) {
  fun bread(bread: String) = apply { this.bread = bread }
  fun condiments(condiments: String?) = apply { this.condiments = condiments }
  fun meat(meat: String?) = apply { this.meat = meat }
  fun fish(fish: String?) = apply { this.fish = fish }
}

val foodOrder2 = FoodOrder2(
  bread = "white bread",
  condiments = "olive oil",
  fish = "salmon"
)

val foodOrder3 = FoodOrder2().apply {
  bread = "white bread"
  condiments = "olive oil"
  fish = "salmon"
}

val foodOrder4 =
  FoodOrder2()
    .bread("white bread")
    .condiments("olive oil")
    .fish("salmon")
