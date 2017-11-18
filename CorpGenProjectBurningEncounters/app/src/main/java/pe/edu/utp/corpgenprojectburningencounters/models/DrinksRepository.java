package pe.edu.utp.corpgenprojectburningencounters.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 18/11/2017.
 */

public class DrinksRepository {
    public static List<BuyDrinks> getDrinks(){
        List<BuyDrinks> drinks = new ArrayList<>();
        drinks.add(new BuyDrinks("Sample Name1","Sample Description1",14));
        drinks.add(new BuyDrinks("Sample Name2","Sample Description2",12));
        drinks.add(new BuyDrinks("Sample Name3","Sample Description3",41));
        drinks.add(new BuyDrinks("Sample Name4","Sample Description4",51));
        return drinks;
    }
}
