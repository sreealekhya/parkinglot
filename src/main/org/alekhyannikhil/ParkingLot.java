package org.alekhyannikhil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int size;
    private Map<Integer,Car> slots;
    private ParkingLotOwner owner;
    private List<ParkingFullNotification> parkingFull = new ArrayList<ParkingFullNotification>();
    private List<ParkingHasAEmptySlotNotification> parkingHasEmptySlot = new ArrayList<ParkingHasAEmptySlotNotification>();

    public ParkingLot(int size, Map slots, ParkingLotOwner owner) {
        this.size = size;
        this.slots = slots;
        this.owner = owner;
    }


    public Object park(Car car)  {
        if(slots.size()<size)
        {
            if(slots.containsValue(car))
                throw new SameCarCannotBeParkedAgainException("Car Already Parked");
            for(int i=1;i<=size;i++)
            {
                if(!slots.containsKey(i))
                {
                    slots.put(i,car);
                    if(size == slots.size())
                    {
                        for(ParkingFullNotification parkingFullNotification : parkingFull){
                            parkingFullNotification.notifyParkingFull();
                        }
                    }
                    return i;
                }
            }
        }
        throw new ParkingFullException("ParkingLot is Full");
    }

    public Car unpark(Object slotNo) {
        if(!isCarParkedForDriveOut(slotNo))
            throw new CarIsNotParkedToBeUnParkedException("Car is not parked for unparking");
        Car car = slots.get(slotNo);
        slots.remove(slotNo);
        if((size-1) == slots.size())
        {
            for(ParkingHasAEmptySlotNotification parkingHasAEmptySlotNotification : parkingHasEmptySlot){
                parkingHasAEmptySlotNotification.notifyParkingLotHasSpaceAgain();
            }
        }
        return car;
    }

    private boolean isCarParkedForDriveOut(Object slotNo)
    {
        if(!slots.containsKey(slotNo))
            return false;
        return true;
    }

    public void subscriberForParkingFullNotification(ParkingFullNotification subscriber) {
        parkingFull.add(subscriber);
    }

    public void subscriberForParkingHasAEmptySlot(ParkingHasAEmptySlotNotification subscriber) {
        parkingHasEmptySlot.add(subscriber);
    }
}
