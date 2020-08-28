import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class ConnascentMarsRoverShould {
    private MarsRoverReceiver marsRoverReceiver;
    private MarsRoverSender marsRoverSender;

    @BeforeEach
    void setUp() {
        marsRoverReceiver = new MarsRoverReceiver();
        marsRoverSender = new MarsRoverSender();
        MarsRoverController marsRoverController = new MarsRoverController();
        ServiceBus marsRoverBus = new ServiceBus();
        MarsRover marsRover = new MarsRover(marsRoverBus, marsRoverReceiver, marsRoverSender, marsRoverController);
    }

    @Test
    void move_following_commands() {
        String[] inputPackages = {"P", "X2", "Y5", "DN", "M5", "1F", "2L", "3F", "4R", "5F"};
        for(String pack : inputPackages){
            marsRoverReceiver.received(pack);
        }

        verify(marsRoverSender).send("1 7 N");
    }
}