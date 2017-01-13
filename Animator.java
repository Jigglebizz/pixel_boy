import processing.core.*;

public abstract class Animator {
  public static final int COLOR1 = 0;
  public static final int COLOR2 = 1;
  
  protected PApplet parent;
  protected int size;
  protected int[][] pix;
  protected int color1, color2;
  
  public Animator(PApplet app, int size, int color1, int color2) {
    parent = app;
    this.size = size;
    pix = new int[size][size];
    this.color1 = color1;
    this.color2 = color2;
  }
  public abstract int[][] drawFrame();
  
  public void setColor(int cNum, int cVal) {
    if (cNum == COLOR1)
      color1 = cVal;
    else if (cNum == COLOR2)
      color2 = cVal;
    
  }
}