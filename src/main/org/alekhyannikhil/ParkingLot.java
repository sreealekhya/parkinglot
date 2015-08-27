package org.alekhyannikhil;

import java.util.Map;

public class ParkingLot {
    private int size;
    private Map<Integer,Car> slots;
    private ParkingLotOwner owner;

    public ParkingLot(int size, Map slots, ParkingLotOwner owner) {
        this.size = size;
        this.slots = slots;
        this.owner = owner;
    }


    public Object park(Car car)  {
        if(car != null){
        if(slots.size()<size)
        {
            if(slots.containsValue(car))
                throw new SameCarCannotBeParkedAgainException("Car Already Parked");
            for(int i=1;i<=10;i++)
            {
                if(!slots.containsKey(i))
                {
                    slots.put(i,car);
                    if(size == slots.size())
                    {
                            owner.notifyParkingFull();
                    }
                    return i;
                }
            }
        }
        throw new ParkingFullException("ParkingLot is Full");
       }
       return "Car cannot be null";
    }

    public Car unpark(Object slotNo) {
        if(!isCarParkedForDriveOut(slotNo))
            throw new CarIsNotParkedToBeUnParkedException("Car is not parked for unparking");
        Car car = slots.get(slotNo);
        slots.remove(slotNo);
        if((size-1) == slots.size())
        {
            owner.notifyParkingLotHasSpaceAgain();
        }
        return car;
    }

    private boolean isCarParkedForDriveOut(Object slotNo)
    {
        if(!slots.containsKey(slotNo))
            return false;
        return true;
    }

}
