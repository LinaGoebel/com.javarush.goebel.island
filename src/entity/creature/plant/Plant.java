package entity.creature.plant;

import entity.creature.Creature;

public class Plant extends Creature {

  private int quantity;
  private int growthRate;
  private int nutritionValue;

  public Plant() {
    this.quantity = 1;
    this.growthRate = 1;
    this.nutritionValue = 1;
  }

  public int getNutritionValue() {
    return nutritionValue;
  }

  public void grow(int rate) {
    this.quantity += rate * this.growthRate;
    if (this.quantity > 20) {
      this.quantity = 20;
    }
  }

  public void setQuantity(int quantity) {
    if (quantity < 0) {
      this.quantity = 0;
    } else if (quantity > 20) {
      this.quantity = 20;
    } else {
      this.quantity = quantity;
    }
  }

  public int getQuantity() {
    return quantity;
  }

  public void setGrowthRate(int growthRate) {
    if (growthRate < 0) {
      this.growthRate = 0;
    } else {
      this.growthRate = growthRate;
    }
  }


  public void reduce(int amount) {
    this.quantity -= amount;
    if (this.quantity < 0) {
      this.quantity = 0;
    }
  }

  public String getSymbol() {
    return "🌱";
  }
}

