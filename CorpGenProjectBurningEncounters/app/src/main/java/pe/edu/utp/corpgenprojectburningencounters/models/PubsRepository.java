package pe.edu.utp.corpgenprojectburningencounters.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heisenberg on 28/10/2017.
 */

public class PubsRepository {
    public static List<Pub> getPubs(){
        List<Pub> pubs = new ArrayList<>();

        pubs.add(new Pub("Sample Name1", "Address1", "event1","horary1"));
        pubs.add(new Pub("Sample Name2", "Address2", "event2","horary2"));
        pubs.add(new Pub("Sample Name3", "Address3", "event3","horary3"));
        pubs.add(new Pub("Sample Name4", "Address4", "event4","horary4"));
        pubs.add(new Pub("Sample Name5", "Address5", "event5","horary5"));
        return pubs;

    }
}
