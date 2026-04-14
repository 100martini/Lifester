# Lifester 👽

> *"Monsters of life. Monsters are real. They live inside of us — and sometimes they win."*
>
> **Fight your monsters.**

---

## What is this?

It's a Star Wars-themed space shooter 2D game where the monsters aren't aliens from outer space —  
they're the ones from *inside* you. Depression, fear, doubt, your 3 AM thoughts, that email you still haven't replied to.

You shoot them. With a spaceship. And it's oddly therapeutic.

---

## The Lore

```
L I F E S T E R
life · ster
/ˈlʌɪfstə/

(noun) A person locked in permanent combat with the monsters of their own life.
See also: you, right now, reading this README instead of sleeping.
```

---

## Gameplay

The game has **3 stages**, each one harder than your last life decision:

| Stage | Vibe | What you're fighting |
|-------|------|----------------------|
| 1 | Chill intro | Baby aliens — they're weak, you got this |
| 2 | It escalates | More aliens, they start moving in patterns, balls are limited |
| 3 | Boss fight | Two big monsters with health bars. No more running. Face them. |

**Controls:**

| Key | Action |
|-----|--------|
| Arrow keys | Move hero |
| `W` | Rotate weapon clockwise |
| `X` | Rotate weapon counter-clockwise |
| `SPACE` | Shoot 💥 |

---

## Requirements

- Java 8+
- JavaFX (bundled with Java 8, needs separate setup on Java 11+)
- Eclipse (or any IDE that supports JavaFX projects)

---

## How to Run

1. Clone the repo
2. Open in Eclipse as an existing Java project
3. Make sure your build path includes the JavaFX library
4. Run `Main.java`
5. Click the button on the landing screen
6. Fight your monsters

---

## Project Structure

```
src/application/
├── Main.java              ← Game loop, all 3 stages, entry point
├── GraphicObject.java     ← Base class for everything on screen
├── Hero.java              ← You
├── Monster.java           ← Them
├── Ball.java              ← Your weapon projectiles
├── Arme.java              ← The weapon itself
├── Blood.java             ← Health bar logic
├── Danger.java            ← Falling obstacles in stage 3
├── Zone.java              ← Boundary zones for spawning
├── Style.css              ← The Star Wars aesthetic
├── Pictures/              ← Sprites & backgrounds
└── Sounds/                ← Shot sound & background music
```

---

## Known quirks

- The method names in `Blood.java` are in Darija (Moroccan Arabic). `ba9i()` means "still alive?", `n9es()` means "decrease". They stay. This is a feature, not a bug. 🇲🇦
- The background is Star Wars. The monsters are aliens. The music is dramatic. You're welcome.

---

*Built with JavaFX, sleep deprivation, and the firm belief that you can beat whatever's chasing you.*
