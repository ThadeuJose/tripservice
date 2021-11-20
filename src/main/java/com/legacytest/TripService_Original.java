package com.legacytest;

import java.util.ArrayList;
import java.util.List;

import com.legacytest.exception.UserNotLoggedInException;
import com.legacytest.trip.Trip;
import com.legacytest.trip.TripDAO;
import com.legacytest.user.User;
import com.legacytest.user.UserSession;

public class TripService_Original {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = UserSession.getInstance().getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = TripDAO.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

}
