package com.example.vehiclesharing.model;

public class Ride
{
	private int ride_id;
	private String source;
	private String destination;
	private String start_time;
	private String end_time;
	private int remaining_seats;
	private float KMs_travelled;
	private float total_cost;
	private int driver_id;
	
	public Ride(int trip_id, String source, String destination, float KMs_travelled, int remaining_seats,
			String start_time, String end_time, int seats_remaining, float cost, int vehicle_owner_id) 
	{
        this.ride_id = trip_id;
        this.source = source;
        this.destination = destination;
        this.KMs_travelled = KMs_travelled;
        this.remaining_seats = remaining_seats;
        this.start_time = start_time;
        this.end_time = end_time;
        this.total_cost = total_cost;
        this.driver_id = driver_id;
    }

    public Ride() {
    }
    
	public int getRide_id() 
	{
		return ride_id;
	}
	
	public void setRide_id(int ride_id) 
	{
		this.ride_id = ride_id;
	}
	
	public String getSource() 
	{
		return source;
	}
	
	public void setSource(String source) 
	{
		this.source = source;
	}
	
	public String getDestination() 
	{
		return destination;
	}
	
	public void setDestination(String destination) 
	{
		this.destination = destination;
	}
	
	public String getStart_time() 
	{
		return start_time;
	}
	
	public void setStart_time(String start_time) 
	{
		this.start_time = start_time;
	}
	
	public String getEnd_time() 
	{
		return end_time;
	}
	
	public void setEnd_time(String end_time) 
	{
		this.end_time = end_time;
	}
	
	public int getRemaining_seats()
	{
		return remaining_seats;
	}
	
	public void setRemaining_seats(int remaining_seats)
	{
		this.remaining_seats = remaining_seats;
	}
	
	public float getKMs_travelled() 
	{
		return KMs_travelled;
	}
	
	public void setKMs_travelled(float kMs_travelled) 
	{
		KMs_travelled = kMs_travelled;
	}
	
	public float getTotal_cost() 
	{
		return total_cost;
	}
	
	public void setTotal_cost(float total_cost) 
	{
		this.total_cost = total_cost;
	}
	
	public int getDriver_id() 
	{
		return driver_id;
	}
	
	public void setDriver_id(int driver_id) 
	{
		this.driver_id = driver_id;
	}

}
