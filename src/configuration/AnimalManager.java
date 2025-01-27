package configuration;

import entity.Island;
import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import java.util.ArrayList;
import java.util.List;

public class AnimalManager {

  private final Island island;

  public AnimalManager(Island island) {
    this.island = island;
  }

  public void updateAnimals() {
    for (int x = 0; x < island.getColumnsCount(); x++) {
      for (int y = 0; y < island.getRowsCount(); y++) {
        Location loc = island.getLocation(x, y);
        List<Animal> animals = new ArrayList<>(loc.getAnimals());

        for (Animal animal : animals) {
          animal.decreaseSatiety();

          if (animal.isDead()) {
            loc.removeAnimal(animal);
            continue;
          }

          boolean atePlant = false;
          for (Plant plant : loc.getPlants()) {
            if (animal.eat(plant)) {
              atePlant = true;
              break;
            }
          }

          if (!atePlant) {
            for (Animal other : new ArrayList<>(loc.getAnimals())) {
              if (animal != other && animal.eat(other)) {
                loc.removeAnimal(other);
                break;
              }
            }
          }

          animal.move(island);

          Animal offspring = animal.reproduce();
          if (offspring != null) {
            System.out.println(animal.getSymbol() + " размножился!");
            loc.addAnimal(offspring);
          }
        }
      }
    }
  }
}


