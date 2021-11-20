package com.legacytest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.legacytest.exception.UserNotLoggedInException;
import com.legacytest.trip.TripService;
import com.legacytest.user.User;

import org.junit.jupiter.api.Test;

public class TripServiceTest {

    User loggedUser;

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

    // TODO: Test with User friend empty

    class TestTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }
    }

}
