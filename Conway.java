import processing.core.*;

public class Conway extends Animator {
  boolean[][] grid;
  
  int num_filled = 0;
  int iterations_same_num = 0;
  int startThreshold = 80;
  
  int speed_divider = 4;
  int time_counter = 0;
  
  
  public Conway(PApplet app, int size, int color1, int color2) {
    super(app, size, color1, color2);
    grid = new boolean[size][size];
    
    while (num_filled == 0) {
      initialize();
    }
  }
  
  public int[][] drawFrame() {
    int[][] pix = new int[size][size];
    int prev_num_filled = num_filled;
    
    if (iterations_same_num == 10) {
      initialize();
    }
    // Rules of the game
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        if (time_counter % speed_divider == 0) {
          int foodNum = 0;
          
          // Check neighbors for food
          boolean look_left  = x > 0; 
          boolean look_right = x < size - 1;
          boolean look_up    = y > 0;
          boolean look_down  = y < size - 1;
          
          // Adjacents
          foodNum += (look_left  && grid[ x - 1 ][ y     ]) ? 1 : 0;
          foodNum += (look_up    && grid[ x     ][ y - 1 ]) ? 1 : 0;
          foodNum += (look_right && grid[ x + 1 ][ y     ]) ? 1 : 0;
          foodNum += (look_down  && grid[ x     ][ y + 1 ]) ? 1 : 0;
          
          // Corners
          foodNum += (look_left  && look_up   && grid[ x - 1 ][ y - 1 ]) ? 1 : 0;
          foodNum += (look_right && look_up   && grid[ x + 1 ][ y - 1 ]) ? 1 : 0;
          foodNum += (look_left  && look_down && grid[ x - 1 ][ y + 1 ]) ? 1 : 0;
          foodNum += (look_right && look_down && grid[ x + 1 ][ y + 1 ]) ? 1 : 0;
          
          // The rules
          // Live cell under-population
          if (grid[x][y] && foodNum < 2) {
            grid[x][y] = false;
            num_filled--;
          }
          
          // Live cell over-population
          if (grid[x][y] && foodNum > 3) {
            grid[x][y] = false;
            num_filled--;
          }
          
          // Dead cell reproduction
          if (!grid[x][y] && foodNum == 3) {
            grid[x][y] = true;
            num_filled++;
          }
          
          // Two implied rules:
          //   Cells with 2-3 neighbors live another iteration
          //   Cells that are dead remain dead unless there are 3 live neighbors
          
        }
        
        pix[x][y] = (grid[x][y]) ? color1 : color2;
      }
    }
    
    if (time_counter % speed_divider == 0) {
      if (prev_num_filled == num_filled) {
        iterations_same_num++;
      } 
    }
    
    time_counter++;
    return pix;
  }
  
  private void initialize() {
    iterations_same_num = 0;
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        if (parent.random(100) > startThreshold)
        {
          grid[x][y] = true;
          num_filled++;
        }
      }
    }
  }
}