import processing.core.*;

public class Physics extends Animator {
  
  int trail_length = 20;
  int prev_ball_x[];
  int prev_ball_y[];
  
  // Note on this sketch's coordinate space:
  // We're dealing in meters.
  // The arena_size variable scales the simulation to the "screen"
  float ball_x, ball_y;
  float ball_vx, ball_vy;
  float hardness = 0.98f;
  float mass = 0.2f;
  
  float arena_size = 1000;
  
  float g = 9.8f;
  
  // color 1 is ball, color 2 is trail
  public Physics(PApplet app, int size, int color1, int color2) {
    super(app, size, color1, color2);
    
    prev_ball_x = new int[trail_length];
    prev_ball_y = new int[trail_length];
    
    ball_x = 5;
    ball_y = 0;
    ball_vx = 5f;
    ball_vy = 0;
  }
  
  public int[][] drawFrame() {
    int[][] pix = new int[size][size];
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        pix[x][y] = color1;
      }
    }
    
    ball_x += ball_vx;
    ball_y += ball_vy;
    
    if (ball_y >= arena_size) {
      ball_vy = -(ball_vy * hardness);
    }
    else {
      ball_vy += g * mass;
    }
    
    if (ball_x < 0 || ball_x >= arena_size) {
      ball_vx = -ball_vx;
    }
    
    int x = (int)((ball_x / arena_size) * size);
    int y = (int)((ball_y / arena_size) * size);
    
    
    
    for (int i = 0; i < trail_length; i++) {
      if (prev_ball_x[i] >= 0 && prev_ball_x[i] < size &&
          prev_ball_y[i] >= 0 && prev_ball_y[i] < size) {
        pix[prev_ball_x[i]][prev_ball_y[i]] = parent.lerpColor(color2, color1, (float)i / (float)trail_length);
      }
    }
    
    if (x >= 0 && x < size &&
        y >= 0 && y < size) {
      pix[x][y] = 0;
      
    }
    
    // Trail queue
    for (int i = trail_length - 1; i > 0; i--) {
      prev_ball_x[i] = prev_ball_x[i-1];
      prev_ball_y[i] = prev_ball_y[i-1];
    }
    prev_ball_x[0] = x;
    prev_ball_y[0] = y;
    
    return pix;
  }
}