# 🍕 PizzeriaSimulator2019 🍕

Collin Barlage

Java Swing
CS 338 - Winter 2019

## Art

*Customers, and pizzeria shop art assets were done by [Brianna Maule](https://www.instagram.com/briannamauleartist/)*
Pizza art was done by Collin Barlage.

## Compilation Basics

The `main()` method can be found the in the `App` class, in which it invokes a new Appp using `java.lang`'s Runnable class.

However, a jar file `PizzeriaSimulator2019.jar` has been included for your convenience.

## Modules

`javax.swing` was used to implement menus and overall game window, while `java.awt.Graphics` and c were used to implement displaying many images on a canvas.

## Implementation

### Concurrency

This Java Swing app is multi threaded, having one thread for input and event handeling, and annother thread for drawing images.
This was accomplished by having a global variable of type `Thread` class called `gameThread` which we use for the game loop. Here is how the game itself starts:
```java
gameThread = new Thread(loop);
gameThread.start();
``` 

### Real-time Graphics

To draw multiple objects in the draw loop, I implemented an abstract class `GameObject` which has a Vector of `Sprite`s. Sprites are a wrapper for `java.awt.Graphics` and can be drawn easily. All images are kept in a folder `/images` and *must* be present for the game to run properly 

### Input Manager

Because this is an interactive game, an input manager was needed to capture click events. To do this, I implemented the class `InputManager` which implements the `java.awt` class `MouseListener`. Then, in all `GameObjects` and abstract method `action` is called if the object was clicked:
```java
public void mousePressed(MouseEvent event) {
    System.out.println("mousePressed at: "+ event.getX() + ", "+ event.getY());
    loop.click(event.getX(), event.getY());

}
```
