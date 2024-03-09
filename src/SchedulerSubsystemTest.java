import static org.junit.jupiter.api.Assertions.*;

import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

class SchedulerSubsystemTest {

    @org.junit.jupiter.api.Test
    void getRequestFromInternet() {

    }

    @org.junit.jupiter.api.Test
    void dealWithNewRequest() {
        try {
            ArrayList<ElevatorSchedulerData> elevatorList = new ArrayList<>();
            elevatorList.add(new ElevatorSchedulerData(1, InetAddress.getLoopbackAddress()));
            elevatorList.add(new ElevatorSchedulerData(2, InetAddress.getLoopbackAddress()));
            ElevatorSchedulerData elevator3 = new ElevatorSchedulerData(3, InetAddress.getLoopbackAddress());
            elevator3.setInUse(true);


            Request r = new Request(1, 1, 2);
            ArrayList<Request> outstandingRequests = new ArrayList<>();
            outstandingRequests.add(r);

            SchedulerSubsystem testSystem = new SchedulerSubsystem(1, elevatorList, outstandingRequests);

            ElevatorSchedulerData elevatorTest = new ElevatorSchedulerData(3, InetAddress.getLoopbackAddress());
            RequestWrapper rw = new RequestWrapper(r, elevatorTest);


            testSystem.dealWithNewRequest(rw);
        } catch (Exception e) {
            fail("Exception in code");
        }
    }

    @org.junit.jupiter.api.Test
    void sendRequestToElevator() {

    }

    @org.junit.jupiter.api.Test
    void checkPending() {

    }

    @org.junit.jupiter.api.Test
    void selectElevator() {
        try {
            Request req = new Request(1,4,10);
            SchedulerSubsystem testScheduler = new SchedulerSubsystem(1);

            testScheduler.selectElevator(req);
            assertTrue(testScheduler.getElevatorList().getFirst().getInUse(),
                    "first elevator should be running");

        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}