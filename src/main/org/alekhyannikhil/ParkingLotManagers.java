package org.alekhyannikhil;

public class ParkingLotManagers {

    private boolean parkingFull;

    public boolean isParkingFull() {
        return parkingFull;
    }

    public void notifyParkingFull() {
        parkingFull = true;
    }
}
