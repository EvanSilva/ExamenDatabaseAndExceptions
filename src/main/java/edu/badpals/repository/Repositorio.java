package edu.badpals.repository;

import edu.badpals.domain.MagicalItem;
import edu.badpals.domain.Order;
import edu.badpals.domain.Wizard;
import edu.badpals.domain.WizardPersona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Negative;
import jakarta.ws.rs.ApplicationPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class Repositorio {

    public Optional<Wizard> loadWizard(String name) {

        Optional<Wizard> wizardToLoad = Wizard.findByIdOptional(name);

        return wizardToLoad;
    }

    public Optional<MagicalItem> loadItem(String itemName) {

        Optional<MagicalItem> itemToLoad = MagicalItem.find("name", itemName).firstResultOptional();

        return itemToLoad;

    }

    public Optional<MagicalItem> loadItem(MagicalItem magicalItem) {

        Optional<MagicalItem> itemToLoad = MagicalItem.find("name = ?1 and quality = ?2 and type = ?3", magicalItem.getName(), magicalItem.getQuality(), magicalItem.getType()).firstResultOptional();

        return itemToLoad;
    }

    public List<MagicalItem> loadItems(String name) {

        List<MagicalItem> listaCompleta = MagicalItem.listAll();
        List<MagicalItem> listaFiltrada = new ArrayList<>();

        for (MagicalItem item : listaCompleta) {

            if (item.getName().equals(name)) {

                listaFiltrada.add(item);

            }
        }
        return listaFiltrada;
    }

    @Transactional
    public Optional<Order> placeOrder(String wizard, String magicalItem) {

        Optional<Wizard> wizardBuyer = Wizard.findByIdOptional(wizard);
        Optional<MagicalItem> itemToBuy = MagicalItem.find("name = ?1", magicalItem ).firstResultOptional();

        if (wizardBuyer.isPresent() && itemToBuy.isPresent() && wizardBuyer.get().getWizard() != WizardPersona.MUDBLOOD ) {

            Order nuevaOrden = new Order(wizardBuyer.get(), itemToBuy.get());
            nuevaOrden.persist();
            return Optional.ofNullable(nuevaOrden);

        }
        return Optional.empty();
    }

    @Transactional
    public void createItem(String name, int quality, String type) {

        MagicalItem itemToCreate = new MagicalItem(name, quality , type);
        itemToCreate.persist();

    }

    @Transactional
    public void createItems(List<MagicalItem> listToLoad) {

        for (MagicalItem item : listToLoad) {

            item.persist();

        }

    }

    @Transactional
    public void deleteItem(MagicalItem item) {

        Optional<MagicalItem> itemToDelete = MagicalItem.find("name = ?1 and quality = ?2 and type = ?3", item.getName(), item.getQuality(), item.getType()).firstResultOptional();

        if (itemToDelete.isPresent()) {
            itemToDelete.get().delete();
        }
    }


}
