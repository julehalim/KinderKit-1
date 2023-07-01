import java.util.Scanner;

public class Triangle {
//instance variables
  private int sideLength;
  private char printingChar;

//default constructor
  public Triangle(){
    setSideLength(5);
    setPrintingChar('*');
  }

//constructor
  public Triangle(int sideLength, char printingChar){
    setSideLength(sideLength);
    setPrintingChar(printingChar);
  }

//copy constructor
  public Triangle(Triangle triangle){
    setPrintingChar(triangle.printingChar);
    setSideLength(triangle.sideLength);
  }

//accessors
  public int getSideLength(){
    return sideLength;
  }

  public char getPrintingChar(){
    return printingChar;
  }
  
//mutators
  public void setSideLength(int sideLength){
    this.sideLength=sideLength;
  }

  public void setPrintingChar(char printingChar){
    this.printingChar=printingChar;
  }

//print board with shape
  public void trianglePrint(Triangle triangle,DrawingCanvas drawingCanvas,int horizontalCount,int verticalCount){
  //includes move commands
  //printing up/down move commands
    for(int o=0;o<verticalCount;o++){
      for (int p=0;p<drawingCanvas.getWidth();p++){
        System.out.print(drawingCanvas.getBackground());
      }
      System.out.println("");
    }
  //printing left/right commands and rest of board
    for(int i=0;i<(drawingCanvas.getHeight()-verticalCount);i++){
      int j=triangle.getSideLength();
      for(int m=0;m<horizontalCount;m++){
          System.out.print(drawingCanvas.getBackground());
        }
      for (int n=0;n<drawingCanvas.getWidth()-horizontalCount;n++){
        if(n<(j-i)){
          System.out.print(triangle.getPrintingChar());
        }
        else{
          System.out.print(drawingCanvas.getBackground());
        }
      }
      System.out.println("");
    }
  }
}
