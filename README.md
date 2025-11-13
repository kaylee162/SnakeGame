**🐍 Snake Game (Java Swing)**
===========


A simple Snake game built in Java using Swing.
This version includes a clean UI, smooth movement, and a persistent high score system that saves your best score between sessions.

**🎮 Features**
===========

Classic Snake gameplay  
Smooth movement and keyboard controls  
Randomly generated food  
Score counter that updates in real time  
Persistent High Score saved to highscore.txt  
Game Over screen  
Simple and clean UI  
Lightweight — no external libraries required  

**🕹️ Controls**  
===========
Key	Action  
↑	Move Up  
↓	Move Down  
←	Move Left  
→	Move Right  

The game starts immediately when the window opens.  

**🚀 How to Run**  
===========

**Option 1: IntelliJ IDEA (recommended)**  
Open IntelliJ  
Click Open and select the project folder  
Open src/SnakeGame.java  
Press Run ▶️  
The game window will appear  

**Option 2: Command Line**  
Navigate into the src folder:  
```bash
cd Snake/src
```
Compile:  
```bash
javac SnakeGame.java
```
Run:  
```bash
java SnakeGame
```

**📁 Project Structure**
===========

```bash
Snake/
 ├── src/
 │    └── SnakeGame.java
 ├── highscore.txt        (auto-generated)
 ├── .gitignore
 ├── Snake.iml
 └── README.md
```
**📝 High Score Saving**
===========
The game automatically saves your best score to:  
highscore.txt  

If the file is missing, it will be created when you first beat your previous score.  

**💡 Future Improvements (Ideas)**  
===========
Add a restart key (press R to play again)  
Main menu UI  
Difficulty levels (speed changes)  
Snake skins or color themes  
Sound effects  
Animated movement  
Grid lines or background textures  
