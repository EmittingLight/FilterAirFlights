package com.gridnine.testing;

import java.util.List;

public interface Filter {
    List<Flight> flightUpToTheCurrentPointInTime();
    List<Flight> departureDateAndArrivalDate();
    List<Flight> differenceInTime();

}
