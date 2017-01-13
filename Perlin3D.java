import processing.core.*;

public class Perlin3D extends Animator {
  float increment = 0.05f;
  float zoff = 0.0f;
  float zincrement = 0.03f;
  
  float color2offset = 20;
  
  public Perlin3D(PApplet app, int size, int color1, int color2) {
    super(app, size, color1, color2);
  }
  
  public int[][] drawFrame() {
    float xoff = 0.0f;
    
    for (int y = 0; y < size; y++) {
      xoff += increment;
      float yoff = 0.0f;
      
      for (int x = 0; x < size; x++) {
        yoff += increment;
        float bright1 = parent.noise(xoff,yoff,zoff);
        bright1 = (bright1 * 5) - 2.5f;
        bright1 = clamp(bright1);
        
        float bright2 = parent.noise(xoff+color2offset, yoff+color2offset, zoff);
        bright2 = (bright2 * 5) - 2.5f;
        bright2 = clamp(bright2);
        
        int fgColor = parent.color( bright1 * parent.red(color1), 
                                    bright1 * parent.green(color1), 
                                    bright1 * parent.blue(color1));
                                  
        int bgColor = parent.color ( bright2 * parent.red(color2),
                                     bright2 * parent.green(color2),
                                     bright2 * parent.blue(color2));                                        
        
        pix[x][y] = parent.lerpColor(fgColor, bgColor, bright2 / bright1);
      }
    }
    zoff+=zincrement;
    return pix;
  }
  
  private float clamp(float value) {
    return value < 0 ? 0 : value > 1 ? 1 : value;
  }
}