import classesandobjects.Square;
import classesandobjects.Rectangle;

public class Demo {

    public static void main(String[] args) {
        Square my_square = new Square(4);
        Rectangle my_rect = new Rectangle(5, 6);

        try {
            System.out.println("---- Square -----");
            int size = my_square.getSize();
            my_square.draw(size);
            int s = my_square.area(); // area of square
            System.out.println("The area is " + s);
            System.out.println("");
            System.out.println("");
            System.out.println("---- Rectangle -----");
            int r = my_rect.area(); // area of rectabgle
            System.out.println("The area is " + r);
            int width = 15;
            int height = 4;
            my_rect.draw(width, height);


        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }

         
    }
}
