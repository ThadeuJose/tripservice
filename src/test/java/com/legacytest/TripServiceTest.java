package com.legacytest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.legacytest.exception.UserNotLoggedInException;
import com.legacytest.trip.Trip;
import com.legacytest.trip.TripService;
import com.legacytest.user.User;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

    User loggedUser;
    List<Trip> trips;

    @Test
    public void shouldRaiseErrorIfUserNotLogged() {
        TripService tripService = new TestTripService();
        loggedUser = null;
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(null));
    }

    @Test
    public void shouldReturnEmptyListIfUserHasNoFriend() {
        TripService tripService = new TestTripService();
        loggedUser = new User();
        User bob = new User();
        assertThat(tripService.getTripsByUser(bob), is(empty()));
    }

    @Test
    public void shouldReturnTripListIfUserAreFriends() {
        TripService tripService = new TestTripService();
        loggedUser = new User();
        User bob = new User();

        Trip london = new Trip();
        Trip us = new Trip();
        Trip canada = new Trip();

        bob.addFriend(loggedUser);
        bob.addTrip(london);
        bob.addTrip(us);
        bob.addTrip(canada);

        assertThat(tripService.getTripsByUser(bob), Matchers.containsInAnyOrder(bob.trips().toArray()));
    }

    // TODO: Test with User friend empty

    class TestTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return user.trips();
        }
    }

}
