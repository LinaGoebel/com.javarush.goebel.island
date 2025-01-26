package entity;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import java.util.ArrayList;
import java.util.List;

public class Location {

  private final int x;
  private final int y;

  private List<Animal> animals = new ArrayList<>();
  private List<Plant> plants = new ArrayList<>();

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public List<Animal> getAnimals() {
    return animals;
  }

  public List<Plant> getPlants() {
    return plants;
  }

  public void addAnimal(Animal animal) {
    animals.add(animal);
  }

  public void removeAnimal(Animal animal) {
    animals.remove(animal);
  }

  public void addPlant() {
    plants.add(new Plant());
  }

  public void removePlant(Plant plant) {
    plants.remove(plant);
  }
}

