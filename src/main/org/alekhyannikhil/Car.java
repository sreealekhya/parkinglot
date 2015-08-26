package org.alekhyannikhil;

public class Car {
    private int id;

    public Car(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Car)
        {
            Car that = (Car) obj;
            return (this.id == that.id);
        }
        return false;
    }
}
