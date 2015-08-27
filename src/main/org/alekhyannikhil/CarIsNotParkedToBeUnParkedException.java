package org.alekhyannikhil;

public class CarIsNotParkedToBeUnParkedException extends RuntimeException {
    CarIsNotParkedToBeUnParkedException(String s)
    {
        super(s);
    }
}
