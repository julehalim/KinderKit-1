import java.util.Scanner;

public class DrawingCanvas {
//instance variables
  private char background;
  private int width;
  private int height;

//default constructor
  public DrawingCanvas(){
    setBackground('-');
    setHeight(10);
    setWidth(6);
  }

//constructor
  public DrawingCanvas(int width,int height,char background ){
    setBackground(background);
    setWidth(width);
    setHeight(height);
  }

//copy constructor
  public DrawingCanvas(DrawingCanvas drawingCanvas){
    background=drawingCanvas.background;
    width=drawingCanvas.width;
    height=drawingCanvas.height;
  }

//accessors
  public char getBackground(){
    return background;
  }
  
  public int getWidth(){
    return width;
  }
  
  public int getHeight(){
    return height;
  }
//mutators
  public void setBackground(char background){
    this.background=background;
  }

  public void setWidth(int width){
    this.width=width;
  }

  public void setHeight(int height){
    this.height=height;
  }
}
