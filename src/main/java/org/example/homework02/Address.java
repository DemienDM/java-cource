package org.example.homework02;

public final class Address {

    private final String city;

    private final String street;

    Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Address{"
                + "city='" + city + '\''
                + ", street='" + street + '\''
                + '}';
    }
}
