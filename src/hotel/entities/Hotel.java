package hotel.entities;

<<<<<<< HEAD:src/entities/Hotel.java
=======
import java.awt.print.Book;
>>>>>>> master:entities/Hotel.java
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hotel.credit.CreditCard;
import hotel.utils.IOUtils;

public class Hotel {
	
	private Map<Integer, Guest> guests;
	public Map<RoomType, Map<Integer,Room>> roomsByType;
	public Map<Long, Booking> bookingsByConfirmationNumber;
	public Map<Integer, Booking> activeBookingsByRoomId;
	
	
	public Hotel() {
		guests = new HashMap<>();
		roomsByType = new HashMap<>();
		for (RoomType rt : RoomType.values()) {
			Map<Integer, Room> rooms = new HashMap<>();
			roomsByType.put(rt, rooms);
		}
		bookingsByConfirmationNumber = new HashMap<>();
		activeBookingsByRoomId = new HashMap<>();
	}

	
	public void addRoom(RoomType roomType, int id) {
		IOUtils.trace("Hotel: addRoom");
		for (Map<Integer, Room> rooms : roomsByType.values()) {
			if (rooms.containsKey(id)) {
				throw new RuntimeException("Hotel: addRoom : room number already exists");
			}
		}
		Map<Integer, Room> rooms = roomsByType.get(roomType);
		Room room = new Room(id, roomType);
		rooms.put(id, room);
	}

	
	public boolean isRegistered(int phoneNumber) {
		return guests.containsKey(phoneNumber);
	}

	
	public Guest registerGuest(String name, String address, int phoneNumber) {
		if (guests.containsKey(phoneNumber)) {
			throw new RuntimeException("Phone number already registered");
		}
		Guest guest = new Guest(name, address, phoneNumber);
		guests.put(phoneNumber, guest);		
		return guest;
	}

	
	public Guest findGuestByPhoneNumber(int phoneNumber) {
		Guest guest = guests.get(phoneNumber);
		return guest;
	}

	
	public Booking findActiveBookingByRoomId(int roomId) {
		Booking booking = activeBookingsByRoomId.get(roomId);;
		return booking;
	}


	public Room findAvailableRoom(RoomType selectedRoomType, Date arrivalDate, int stayLength) {
		IOUtils.trace("Hotel: checkRoomAvailability");
		Map<Integer, Room> rooms = roomsByType.get(selectedRoomType);
		for (Room room : rooms.values()) {
			IOUtils.trace(String.format("Hotel: checking room: %d",room.getId()));
			if (room.isAvailable(arrivalDate, stayLength)) {
				return room;
			}			
		}
		return null;
	}

	
	public Booking findBookingByConfirmationNumber(long confirmationNumber) {
		return bookingsByConfirmationNumber.get(confirmationNumber);
	}

<<<<<<< HEAD:src/entities/Hotel.java
	
	public long book(Room room, Guest guest, 
			Date arrivalDate, int stayLength, int occupantNumber,
			CreditCard creditCard) {
		// TODO Auto-generated method stub
		return 0L;		
=======
	public long book(Room room, Guest guest,
			Date arrivalDate, int stayLength, int occupantNumber,
			CreditCard creditCard) {

        Booking booking = room.book(guest,arrivalDate,stayLength,occupantNumber,creditCard);
        if (booking !=null){

            bookingsByConfirmationNumber.put(booking.getConfirmationNumber(),booking);
            return booking.getConfirmationNumber();
        }

        else {
            return 0L;
        }

>>>>>>> master:entities/Hotel.java
	}

	
	public void checkin(long confirmationNumber) {
<<<<<<< HEAD:src/entities/Hotel.java
		// TODO Auto-generated method stub
=======

        Booking booking = findBookingByConfirmationNumber(confirmationNumber);

        if(booking == null){

            throw new RuntimeException();

        }

        else {

            booking.checkIn();
            booking.getRoom().checkin();
            activeBookingsByRoomId.put(booking.getRoom().getId(),booking);

        }
>>>>>>> master:entities/Hotel.java
	}


	public void addServiceCharge(int roomId, ServiceType serviceType, double cost) {
<<<<<<< HEAD:src/entities/Hotel.java
		// TODO Auto-generated method stub
=======

	    Booking booking = activeBookingsByRoomId.get(roomId);

	    if(booking == null ){

	        throw new RuntimeException();
        }
        else {

            booking.addServiceCharge(serviceType,cost);

        }
>>>>>>> master:entities/Hotel.java
	}

	
	public void checkout(int roomId) {
<<<<<<< HEAD:src/entities/Hotel.java
		// TODO Auto-generated method stub
=======

	    Booking booking = activeBookingsByRoomId.get(roomId);

	    if(booking == null){

	        throw new RuntimeException();


        }
        else {

            booking.checkOut();
        }
>>>>>>> master:entities/Hotel.java
	}


}