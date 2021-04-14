package usantatecla.movies.v22;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class CustomerTest {

	private static String CUSTOMER_NAME = "customerName";
	private static String MOVIE_NAME = "movieName";

	@Test
	public void withoutRentalsStatementTest() {
		Customer customer = new CustomerBuilder().name(CUSTOMER_NAME).build();
		String statement = customer.statement();
		String result = new StatementBuilder().customerName(CUSTOMER_NAME)
				.totalAmount(0).frequentRenterPoints(0).build();
		assertEquals(result, statement);
	}


	@Test
	public void totalFrequentRenterPointsForRegularMovieOneDayTest() {
		Movie regularMovie = new RegularMovie(MOVIE_NAME);
		Rental rental = new RentalBuilder().movie(regularMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(CUSTOMER_NAME).rental(rental).build();
		assertEquals(1,customer.getTotalFrequentRenterPoints());
	}


	@Test
	public void totalChargeForRegularMovieOneDayTest() {
		Movie regularMovie = new RegularMovie(MOVIE_NAME);
		Rental rental = new RentalBuilder().movie(regularMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(CUSTOMER_NAME).rental(rental).build();
		assertTrue(customer.getTotalCharge() == 2.0);
	}


	@Test
	public void totalFrequentRenterPointsForChildrenMovieOneDayTest() {
		Movie childrenMovie = new ChildrenMovie(MOVIE_NAME);
		Rental rental = new RentalBuilder().movie(childrenMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(CUSTOMER_NAME).rental(rental).build();
		assertEquals(1,customer.getTotalFrequentRenterPoints());
	}
	@Test
	public void totalChargeForChildrenMovieOneDayTest() {
		Movie childrenMovie = new ChildrenMovie(MOVIE_NAME);
		Rental rental = new RentalBuilder().movie(childrenMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(CUSTOMER_NAME).rental(rental).build();
		assertTrue(customer.getTotalCharge() == 1.5);
	}
	@Test
	public void totalFrequentRenterPointsForNewReleaseMovieOneDayTest() {
		Movie childrenMovie = new ChildrenMovie(MOVIE_NAME);
		Rental rental = new RentalBuilder().movie(childrenMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(CUSTOMER_NAME).rental(rental).build();
		assertEquals(1,customer.getTotalFrequentRenterPoints());
	}
	@Test
	public void totalChargeForNewReleaseOneDayTest() {
		Movie newReleaseMovie = new NewReleaseMovie(MOVIE_NAME);
		Rental rental = new RentalBuilder().movie(newReleaseMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(CUSTOMER_NAME).rental(rental).build();
		assertTrue(customer.getTotalCharge() == 3.0);
	}
	@Test
	public void CustomerNameForRegularMovieOneDayTest() {
		Movie regularMovie = new RegularMovie(MOVIE_NAME);
		Rental rental = new RentalBuilder().movie(regularMovie).daysRented(1).build();
		Customer customer = new CustomerBuilder().name(CUSTOMER_NAME).rental(rental).build();
		assertEquals("customerName", customer.getName());
	}

	@Test
	public void rentalTest() {
		String regularMovieName = "regularMovieName";
		Movie regularMovie = new MovieBuilder().title(regularMovieName).regular().build();
		Rental regularRental = new RentalBuilder().movie(regularMovie).daysRented(10).build();

		String newReleaseMovieName = "newReleaseMovieName";
		Movie newReleaseMovie = new MovieBuilder().title(newReleaseMovieName).newRelease().build();
		Rental newReleaseRental = new RentalBuilder().movie(newReleaseMovie).daysRented(10).build();

		String childrensMovieName = "childrensMovieName";
		Movie childrensMovie = new MovieBuilder().title(childrensMovieName).childrens().build();
		Rental childrensRental = new RentalBuilder().movie(childrensMovie).daysRented(10).build();

		String customerName = "customerName";
		Customer customer = new CustomerBuilder().name(customerName)
				.rental(regularRental).rental(newReleaseRental).rental(childrensRental).build();

		String statement = customer.statement();

		String result = new StatementBuilder().customerName(customerName)
				.movie(regularMovieName, 14).movie(newReleaseMovieName, 3).movie(childrensMovieName, 15)
				.totalAmount(32).frequentRenterPoints(4).build();
		assertEquals(result, statement);
	}


}
