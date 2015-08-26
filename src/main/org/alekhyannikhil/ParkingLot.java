package org.alekhyannikhil;

import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int size;
    private Map<Integer,Car> slots;
    private List<ParkingLotManagers> parkingLotManagers;

    public ParkingLot(int size, Map slots, List<ParkingLotManagers> parkingLotManagers) {
        this.size = size;
        this.slots = slots;
        this.parkingLotManagers = parkingLotManagers;
    }


    public boolean park(Car car)  {
        if(car != null){
        if(slots.size()<size)
        {
            if(slots.containsValue(car))
                return false;
            for(int i=1;i<=10;i++)
            {
                if(!slots.containsKey(i))
                {
                    slots.put(i,car);
                    if(size == slots.size())
                    {
                        for(ParkingLotManagers parkingLotManager : parkingLotManagers)
                        {
                            parkingLotManager.notifyParkingFull();
                        }
                    }
                    return true;
                }
            }
        }
       }
       return false;
    }

    public boolean unpark(int slotNo) {
        if(!slots.containsKey(slotNo))
            return false;
        return true;
    }

    public void add(ParkingLotManagers manager) {
        parkingLotManagers.add(manager);
    }
}
