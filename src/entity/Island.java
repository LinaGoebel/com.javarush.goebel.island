package entity;

public class Island {

  private int columnsCount;
  private int rowsCount;

  public Island(int columnsCount, int rowsCount) {
    this.columnsCount = columnsCount;
    this.rowsCount = rowsCount;
  }

  public Location[][] locations = new Location[columnsCount][rowsCount];

}
