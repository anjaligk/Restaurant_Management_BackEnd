package snippet;

public class Snippet {
	private final ReservationService reservationService;
	
	    @Autowired
	    public ReservationController(ReservationService reservationService) {
	        this.reservationService = reservationService;
	    }
}

