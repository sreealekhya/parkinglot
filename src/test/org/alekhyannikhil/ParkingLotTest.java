package org.alekhyannikhil;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotTest {

    @Test
    public void shouldCheckIsTrueIfCarIsParkedWithFreeParkingSlotAvailable(){
        ParkingLotOwner owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<Integer,Car>(), owner);
        Car car = new Car(1);
        Object token = parkingLot.park(car);
        assertEquals(1, token);
    }

    @Test(expected = ParkingFullException.class)
    public void shouldNotParkIfParkingLotFull()
    {
        ParkingLotOwner owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(0, new HashMap<Integer,Car>(), owner);
        Car car = new Car(11);
        parkingLot.park(car);
    }

    @Test(expected = SameCarCannotBeParkedAgainException.class)
    public void shouldCheckIfACarIsNotParkedAgain(){
        ParkingLotOwner owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(2, new HashMap<Integer,Car>(), owner);
        Car car = new Car(11);
        parkingLot.park(car);
        Car sameCar = new Car(11);
        parkingLot.park(sameCar);

    }

    @Test
    public void shouldNotifyIfParkingLotIsFull(){
        ParkingLotOwner owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, new HashMap<Integer,Car>(), owner);
        Car car = new Car(1);
        parkingLot.park(car);
        verify(owner,times(1)).notifyParkingFull();
    }


    @Test
    public void shouldCheckIfACarExistsForUnparkingAndCarIsReturnedForDriveOut(){
        ParkingLotOwner owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<Integer,Car>(), owner);
        Car car = new Car(1);
        Object token = parkingLot.park(car);
        Car carUnParked = parkingLot.unpark(token);
        assertEquals(car,carUnParked);
    }

    
    @Test(expected = CarIsNotParkedToBeUnParkedException.class)
    public void shouldCheckToThrowExceptionIfCarIsNotParkedToBeUnParked()
    {
        ParkingLotOwner owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<Integer,Car>(), owner);
        parkingLot.unpark(1);
    }

    @Test
    public void shouldNotifyIfParkingLotHasSpaceAgain()
    {
        ParkingLotOwner owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, new HashMap<Integer,Car>(), owner);
        Car car = new Car(1);
        Object token = parkingLot.park(car);
        parkingLot.unpark(token);
        verify(owner).notifyParkingLotHasSpaceAgain();
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotIsFull()
    {
        ParkingFullNotification owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, new HashMap<Integer,Car>(), (ParkingLotOwner)owner);
        parkingLot.subscriberForParkingFullNotification(owner);
        Car car = new Car(1);
        parkingLot.park(car);
        verify(owner,times(1)).notifyParkingFull();
    }

    @Test
    public void shouldNotifySecurityIfParkingLotIsFull()
    {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, new HashMap<Integer,Car>(), owner);
        ParkingFullNotification security = mock(AirportSecurityPerson.class);
        parkingLot.subscriberForParkingFullNotification(security);
        Car car = new Car(1);
        parkingLot.park(car);
        verify(security,times(1)).notifyParkingFull();
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotHasEmptySlot()
    {
        ParkingHasAEmptySlotNotification owner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, new HashMap<Integer,Car>(), (ParkingLotOwner)owner);
        parkingLot.subscriberForParkingHasAEmptySlot(owner);
        Car car = new Car(1);
        Object token = parkingLot.park(car);
        parkingLot.unpark(token);
        verify(owner,times(1)).notifyParkingLotHasSpaceAgain();
    }

}
