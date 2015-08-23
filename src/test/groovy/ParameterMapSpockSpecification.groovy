import spock.lang.Specification

public class ParameterMapSpockSpecification extends Specification {

    final TicketService ticketService = Stub(TicketService)
    final Booking booking = Stub(Booking)
    final BookingService bookingService = new BookingService(ticketService);

    def "should throw exception if there are not enough tickets for particular screening"() {
        given:
        bookingIsDoneFor movie: "Rush", tickets: 2, date: "10/10/2014 07:00PM"
        availableTicketsFor movie: "Rush", on: "10/10/2014 07:00PM", equalTo: 1

        when:
        bookingService.book(booking)
        then:
        thrown(NotEnoughTicketsException)
    }

    private bookingIsDoneFor(Map bookingDetails) {
        booking.getMovie() >> bookingDetails['movie']
        booking.getDate() >> bookingDetails['date']
        booking.getNumberOfTickets() >> bookingDetails['tickets']
    }

    private void availableTicketsFor(Map ticketDetails) {
        ticketService.getAvailableTickets(ticketDetails['movie'], ticketDetails['on']) >> ticketDetails['equalTo']
    }
}