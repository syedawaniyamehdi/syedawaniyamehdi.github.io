'''
Project 3 by Waniya Mehdi & Prashant Baniya

Use the arrow to shoot the apples. Move Newton using the mouse .
If the arrow catches enough apples before they escape, you win.

In this checkpoint,Newton moves around with the mouse.
The arrow does not move and the apples keep falling from the sky causing the points to do in deficit.

Asistance : The tutors helped us with the function to make the newton move using the mouse pad
'''
from graphics2 import *
import time
import random
import math

APPLE_SPEED = 25
NEWTON_SPEED = 25
NUM_LOSE = 15
NUM_WIN = 25
STALL_TIME = 0.05
THRESHOLD = 50

def distanceBetweenPoints(point1, point2):
    '''
    Calculates the distance between two points
    
    Params:
    point1 (Point): the first point
    point2 (Point): the second point
    
    Returns:
    the distance between the two points
    '''
    p1x = point1.getX()
    p1y = point1.getY()
    p2x = point2.getX()
    p2y = point2.getY()
    return math.sqrt((p1x - p2x)*(p1x - p2x) + (p1y - p2y) * (p1y - p2y))


def isCloseEnough(NewtonImg, appleImg):
    centerNewton = NewtonImg.getAnchor()
    centerApple = appleImg.getAnchor()
    
    if distanceBetweenPoints(centerNewton, centerApple) < THRESHOLD:
        return True
    else:
        return False

def moveApples(appleImgList):
    for apple in appleImgList:
        apple.move(0, APPLE_SPEED)

def shootArrows(arrowImage):
    arrow = Image(Point(333, 333), "ARROW.gif")
    arrow.draw(window)
    


def moveNewton(window, NewtonImg):
   
    
    mouse = window.checkMousePointer()
    

    newtonCenter = NewtonImg.getCenter()
    newtonHeight = NewtonImg.getHeight()
    newtonWidth = NewtonImg.getWidth()
    
    if mouse.getX() > newtonCenter.getX() and mouse.getY() > newtonCenter.getY() - (newtonHeight/2) :
        if mouse.getX() >= (newtonCenter.getX() + newtonWidth/2):
            NewtonImg.move(NEWTON_SPEED, 0)
        
    elif mouse.getX() < newtonCenter.getX() and mouse.getY() > newtonCenter.getY() - (newtonHeight/2):
        if mouse.getX() <= (newtonCenter.getX() - newtonWidth/2):
            NewtonImg.move(-NEWTON_SPEED, 0)
            
        
    time.sleep(STALL_TIME)
    
def addappleToWindow(window):
    xLocation = random.randrange(40, 620)
    startPoint = Point(xLocation, 0)
    appleImage = Image(startPoint, 'apple.gif')
    appleImage.draw(window)
    return appleImage

def gameLoop(window, Newton):
    '''
    Loop continues to allow the apples to fall and Newton to move
    until enough apples escape or the arrow catches enough apples to
    end the game.

    window (GraphWin): the window where game play takes place
    Newton (Image): Newton image
    '''
    appleList = []
    missed = 0
    totalMissed = 0
    caught = 0
    scoreLabel = Text(Point(600,50),'0')
    scoreLabel.setSize(16)
    scoreLabel.draw(window)
    while totalMissed < NUM_LOSE and caught < NUM_WIN:
        moveNewton(window, Newton)
        if random.randrange(100) < 6:  #only 6% of the time apple are being drawn
            apple = addappleToWindow(window)
            appleList.append(apple)
            
        moveApples(appleList)
        
        for apple in appleList:
            if apple.getAnchor().getY()> 700:
                appleList.remove(apple)
                missed = missed + 1
                totalMissed = totalMissed + 1
            
#             if isCloseEnough(Newton, apple):
#                     appleList.remove(apple)
#                     apple.undraw()
#                     caught = (caught + 1)
            if missed > 0:
                caught = caught - 1 
                missed = missed - 1
            scoreLabel.setText(str(caught))
#             else:
#                 scoreLabel.setText(str(caught))
                    
             
                    
            
              
        
        time.sleep(STALL_TIME)

def main():
# setup the game 
    window = GraphWin("GRAVITY!!!", 666,666)
    window.setBackground("white")
    directions = Text(Point(333, 650), 'Use the mouse to move Newton.')
    directions.setSize(16)
    directions.draw(window)
    arrow = Image(Point(333, 333), "ARROW.gif")
    arrow.draw(window)

    Newton = Image(Point(333,580), "newton.gif")
    Newton.draw(window)

    gameLoop(window, Newton)


main()
    
