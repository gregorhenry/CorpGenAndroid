package pe.edu.utp.corpgenprojectburningencounters.models;

/**
 * Created by Heisenberg on 28/10/2017.
 */

public class Pub {
    private String name;
    private String address;
    private String event;
    private String horary;

    public Pub(String name, String address, String event, String horary) {
        this.name = name;
        this.address = address;
        this.event = event;
        this.horary = horary;
    }

    public Pub() {
    }

    public String getName() {
        return name;
    }

    public Pub setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Pub setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEvent() {
        return event;
    }

    public Pub setEvent(String event) {
        this.event = event;
        return this;
    }

    public String getHorary() {
        return horary;
    }

    public Pub setHorary(String horary) {
        this.horary = horary;
        return this;
    }
}
