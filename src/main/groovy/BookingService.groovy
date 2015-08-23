
public class BookingService {

    private TicketService ticketService;

    public BookingService(TicketService ticketService) {
        this.ticketService = ticketService
    }

    public void book(final Booking booking) {
        def desiredMovie = booking.movie;
        def desiredDate = booking.date;
        def desiredTickets = booking.numberOfTickets;
        def availableTickets = ticketService.getAvailableTickets(desiredMovie, desiredDate);

        if (availableTickets < desiredTickets) {
            throw new NotEnoughTicketsException();
        }
    }
}
