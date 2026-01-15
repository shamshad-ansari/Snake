# Java Snake Game

A lightweight, minimal implementation of the classic Snake game built using Java Swing.

## 🎮 Gameplay Preview

![Gameplay Demo]([path/to/your/gameplay.gif](https://github.com/shamshad-ansari/Snake/tree/main/src/snakegame/icons/Snake.gif))

<!-- To add a gameplay GIF:
1. Record your game using a screen recorder (e.g., OBS Studio, ShareX, or LICEcap)
2. Convert to GIF if needed using a tool like ezgif.com or ffmpeg
3. Add the GIF to your repository (e.g., in a folder called 'assets' or 'screenshots')
4. Update the path above to point to your GIF file
   - If in root: gameplay.gif
   - If in folder: assets/gameplay.gif
   - Or use a direct URL from GitHub: https://github.com/username/repo/blob/main/assets/gameplay.gif
-->

## 🚀 Features

* **Classic Gameplay**: Control the snake to eat apples and grow in length.
* **Collision Logic**: Game over triggers upon hitting boundaries or the snake's own body.
* **Smooth Controls**: Directional movement via arrow keys with logic to prevent illegal 180-degree turns.
* **Lightweight**: Minimalist codebase with no external heavy dependencies.

## 🛠️ Directory Structure

The code uses `ClassLoader.getSystemResource`, so ensure your folder structure looks like this:
```
src/
└── snakegame/
    ├── Board.java
    ├── SnakeGame.java (Main)
    └── icons/
        ├── apple.png
        ├── dot.png
        └── head.png
```

## 💻 How to Run

1. Clone the repository:
```bash
git clone https://github.com/your-username/java-snake-game.git
```

2. Compile the code:
```bash
javac snakegame/*.java
```

3. Run the application:
```bash
java snakegame.SnakeGame
```

## ⌨️ Controls

| Key | Action |
|-----|--------|
| Up Arrow | Move Snake Up |
| Down Arrow | Move Snake Down |
| Left Arrow | Move Snake Left |
| Right Arrow | Move Snake Right |

## 🏗️ Technical Overview

* **Logic Loop**: Driven by a `javax.swing.Timer` at a 140ms delay.
* **Rendering**: Overrides `paintComponent` for custom graphics drawing.
* **Input**: Uses an inner `TAdapter` class extending `KeyAdapter` for clean input handling.
