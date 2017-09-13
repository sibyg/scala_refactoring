import scala.collection.mutable.ListBuffer

case class Movie(title: String, priceCode: Int)

object Movie {
  val CHILDRENS = 2
  val REGULAR = 0
  val NEW_RELEASE = 1
}

case class Rental(movie: Movie, daysRented: Int) {
  def amount = {
    var thisAmount = 0.0
    movie.priceCode match {
      case Movie.REGULAR =>
        thisAmount += 2
        if (daysRented > 2) thisAmount += (daysRented - 2) * 1.5
      case Movie.NEW_RELEASE =>
        thisAmount += daysRented * 3
      case Movie.CHILDRENS =>
        thisAmount += 1.5
        if (daysRented > 3) thisAmount += (daysRented - 3) * 1.5
    }
    thisAmount
  }
}

case class Customer(name: String) {
  val rentals: ListBuffer[Rental] = ListBuffer()

  var frequentRenterPoints = 0
  var totalAmount = 0.0

  def addRental(rental: Rental) {
    rentals += rental
    // add frequent renter points
    frequentRenterPoints += 1
    // add bonus for a two day new release rental
    if ((rental.movie.priceCode == Movie.NEW_RELEASE)
      && rental.daysRented > 1) {
      frequentRenterPoints += 1
    }
  }

  def statement: String = {

    var result = "Rental Record for " + name + "\n"
    rentals.foreach(rental => {
      //show figures for this rental
      result += "\t" + rental.movie.title + "\t" + String.valueOf(rental.amount) + "\n"
      totalAmount += rental.amount
    })


    //    add footer lines
    result += "Amount owed is " + String.valueOf(totalAmount) + "\n"
    result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points"
    result
  }


}