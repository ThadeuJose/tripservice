package com.legacytest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.legacytest.exception.UserNotLoggedInException;
import com.legacytest.trip.TripService;
import com.legacytest.user.User;

import org.junit.jupiter.api.Test;

public class TripServiceTest {

    User user;

    @Test
    public void shouldRaiseErrorIfUserNotLogged() {
        TripService tripService = new TestTripService();
        user = null;
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(null));
    }

    class TestTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return user;
        }
    }

}
