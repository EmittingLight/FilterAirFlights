package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        System.out.println("Список перелетов");
        flights.forEach(System.out::println);
        System.out.println("______________________________________________-");
        Filter filter = new FilterImpl(flights);

        List<Flight> flightUpToTheCurrentPointInTime = filter.flightUpToTheCurrentPointInTime();
        List<Flight> departureDateAndArrivalDate = filter.departureDateAndArrivalDate();
        List<Flight> differenceInTime = filter.differenceInTime();

        System.out.println("Исключены перелёты: вылеты до текущего момента времени");
        flightUpToTheCurrentPointInTime.forEach(System.out::println);
        System.out.println("--------------------------------------");

        System.out.println("Исключены перелёты: с датой прилёта раньше даты вылета");
        departureDateAndArrivalDate.forEach(System.out::println);
        System.out.println("--------------------------------------");

        System.out.println("Исключены перелёты: общее время на земле превышает 2 часа");
        differenceInTime.forEach(System.out::println);
    }
}