package org.alekhyannikhil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

    @Test
    public void shouldCheckIsTrueIfCarIsParkedWithFreeParkingSlotAvailable(){
        ParkingLot parkingLot = new ParkingLot(10,new HashMap<Integer,Car>(), new ArrayList<ParkingLotManagers>());
        Car car = new Car(1);
        boolean park = parkingLot.park(car);
        assertTrue(park);
    }

    @Test
    public void shouldCheckIsFalseIfCarIsNotParkedWithNoSlotFree()
    {
        ParkingLot parkingLot = new ParkingLot(0, new HashMap<Integer,Car>(), new ArrayList<ParkingLotManagers>());
        Car car = new Car(11);
        boolean park = parkingLot.park(car);
        assertFalse(park);
    }

    @Test
    public void shouldCheckIfACarIsNotParkedTwice(){
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<Integer,Car>(), new ArrayList<ParkingLotManagers>());
        Car car = new Car(11);
        boolean park = parkingLot.park(car);
        Car sameCar = new Car(11);
        boolean parkAgain = parkingLot.park(sameCar);
        assertFalse(parkAgain);

    }

    @Test
    public void shouldCheckIfACarExistsForUnparking(){
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<Integer,Car>(), new ArrayList<ParkingLotManagers>());
        Car car = new Car(1);
        parkingLot.park(car);
        boolean unpark = parkingLot.unpark(1);
        assertTrue(unpark);
    }

    @Test
    public void shouldCheckIsFalseIfCarIsNotParkedAndIsBeingUnpark()
    {
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<Integer,Car>(), new ArrayList<ParkingLotManagers>());
        boolean unpark = parkingLot.unpark(1);
        assertFalse(unpark);
    }

    @Test
    public void shouldUnparkACarAndFreeTheSlot()
    {
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<Integer,Car>(), new ArrayList<ParkingLotManagers>());


    }

    @Test(expected = ParkingFullException.class)
    public void shouldThrowParkingFullException() {
        ParkingLot parkingLot = new ParkingLot(1, new HashMap<Integer,Car>(), new ArrayList<ParkingLotManagers>());
        Car car = new Car(1);
        boolean park = parkingLot.park(car);
        assertTrue(park);
    }

    @Test
    public void shouldNotifyIfParkingLotIsFull(){
        ParkingLot parkingLot = new ParkingLot(1, new HashMap<Integer,Car>(), new ArrayList<ParkingLotManagers>());
        ParkingLotManagers owner = new ParkingLotManagers();
        parkingLot.add(owner);
        Car car = new Car(1);
        parkingLot.park(car);
        assertTrue(owner.isParkingFull());
    }
}
