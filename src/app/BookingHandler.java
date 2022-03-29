package app;

import com.booking.api.reservations.BookingReservation;
import com.booking.api.reservations.BookingReservationAPI;
import com.booking.api.reservations.BookingReservationAcknowledgement;
import com.booking.api.reservations.BookingReservationHandler;

public class BookingHandler implements Subscriber {
    //access the settings
    Settings settings = Settings.getInstance();
    
    public String type = "booking";
    private String username = settings.getUsername();
    private String password = settings.getPassword();
    private BookingHandler() {
    
    }

    public static BookingHandler getInstance() {
        return new BookingHandler();
    }
    BookingReservationAPI api = new BookingReservationAPI(username,
                                                          password,
                                                            new BookingReservationHandler() {
                                                            @Override
                                                            public BookingReservationAcknowledgement handle(BookingReservation reservation) {
                                                                // Handle reservation.
                                                                return null;
                                                          }
                                                      });


    @Override
    public void update(String context, String content) {
        
        // write to booking server logic
        
    }}
