import java.util.Scanner;

/**
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Jule Valendo Halim
 * @studentID: 1425567
 * @universityEmail : julevalendoh@student.unimelb.edu.au
 */

public class KinderKit {
  
  public static void main(String[] args) {
    //initialize and declare values
    DrawingCanvas drawingCanvas=new DrawingCanvas();
    drawingCanvas.setWidth(Integer.parseInt(args[0]));
    drawingCanvas.setHeight(Integer.parseInt(args[1]));
    drawingCanvas.setBackground(args[2].charAt(0));

    //welcome message
    System.out.printf("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----%nCurrent drawing canvas settings:%n");
    System.out.println("- Width: "+drawingCanvas.getWidth());
    System.out.println("- Height: "+drawingCanvas.getHeight());
    System.out.printf("- Background character: %c%n%n",drawingCanvas.getBackground());
    
    //initializing scanner
    Scanner scanner=new Scanner(System.in);
    
    //initialize input
    int inputInt=0;

    //main menu
    while (inputInt<1||inputInt>4){
      System.out.println("Please select an option. Type 4 to exit.");
      System.out.println("1. Draw triangles");
      System.out.println("2. Draw rectangles");
      System.out.println("3. Update drawing canvas settings");
      System.out.println("4. Exit");
      inputInt=scanner.nextInt();
      if(inputInt<1||inputInt>4){
        System.out.println("Unsupported option. Please try again!");
        }
        //switch statement for all 4 options
        switch(inputInt){
          case 1:
          while (inputInt==1){
          //initialize max for checking triangle size later
            int max;

          //default constructor
            Triangle triangle=new Triangle();
            if(drawingCanvas.getHeight()>drawingCanvas.getWidth()){
              max=drawingCanvas.getHeight();
              }
              else{
              max=drawingCanvas.getWidth();
              }
              triangle.setSideLength(max+1);

            //get triangle sideLength
              while((triangle.getSideLength()>drawingCanvas.getWidth())||triangle.getSideLength()>drawingCanvas.getHeight()){
                System.out.println("Side length:");
                triangle.setSideLength(scanner.nextInt());
                if((triangle.getSideLength()>drawingCanvas.getWidth())||triangle.getSideLength()>drawingCanvas.getHeight()){
                  System.out.printf("Error! The side length is too long (Current canvas size is %dx%d). Please try again.%n",drawingCanvas.getWidth(),drawingCanvas.getHeight());
                }
              }

            //initialize counts for move 
              int horizontalCount=0;
              int verticalCount=0;

            //get triangle print character
              System.out.println("Printing character:");
              triangle.setPrintingChar(scanner.next().charAt(0));
              triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);

            //zoom or move mode
              int zoomMoveWhile=0;
              while(zoomMoveWhile==0){
                System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
                String zoomOrMove=scanner.next().toLowerCase() ;
                if (!(zoomOrMove.equals("z")||zoomOrMove.equals("m"))){
                  int drawWhile=0;
                //redraw triangle
                  while(drawWhile==0){
                    System.out.println("Draw another triangle (Y/N)?");
                    String confirmation=scanner.next().toLowerCase();
                    if(confirmation.equals("y")){
                      drawWhile=1;
                      zoomMoveWhile=1;
                      break;
                    }
                    if(confirmation.equals("n")){
                      drawWhile=1;
                      zoomMoveWhile=1;
                      inputInt=0;
                      break;
                    }
                    if(!((confirmation.equals("n"))||confirmation.equals("y"))){
                      System.out.println("Unsupported option. Please try again!");
                    }
                  }
                }

              //zooming
                if(zoomOrMove.equals("z")){
                  triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                  while(zoomOrMove.equals("z")){
                    System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
                    String zoomType=scanner.next().toLowerCase();

                   //zoom out 
                    if (zoomType.equals("o")){
                      if(triangle.getSideLength()<=1){
                        System.out.println("This triangle reaches its limit. You cannot make it smaller!");
                        triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                      }
                      else{
                        triangle.setSideLength(triangle.getSideLength()-1);
                        triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                      }
                    }
                  
                  //zoom in
                    if(zoomType.equals("i")){
                      if(triangle.getSideLength()>=(drawingCanvas.getHeight()-verticalCount)||triangle.getSideLength()>=(drawingCanvas.getWidth()-horizontalCount)){
                        System.out.println("This triangle reaches its limit. You cannot make it bigger!");
                        triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                      }
                      if(!(triangle.getSideLength()>=(drawingCanvas.getHeight()-verticalCount)||triangle.getSideLength()>=(drawingCanvas.getWidth()-horizontalCount))){
                        triangle.setSideLength(triangle.getSideLength()+1);
                        triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                      }
                    }

                  //return to zoom/move menu
                    if(!(zoomType.equals("i")||zoomType.equals("o"))){
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                      zoomOrMove="a";
                      break;
                    }
                  }
                }

              //moving
                if(zoomOrMove.equals("m")){
                  triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                  while(zoomOrMove.equals("m")){
                    System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
                    String directionInput=scanner.next().toLowerCase();

                    //return to zoom/move menu
                    if(!(directionInput.equals("a")||directionInput.equals("s")||directionInput.equals("w")||directionInput.equals("z"))){
                    triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    zoomOrMove="a";
                  }

                //move left
                  if(directionInput.equals("a")){
                    if ((horizontalCount-1)<0){
                      System.out.println("You cannot move this triangle outside of the drawing canvas!");
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                    else{
                      horizontalCount=horizontalCount-1;
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                  }

                //move right
                  if(directionInput.equals("s")){
                    if(((horizontalCount+1)+triangle.getSideLength())>drawingCanvas.getWidth()){
                      System.out.println("You cannot move this triangle outside of the drawing canvas!");
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                    else{
                      horizontalCount=horizontalCount+1;
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                  }

                //move up
                  if(directionInput.equals("w")){
                    if((verticalCount-1)<0){
                      System.out.println("You cannot move this triangle outside of the drawing canvas!");
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                    else{
                      verticalCount=verticalCount-1;
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                  }

                //move down
                  if(directionInput.equals("z")){
                    if(((verticalCount+1)+triangle.getSideLength())>drawingCanvas.getHeight()){
                      System.out.println("You cannot move this triangle outside of the drawing canvas!");
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                    else{
                      verticalCount=verticalCount+1;
                      triangle.trianglePrint(triangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                  }
                }
              }
            }
          }
 
          case 2:
        //default constructor
          while (inputInt==2){
              int horizontalCount=0;
              int verticalCount=0;
            Rectangle rectangle=new Rectangle();
            rectangle.setWidth(drawingCanvas.getWidth()+1);

          //getting rectangle width
            while (rectangle.getWidth()>drawingCanvas.getWidth()){
              System.out.println("width:");
              rectangle.setWidth(scanner.nextInt());
              if(rectangle.getWidth()>drawingCanvas.getWidth()){
                System.out.printf("Error! The width is too large (Current canvas size is %dx%d). Please try again.%n",drawingCanvas.getWidth(),drawingCanvas.getHeight());
              }
            }
            rectangle.setHeight(drawingCanvas.getHeight()+1);

          //getting rectangle height
            while (rectangle.getHeight()>drawingCanvas.getHeight()){
              System.out.println("height:");
              rectangle.setHeight(scanner.nextInt());
              if(rectangle.getHeight()>drawingCanvas.getHeight()){
                System.out.printf("Error! The height is too large (Current canvas size is %dx%d). Please try again.%n",drawingCanvas.getWidth(),drawingCanvas.getHeight());
              }
            }

          //getting rectangle background
            System.out.println("Printing character:");
            rectangle.setPrintingChar(scanner.next().charAt(0));
            
          //print initial rectangle
            rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
            
          //zoom or move mode
            int zoomMoveWhile=0;
            while(zoomMoveWhile==0){
              System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
              String zoomOrMove=scanner.next().toLowerCase() ;

            //redraw rectangle
              if (!(zoomOrMove.equals("z")||zoomOrMove.equals("m"))){
                int drawWhile=0;
                while(drawWhile==0){
                  System.out.println("Draw another rectangle (Y/N)?");
                  String confirmation=scanner.next().toLowerCase();
                  if(confirmation.equals("y")){
                    drawWhile=1;
                    zoomMoveWhile=1;
                    break;
                  }
                  if(confirmation.equals("n")){
                    drawWhile=1;
                    zoomMoveWhile=1;
                    inputInt=0;
                    break;
                  }
                  if(!((confirmation.equals("n"))||confirmation.equals("y"))){
                    System.out.println("Unsupported option. Please try again!");
                  }
                }
              }

            //zooming
            //zoom out
              if(zoomOrMove.equals("z")){
                rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                while(zoomOrMove.equals("z")){
                  System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
                  String zoomType=scanner.next().toLowerCase();
                  if (zoomType.equals("o")){
                    if(((rectangle.getHeight()-1)==0)||((rectangle.getWidth()-1)==0)){
                      System.out.println("This rectangle reaches its limit. You cannot make it smaller!");
                      rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                    else{
                      rectangle.setHeight(rectangle.getHeight()-1);
                      rectangle.setWidth(rectangle.getWidth()-1);
                      rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                  }

                //zoom in
                  if(zoomType.equals("i")){
                    if(((rectangle.getHeight()+1)>(drawingCanvas.getHeight()-verticalCount))||((rectangle.getWidth()+1)>(drawingCanvas.getWidth()-horizontalCount))){
                      System.out.println("This rectangle reaches its limit. You cannot make it bigger!");
                      rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                    if(!(((rectangle.getHeight()+1)>(drawingCanvas.getHeight()-verticalCount))||((rectangle.getWidth()+1)>(drawingCanvas.getWidth()-horizontalCount)))){
                      rectangle.setHeight(rectangle.getHeight()+1);
                      rectangle.setWidth(rectangle.getWidth()+1);
                      rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                    }
                  }

                //return to zoom/move menu
                  if(!(zoomType.equals("i")||zoomType.equals("o"))){
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                    zoomOrMove="a";
                    break;
                  }
                }
              }

            //moving
              if(zoomOrMove.equals("m")){
                rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                while(zoomOrMove.equals("m")){
                  System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
                  String directionInput=scanner.next().toLowerCase();

                //return to zoom/move menu
                  if(!(directionInput.equals("a")||directionInput.equals("s")||directionInput.equals("w")||directionInput.equals("z"))){
                  rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  zoomOrMove="a";
                }

              //move left
                if(directionInput.equals("a")){
                  if ((horizontalCount-1)<0){
                    System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  }
                  else{
                    horizontalCount=horizontalCount-1;
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  }
                }

              //move right
                if(directionInput.equals("s")){
                  if(((horizontalCount+1)+rectangle.getWidth())>drawingCanvas.getWidth()){
                    System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  }
                  else{
                    horizontalCount=horizontalCount+1;
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  }
                }

              //move up  
                if(directionInput.equals("w")){
                  if((verticalCount-1)<0){
                    System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  }
                  else{
                    verticalCount=verticalCount-1;
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  }
                }

              //move down
                if(directionInput.equals("z")){
                  if(((verticalCount+1)+rectangle.getHeight())>drawingCanvas.getHeight()){
                    System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  }
                  else{
                    verticalCount=verticalCount+1;
                    rectangle.rectanglePrint(rectangle,drawingCanvas,horizontalCount,verticalCount);
                  }
                }
              }
            }
          }
        }
        break;

      //updating drawingCanvas
        case 3: 
        //update variables
          System.out.print("Canvas width: ");
          drawingCanvas.setWidth(scanner.nextInt());
          System.out.print("Canvas height: ");
          drawingCanvas.setHeight(scanner.nextInt());
          System.out.print("Background character: ");
          drawingCanvas.setBackground(scanner.next().charAt(0));
          System.out.printf("Drawing canvas has been updated!%n%n");

        //print new values;
          System.out.println("Current drawing canvas settings:");
          System.out.println("- Width: "+drawingCanvas.getWidth());
          System.out.println("- Height: "+drawingCanvas.getHeight());
          System.out.printf("- Background character: " +drawingCanvas.getBackground()+"%n%n");
          inputInt=0;
          break;

      //exit system
        case 4:
          System.out.println("Goodbye! We hope you had fun :)");
          break;
          }
        }
      }
    }

