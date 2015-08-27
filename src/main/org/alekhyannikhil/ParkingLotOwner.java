package org.alekhyannikhil;

public class ParkingLotOwner implements ParkingLotManagers{
    private boolean parkingFull;

    public void notifyParkingFull() {
        parkingFull = true;
    }

    public void notifyParkingLotHasSpaceAgain(){

    }
}
