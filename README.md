# OOP Assignment - Arkanoid Game

A Java implementation of the classic Arkanoid/Breakout game, demonstrating core OOP principles including inheritance, polymorphism, and design patterns.

## 📋 Project Overview

This project is an Arkanoid-style game built from scratch using Object-Oriented Programming concepts. It features collision detection, event-driven architecture with listeners, and a complete game loop with sprite management.

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA (recommended) or any Java IDE
- biuoop-1.4.jar library (included in `libs/`)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/edanBarad/OOP_Assignment.git
   ```
2. Open the project in IntelliJ IDEA
3. Build the project and resolve any dependencies

### Running the Project
Run `Main.java` to start the game. Use the arrow keys to control the paddle and break all the blocks!

**Controls:**
- ← → Arrow keys to move the paddle
- Break all blocks to win
- Don't let all balls fall off the screen!

## 📁 Project Structure

```
OOP_Assignment/
├── src/
│   ├── Main.java          # Entry point - run this to start the game
│   ├── Game_Setup/        # Game management and core systems
│   │   ├── Game.java                    # Main game class (Singleton)
│   │   ├── GameEnvironment.java         # Collision detection system
│   │   ├── SpriteCollection.java        # Manages all drawable objects
│   │   ├── Counter.java                 # Score/block/ball counters
│   │   ├── BlockRemover.java            # Hit listener for blocks
│   │   ├── BallRemover.java             # Hit listener for balls
│   │   ├── ScoreTrackingListener.java   # Tracks score
│   │   └── ScoreIndicator.java          # Displays score on screen
│   ├── Geometry/          # Game objects and geometry
│   │   ├── Ball.java                    # Bouncing ball with physics
│   │   ├── Block.java                   # Breakable blocks
│   │   ├── Paddle.java                  # Player-controlled paddle
│   │   ├── Point.java                   # 2D point
│   │   ├── Line.java                    # Line with intersection logic
│   │   ├── Rectangle.java               # Rectangle collision shape
│   │   ├── Velocity.java                # Movement vector
│   │   ├── CollisionInfo.java           # Collision data
│   │   ├── Colors.java                  # Color enumeration
│   │   └── GeometryTester.java          # Unit tests for geometry classes
│   └── Interfaces/        # Core interfaces
│       ├── Sprite.java                  # Drawable game objects
│       ├── Collidable.java              # Objects that can collide
│       ├── HitListener.java             # Observer pattern for hits
│       └── HitNotifier.java             # Notifies listeners of hits
├── libs/              # External libraries (biuoop GUI)
└── .idea/             # IntelliJ IDEA configuration
```

## 🎮 Features

- **Physics-based ball movement** with accurate collision detection
- **Event-driven architecture** using the Observer pattern (HitListener)
- **Smart paddle mechanics** with 5-region hit zones for ball angle control
- **Score tracking system** with visual indicator
- **Win/Lose conditions** based on blocks cleared and balls remaining
- **Singleton Game class** for centralized game state management

## 🏗️ Design Patterns Used

- **Singleton:** Game class for single instance management
- **Observer:** HitListener/HitNotifier for event handling
- **Collection Management:** SpriteCollection and GameEnvironment for organized object handling

## ⚠️ Status

**Note:** This project is a work in progress. Additional features and components will be added in future updates.

## 📝 License

This project is an academic assignment.

## 👤 Author

**Edan Barad**
- GitHub: [@edanBarad](https://github.com/edanBarad)
