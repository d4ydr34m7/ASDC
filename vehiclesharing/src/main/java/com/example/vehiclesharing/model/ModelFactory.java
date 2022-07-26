package com.example.vehiclesharing.model;

public class ModelFactory implements IModelFactory{

    private static ModelFactory uniqueInstance = null;
    public static ModelFactory instance()
    {
       if(null == uniqueInstance)
       {
           uniqueInstance = new ModelFactory();
       }
        return  uniqueInstance;
    }

    @Override
    public Booking createBookingWithBuilder(IBookingBuilder builder) {
        return new Booking(builder);
    }

    @Override
    public IBookingBuilder createBookingBuilder() {
        return new BookingBuilder();
    }
}
