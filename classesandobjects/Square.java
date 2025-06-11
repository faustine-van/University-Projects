package classesandobjects;

public class Square {
    private int size;

    public Square(){
        this(0);
    }
    public Square(int square_size){
        if (square_size < 0){
            throw new IllegalArgumentException("Size must be a positive number");
        }
        this.size = square_size;
    }
    public int getSize(){
        return size;
    }
    public void setSize(int size ){
        if (size < 0) {
            throw new IllegalArgumentException("Size must be a positive number");
        }  
        this.size = size;
    }
    public int area(){
        return size * size;
    }

    public void draw(int len){
        if (this.size != 0){
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    System.out.print("#");
                }
                System.out.println("");

            }
        }
    }
}
