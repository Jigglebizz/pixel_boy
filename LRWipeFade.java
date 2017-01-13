import processing.core.*;

public class LRWipeFade extends Animator {
  float triangle_wave = 0;
  float tri_slope = 0.03f;
  
  float clip = 0.3f;
  
  public LRWipeFade(PApplet app, int size, int color1, int color2) {
    super(app, size, color1, color2);
  }
  
  public int[][] drawFrame() {
    int[][] pix = new int[size][size];
    
    triangle_wave += tri_slope;
    
    if (triangle_wave >= 1 || triangle_wave < 0) tri_slope = -tri_slope;
    
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        
        float triangle_adjust_for_clip = triangle_wave * (1f + clip * 2) - clip;
        
        pix[x][y] = (x < triangle_adjust_for_clip * (size - 1)) ? color1 : color2;
      }
    }
    return pix;
  }
}