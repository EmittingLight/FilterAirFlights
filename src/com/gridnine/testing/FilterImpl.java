package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class FilterImpl implements Filter {

    private final List<Flight> flights;

    public FilterImpl(List<Flight> flights) {
        this.flights = flights;
    }

    // 1. вылет до текущего момента времени
    @Override
    public List<Flight> flightUpToTheCurrentPointInTime() {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> !segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    // 2. сегменты с датой прилёта раньше даты вылета
    @Override
    public List<Flight> departureDateAndArrivalDate() {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> !segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    //3. общее время, проведённое на земле
    @Override
    public List<Flight> differenceInTime() {
        return flights.stream().filter(flight -> !(flight.getSegments().size() > 1 && flight.getSegments().stream().reduce(ChronoUnit.HOURS.between(
                        flight.getSegments().get(0).getDepartureDate(),
                        flight.getSegments().get(flight.getSegments().size() - 1).getDepartureDate()),
                (period, s) -> {
                    long temp = Math.abs(ChronoUnit.HOURS.between(s.getArrivalDate(), s.getDepartureDate()));
                    return period - temp;
                },
                Long::sum) > 2)).collect(Collectors.toList());
    }
}
