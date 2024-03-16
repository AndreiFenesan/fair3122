package pizzashop.repository;

import pizzashop.model.PizzaItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PizzaRepository {
    private static String filename = "data/menu.txt";
    private List<PizzaItem> pizzaMenu;

    public PizzaRepository(){
    }

    private void readMenu(){
        //ClassLoader classLoader = MenuRepository.class.getClassLoader();
        File file = new File(filename);
        this.pizzaMenu = new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                PizzaItem menuItem=getMenuItem(line);
                pizzaMenu.add(menuItem);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PizzaItem getMenuItem(String pizzaLineDescription){
        PizzaItem item=null;
        if (pizzaLineDescription==null|| pizzaLineDescription.equals("")) return null;
        StringTokenizer st=new StringTokenizer(pizzaLineDescription, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new PizzaItem(name, 0, price);
        return item;
    }

    public List<PizzaItem> getMenu(){
        readMenu();//create a new menu for each table, on request
        return pizzaMenu;
    }
}
