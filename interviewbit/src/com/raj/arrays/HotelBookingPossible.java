package com.raj.arrays;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;

public class HotelBookingPossible {

    /**
     * Hotel Bookings Possible
     A hotel manager has to process N advance bookings of rooms for the next season. His hotel has K rooms. Bookings contain an arrival date and a departure date. He wants to find out whether there are enough rooms in the hotel to satisfy the demand. Write a program that solves this problem in time O(N log N) .

     Input:


     First list for arrival time of booking.
     Second list for departure time of booking.
     Third is K which denotes count of rooms.

     Output:

     A boolean which tells whether its possible to make a booking.
     Return 0/1 for C programs.
     O -> No there are not enough rooms for N booking.
     1 -> Yes there are enough rooms for N booking.
     Example :

     Input :
     Arrivals :   [1 3 5]
     Departures : [2 6 8]
     K : 1

     Return : False / 0

     At day = 5, there are 2 guests in the hotel. But I have only one room.
     */
    private int[] num_roomsOccupiedByDay = new int[10000];

    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {

        Collections.sort(arrive);  // O(nlogn)
        Collections.sort(depart);

        for (int i=0; i<arrive.size(); i++) {
            fillRooms(arrive, depart, i);
        }

        for (int i:num_roomsOccupiedByDay) if (i > K) return false;

        return true;
    }

    private void fillRooms(ArrayList<Integer> arrive, ArrayList<Integer> depart, int i) {
        for (int j=arrive.get(i); j<depart.get(i); j++) {
            num_roomsOccupiedByDay[j]++;
        }
    }

    public static void main(String[] args) {
        HotelBookingPossible hotelBookingPossible = new HotelBookingPossible();
        System.out.println(hotelBookingPossible.hotel(Lists.newArrayList(3,5,2,4,6), Lists.newArrayList(4,8,4,7,9), 2));
    }
}
