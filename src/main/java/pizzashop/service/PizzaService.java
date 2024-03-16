package pizzashop.service;

import pizzashop.model.PizzaItem;
import pizzashop.repository.PizzaRepository;

import java.util.List;

public class PizzaService {

    private PizzaRepository menuRepo;
    public PizzaService(PizzaRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    public List<PizzaItem> getMenuData() {
        return menuRepo.getMenu();
    }

}