package com.legacytest.trip;

import java.util.List;

import com.legacytest.exception.CollaboratorCallException;
import com.legacytest.user.User;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException("TripDAO should not be invoked on an unit test.");
	}

}