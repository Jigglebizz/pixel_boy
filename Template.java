import processing.core.*;

public class Template extends Animator {
  
  public Template(PApplet app, int size, int color1, int color2) {
    super(app, size, color1, color2);
  }
  
  public int[][] drawFrame() {
    return new int[size][size];
  }
}