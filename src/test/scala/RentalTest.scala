import org.scalatest.FunSuite

class RentalTest extends FunSuite {
  test("should charge fixed amount for childrens movie for <= 3 days") {
    // when
    val rental = Rental(Movie("Rio", Movie.CHILDRENS), 3)

    // then
    assert(rental.amount === 1.5)
  }

  test("should charge extra amount after 3 days") {
    // when
    val standardRentalDays = 3
    val extraRentalDays = 2
    val initialRentalCost = 1.5
    // and
    val costForEachExtraDay = 1.5

    // when
    val rental = Rental(Movie("Rio", Movie.CHILDRENS), standardRentalDays + extraRentalDays)

    // then
    assert(rental.amount === initialRentalCost + (extraRentalDays * costForEachExtraDay))
  }
}
