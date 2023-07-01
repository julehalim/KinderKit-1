sideimport java.util.Scanner;

public class Rectangle {
//instance variables
  private int width;
  private int height;
  private char printingChar;

//default constructor
  public Rectangle(){
    setWidth(5);
    setHeight(6);
    setPrintingChar('*');
  }

//constructor
  public Rectangle(int width,int height,char printingChar){
    setWidth(width);
    setHeight(height);
    setPrintingChar(printingChar);
  }

//copy constructor
  public Rectangle(Rectangle rectangle){
    setWidth(rectangle.width);
    setHeight(rectangle.height);
    setPrintingChar(rectangle.printingChar);
  }

//accessors
  public int getWidth(){
    return width;
  }

  public int getHeight(){
    return height;
  }
  
  public char getPrintingChar(){
    return printingChar;
  }

//mutators
  public void setWidth(int width){
    this.width=width;
  }

  public void setHeight(int height){
    this.height=height;
  }
  
  public void setPrintingChar(char printingChar){
    this.printingChar=printingChar;
  }

//print board with shape
  public void rectanglePrint(Rectangle rectangle,DrawingCanvas drawingCanvas,int horizontalCount,int verticalCount){
  //includes move commands
  //printing up/down move commands
    for (int k=0;k<verticalCount;k++){
      for (int n=0;n<drawingCanvas.getWidth();n++){
        System.out.print(drawingCanvas.getBackground());
      }
      System.out.println("");
    } 
  //printing left/right commands and rest of board
    for (int i=0;i<drawingCanvas.getHeight()-verticalCount;i++){
      for(int n=0;n<horizontalCount;n++){
        System.out.print(drawingCanvas.getBackground());
      }
      for(int j=0;j<drawingCanvas.getWidth()-horizontalCount;j++){
        if((j<rectangle.getWidth())&&(i<rectangle.getHeight())){
            System.out.print(rectangle.getPrintingChar());
          }
        else{
          System.out.print(drawingCanvas.getBackground());
        }
      }
      System.out.println("");  
    }
  }
}
