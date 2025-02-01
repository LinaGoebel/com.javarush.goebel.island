package entity.creature.animal;

import entity.Island;
import entity.Location;
import entity.creature.Creature;
import entity.creature.plant.Plant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Creature {

  protected int satiety;
  protected int maxSatiety;
  protected int maxPopulationPerCell;
  protected int movementSpeed;

  protected Location location;
  private boolean isDead = false;

  public Animal(Location location) {
    this.location = location;
    if (location != null) {
      location.addAnimal(this);
    }
  }

  public void decreaseSatiety() {
    this.satiety -= 1;
    if (this.satiety <= 0) {
      this.satiety = 0;
      this.die();
    }
  }

  public boolean isDead() {
    return isDead;
  }

  public boolean eat(Creature creature) {
    if (isDead) {
      return false;
    }

    if (creature instanceof Plant) {
      Plant plant = (Plant) creature;
      int foodConsumed = Math.min(plant.getQuantity(), this.maxSatiety - this.satiety);
      if (foodConsumed > 0) {
        plant.reduce(foodConsumed);
        this.satiety += foodConsumed;
        System.out.println(this.getSymbol() + " ate the plant.");
        return true;
      }
    } else if (creature instanceof Animal prey) {
      double eatProbability = calculateEatProbability(prey);
      if (ThreadLocalRandom.current().nextDouble() < eatProbability) {
        this.satiety += prey.weight;
        if (this.satiety > this.maxSatiety) {
          this.satiety = this.maxSatiety;
        }
        System.out.println(this.getSymbol() + " ate " + prey.getSymbol());
        prey.die();
        return true;
      }
    }
    return false;
  }

  public void move(Island island) {
    if (isDead || location == null) {
      return;
    }

    List<Location> neighbors = island.getNeighbors(location.getX(), location.getY());
    if (!neighbors.isEmpty()) {
      Location target = neighbors.get(ThreadLocalRandom.current().nextInt(neighbors.size()));
      location.removeAnimal(this);
      target.addAnimal(this);
      this.location = target;
    }
  }

  public Animal reproduce() {
    if (isDead || location == null) {
      return null;
    }

    int sameSpeciesCount = 0;
    for (Animal animal : location.getAnimals()) {
      if (animal.getClass() == this.getClass()) {
        sameSpeciesCount++;
      }
    }

    if (sameSpeciesCount > 1 && sameSpeciesCount < maxPopulationPerCell
        && ThreadLocalRandom.current().nextDouble() < 0.8) {
      Animal offspring = createOffspring(location);
      System.out.println(this.getSymbol() + " multiplied!");
      return offspring;
    }
    return null;
  }

  public void die() {
    if (location != null) {
      location.removeAnimal(this);
      location = null;
    }
    isDead = true;
  }

  protected abstract double calculateEatProbability(Creature creature);

  protected abstract int calculateNutritionValue(Creature creature);

  protected abstract Animal createOffspring(Location location);

  public abstract String getSymbol();
}
