# 🌑 UnderAbyss

![Development Status](https://img.shields.io/badge/Status-In%20Development-yellow.svg)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-green.svg)
![NeoForge](https://img.shields.io/badge/NeoForge-Latest-orange.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

**"In the depths, every breath is a luxury you can't always afford."**

UnderAbyss is a hardcore survival mod for Minecraft **1.21.11 (Mounts of Mayhem)** that transforms exploration into a claustrophobic race for oxygen. Never venture into the darkness unprepared again.

---

## 🌌 Overview

The world beneath the surface has become lethal. As soon as you descend below **Y=0**, or step through the portals of the **Nether** and **The End**, the atmosphere turns toxic. Your survival depends entirely on your ability to filter the air you breathe.

### 🤿 Core Mechanics
* **Filtration System:** Upgrade any helmet (Iron, Diamond, Netherite) with specialized air filters using dynamic crafting recipes.
* **Oxygen Management:** Monitor your filter's durability. Once empty, suffocation effects kick in immediately:
    * 🌫️ **Vision Blur:** Particle effects and visual distortion.
    * ⛏️ **Mining Fatigue:** Heavy breathing makes mining almost impossible without a functional filter.
    * 🔥 **Internal Combustion:** Gradual damage and fire overlays to simulate extreme distress.
* **Environmental Tiers:** From "Basic" filters for deep caves to "Void" filters for the End, each biome requires specific gear.

---

## 🛠️ Roadmap & Features

- [x] **Data Component Integration:** Immutable and high-performance filter data management.
- [x] **Dynamic Crafting:** Apply filters to any helmet while preserving existing enchantments and durability.
- [ ] **Atmospheric Tiers:** Specific danger logic for the Nether (Heat) and The End (Void).
- [ ] **End-game Enchantments:** "Eternal Breath" and "Abyssal Sealing".
- [ ] **Corrupted Mounts:** 1.21.11-specific mobs capable of damaging your equipment's filtration units.

---

## 🚧 Development Status

UnderAbyss is currently in **Active Development**. The core survival framework is being implemented and polished.

### Current Focus:
* Finalizing the `SurvivalHandler` for tick-based oxygen consumption.
* Implementing cinematic visual effects (screen shakes, red overlays).
* Expanding the `FilterRecord` system for tiered progression.

---

## 💻 For Developers

If you want to contribute or explore the source code:

### Requirements
* **Java Development Kit (JDK) 21**
* **NeoForge Mappings** (latest for 1.21.11)

### Quick Start
1. Clone the repository: `git clone https://github.com/yukow0/underabyss.git`
2. Import the project into your IDE via `build.gradle`.
3. Run the `runClient` configuration to test the current build.

---

## 🔗 Links

* **Source Code:** [github.com/yukow0/underabyss](https://github.com/yukow0/underabyss)
* **Bug Reports:** [Issue Tracker](https://github.com/yukow0/underabyss/issues)


---

## 📄 License

This project is licensed under the **MIT License**. You are free to use, modify, and distribute the code as long as original credit is provided.