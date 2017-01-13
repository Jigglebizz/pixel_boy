import processing.core.*;

public class Vortex extends Animator {
  float density = 2f;
  float offset = 0;
  float increment = 0.1f;
  
  float center_offset = 0;
  float center_increment = 0.01f;
  
  float center_x, center_y;
  
  public Vortex(PApplet app, int size, int color1, int color2) {
    super(app, size, color1, color2);
    center_x = (size / 2) - 0.5f;
    center_y = (size / 2) - 0.5f;
  }
  
  public int[][] drawFrame() {
    int[][] pix = new int[size][size];
    
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        float distance = parent.sqrt(parent.pow(center_x - x,2) + parent.pow(center_y - y,2));
        float brightness = 0.5f + 0.5f * parent.sin((distance * density) - offset);
        
        pix[x][y] = parent.lerpColor( color1, color2, brightness);
      }
    }
    
    offset += increment;
    center_offset += center_increment;
    
    center_x = (size / 2) - 0.5f + parent.sin(center_offset);
    center_y = (size / 2) - 0.5f + parent.cos(center_offset);
    
    return pix;
  }
}