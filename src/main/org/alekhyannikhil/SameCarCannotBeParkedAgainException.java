package org.alekhyannikhil;

public class SameCarCannotBeParkedAgainException extends RuntimeException {
    SameCarCannotBeParkedAgainException(String s)
    {
        super(s);
    }
}
