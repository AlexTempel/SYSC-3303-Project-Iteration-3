import java.net.DatagramSocket;
import java.net.SocketException;

public class ElevatorSubsystem implements Runnable {
    private int current_floor;
    private final int elevator_id;
    private ElevatorDoors doors;
    private final DatagramSocket socket;

    /**
     * Contruct the ElevatorSubsystem Object
     *
     * @param id integer identifier of the elevator, also the recieve port
     */
    public ElevatorSubsystem(int id) throws SocketException {
        this.elevator_id = id;
        current_floor = 1; //start the Elevator at the ground floor
        doors = new ElevatorDoors();
        this.socket = new DatagramSocket(id);

    }

    public void run() {
        System.out.println("Starting Elevator");
        while (true) {
            // Obtain formatted request data
            Request myRequest = getRequestData();
            handleRequest(myRequest);
        }
    }
    // This is a temporary class
    //The final implementation should:
    // - Collect UDP packet from the socket
    // - Parse and separate the data by ',' delimiter
    // - Create and return a Request type object
    public Request getRequestData(){
        Request currentRequest = new Request(0,0);
        return currentRequest;
    }

    public void moveElevator(int destination) {
        // Check how many floors elevator needs to travel
        int floorDifference = Math.abs(current_floor - destination);
        for (int i = floorDifference; i > 0; i--) {
            System.out.printf("Elevator %d needs to travel %d floors to reach destination\n", elevator_id, i);
            try {
                Thread.sleep(1000); // Simulate travel time
            } catch (InterruptedException e) {
            }
        }
        // Complete the trip
        current_floor = destination;
    }

    /**
     * Moves the elevator to the starting floor, then to the destination floor
     */
    public void handleRequest(Request currentReq) {
        int startingFloor = currentReq.getStartingFloor();
        int endingFloor = currentReq.getDestinationFloor();

        // Move from the current floor to the starting floor
        if (startingFloor != current_floor) {
            moveElevator(startingFloor);
        }
        // TODO ADD DOOR LOGIC

        // Move to destination floor
        moveElevator(endingFloor);
        currentReq.complete();
        currentReq = null;
    }
}