package classesandobjects;

public class Rectangle extends Square {
    private int width;
    private int height;

    public Rectangle(){
        this(0, 0);
    }
    public Rectangle(int w, int h){
        this.width = w;
        this.height = h;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int area(){
       int area = height * width;
       return area;
    }
    public int perimeter(){
        int p = (width + height) / 2;
        return p;
    }
    public void draw(int w, int h){
         if (this.width != 0 || this.height != 0){
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print("*");
                }
                System.out.println("");

            }
        }
    }
}