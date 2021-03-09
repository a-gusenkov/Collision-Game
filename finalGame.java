import processing.core.*;
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class finalGame extends PApplet {

//Anastasia Gusenkov Project 2

//starts the ball in the lower middle
int x=250;
int y=400;
// y value of the rectangles (always changing)
float recy=0;
//counters for array and loops
int i=0;
int k=0;
//creates array to vary width of rectangles
int []width1=new int[500];
int []width2=new int[500];
int j =0;
int point=0;
//controls speed at which rectangles move
float speed=3;
//controls what screen is shown
String s;

public void setup(){
 //The start screen is the first visible screen
  s="start";
  
  frameRate(60);
  for (i=0; i< 500;){
    //The widthProspect is used to see if the values are good enough to use.
    int width1Prospect= (int)random(10,490);
    int width2Prospect= (int)random(-490,-10);
    //sets all values for the width max at 500 to ensure a
    //long enough game.
    //Since the speed keeps on increasing its not possible to 
    //reach 500.
    if(500 - (width1Prospect + abs(width2Prospect)) == 50){
      width1[i]=width1Prospect;
      width2[i]=width2Prospect;
      i++;
    }
  }
}

public void draw(){
//perameters for the start screen
if( s=="start"){
  
  background(163, 163, 194);
  fill(240, 240, 245);
  textSize(62);
  text("EVADE",150,300);
  //used to activate game
  fill(240, 240, 245);
  noStroke();
  rect(150,355,200,50);
  fill(0);
  textSize(32);
  text("Play", 220,390);
  //used to show instructions
  fill(240, 240, 245);
  noStroke();
  rect(150,425, 200,50);
  fill(0);
  textSize(32);
  text("Instructions", 160,460);
  //circle which moves with your mouse
  fill(255, 255, 153);
  stroke(255);
  ellipse(x,y,20,20);
  fill(240, 240, 245);
  }
  //collision detection for play and instruction boxes.
  if(mousePressed&&mouseX>150&&mouseX<350&&mouseY>355&&mouseY<405){
           
           recy=-50;
           speed=3;
           point=0;
           s="play"; 
  }
   if(mousePressed&&mouseX>150&&mouseX<350&&mouseY>425&&mouseY<475){
           s="instructions"; 
  }
  //activates new screen 
  if(s== "play"){
    drawPlay();
  }
   if(s== "gameOver"){
     j=0;
    gameOver();
  }
  if(s == "instructions"){
    instruct();
  }
  

}
public void mouseMoved(){
  //keeps the circle moving with the mouse
  x=mouseX;
  y=mouseY;
  fill(255, 255, 153);
  stroke(255);
  ellipse(x,y,20,20);
}


public void drawPlay(){
  
   background(0);
   stroke(255);
   fill(255, 255, 153);
   ellipse(x,y,20,20);
   //keeps rectangles moving
   fill(0xffb3e6ff);
   rect(0,recy, width1[k], 50,7); //for one rectangle
   rect(500,recy,width2[k],50,7);
   recy+=speed;

   //when recy is out of screen gets a new rectangle to start from the top  
  if (recy > 650){
    k++;
    j=0;
    //speed control
    if( k <= 10){
    speed+=0.8f;}
    else if (k <= 14 && k >10){
     speed+=0.5f;}
    else{
    speed+=0.3f;}
    //speed control ends
    recy=-50;
  }
  //collision detection to activate game over.
  if((x > 0 && x <  width1[k] && y > recy && y < recy + 50) ||
  (x > 500+width2[k] && x < 500 && y > recy && y < recy + 50)){
       s="gameOver";
   }
 //so that only one point when circle passes ractangle
 else if(x > width1[k] && x < 500+width2[k] && y > recy && y < recy + 50){
   if(j <1){
    point++;
    j++;
   }
 }
 //point counter on top left
  fill(250);
  textSize(32);
  text("Points: " + point,10,30);

}

public void gameOver(){
  k=0;
  background(0);
  fill(255,0,0);
  textSize(60);
  text("GAME",150,200);
  text("Over",170,270);
  //shows the points you had in that game
  fill(255);
  textSize(32);
  text("Your Score : " +point, 150, 310);
  //rectangle to play again
  fill(102, 0, 0);
  rect(150,350,200,50);
  fill(255);
  textSize(32);
  text("Play Again", 170,385);
  //collision detection when presssed plays again
   if(mousePressed&&mouseX>150&&mouseX<350&&mouseY>350&&mouseY<400){
     //resets values to play again
           recy=-50;
           speed=3;
           point=0;
           s="play"; 
  }
  //rectangle to go to menu
  fill(102, 0, 0);
  rect(150,500,200,50);
  fill(255);
  textSize(32);
  text("Menu", 200,535);
  //collision detection when pressed goes to menu
   if(mousePressed&&mouseX> 150 &&mouseX<350&&mouseY>500&&mouseY<550){
     //resets values to play again
           recy=-50;
           speed=3;
           point=0;
           s="start"; 
  } 
  //circle still shows even when not moving
  fill(255, 255, 153);
  stroke(255);
  ellipse(x,y,20,20);
  fill(240, 240, 245);
}

public void instruct(){

  background(163, 163, 194);
  fill(240, 240, 245);
  textSize(42);
  text("Instructions",120,62);
  //box to go back
  fill(240, 240, 245);
  noStroke();
  rect(20,10,50,50);
  fill(0);
  textSize(16);
  text("Back", 25,40);
  //instructions
  fill(0);
  textSize(20);
  text("Evade the ball from the rectangles that come ", 30,100);
  text("down the screen by going through the gap.", 30,130);
  text("Each time the ball passes you get 1 point. ", 30,160);
  //shows example how to play game
  fill(0);
  noStroke();
  rect(150,190,200,200);
  
   fill(0xffb3e6ff);
   rect(150,250, 30 ,20,7); //for one rectangle
   rect(250,250,100,20,7);
  
     stroke(255);
   fill(255, 255, 153);
   ellipse(225,259,20,20);
   
  fill(0);
  textSize(20);
   text("Hit the blue rectangles and it Game Over!", 30,430);

  text("The speed will get faster the more points you", 30,460);
  text("have! Have Fun!", 30,490);
    //collsion detection goes back if back pressed
     if(mousePressed&&mouseX>20&&mouseX<70&&mouseY>10&&mouseY<60){
           s="start"; 
  } 
  
  fill(240, 240, 245);
  noStroke();
  rect(150,520,200,50);
  
  fill(0);
  textSize(32);
  text("Play", 220,555);
  //collision detection plays if pressed
       if(mousePressed&&mouseX>150&&mouseX<350&&mouseY>520&&mouseY<570){
         j=0;
           s="play"; 
  } 
  //keeps circle even when not moving
   stroke(255);
   fill(255, 255, 153);
   ellipse(x,y,20,20);
   
  
}
  public void settings() {  size(500,650); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "finalGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
