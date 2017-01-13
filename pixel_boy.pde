int grid_size = 10;
color[][] pix = new color[grid_size][grid_size];

color blue  = color(40, 122, 255);
color yellow = color(255,219,40);
color green = color(16, 209, 96);
color pink  = color(255, 112, 219);


int pix_size;

Animator anim;

void setup() {
  size(1000, 1000);
  frame.setLocation(displayWidth/2-width/2,displayHeight/2-height/2);
  frameRate(30);
  pix_size = width / grid_size;
  
  anim = new Physics(this, grid_size, 
                            color(16, 209, 96),
                            color(255, 112, 219));
}

void draw() {
  pix = anim.drawFrame();
  
  noStroke();
  for (int y = 0; y < grid_size; y++) {
    for (int x = 0; x < grid_size; x++) {
      fill(pix[x][y]);
      rect(x * pix_size, y * pix_size, pix_size, pix_size);
    }
  }
}

void keyReleased() {
  if (key == 'q') {
    anim.setColor(Animator.COLOR1, blue);
  }
  else if (key == 'w') {
    anim.setColor(Animator.COLOR1, yellow);
  }
  else if (key == 'e') {
    anim.setColor(Animator.COLOR1, green);
  }
  else if (key == 'r') {
    anim.setColor(Animator.COLOR1, pink);
  }
  
  
  if (key == 'a') {
    anim.setColor(Animator.COLOR2, blue);
  }
  else if (key == 's') {
    anim.setColor(Animator.COLOR2, yellow);
  }
  else if (key == 'd') {
    anim.setColor(Animator.COLOR2, green);
  }
  else if (key == 'f') {
    anim.setColor(Animator.COLOR2, pink);
  }
}