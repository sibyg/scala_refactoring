import Movie._
import org.scalatest.FunSuite

class CustomerTest extends FunSuite {

  test("should add rental") {
    // given
    val john = Customer("John")
    // and
    val childrenMovie = Rental(Movie("Rio", CHILDRENS), 2)
    val regularMovie = Rental(Movie("Titanic", CHILDRENS), 2)

    // when
    john.addRental(childrenMovie)
    john.addRental(regularMovie)

    // then
    assert(john.rentals.size == 2)
    // and
    assert(john.rentals.head == childrenMovie)
    assert(john.rentals(1) == regularMovie)
  }

  test("should get frequent rental point for each rental") {
    // given
    val john = Customer("John")
    // and
    val childrenMovie = Rental(Movie("Rio", CHILDRENS), 2)
    val regularMovie = Rental(Movie("Titanic", CHILDRENS), 2)

    // when
    john.addRental(childrenMovie)
    john.addRental(regularMovie)

    // then
    assert(john.frequentRenterPoints == 2)
  }

  test("should add extra frequent rental point for new release rented for more than a day") {
    // given
    val john = Customer("John")
    // and
    val newRelease = Rental(Movie("Raes", NEW_RELEASE), 2)
    val regularMovie = Rental(Movie("Rio", CHILDRENS), 1)

    // when
    john.addRental(newRelease)
    john.addRental(regularMovie)

    // then
    assert(john.frequentRenterPoints == 3)
  }
}
