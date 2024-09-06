# Dynamic Multilevel Caching System

## Objective
This project implements a dynamic multilevel caching system that efficiently manages data across multiple cache levels. It supports dynamic cache level addition, eviction policies (LRU/LFU), and in-memory data storage.

## Features
- Supports multiple cache levels (L1, L2, ..., Ln).
- Eviction policies: Least Recently Used (LRU) and Least Frequently Used (LFU).
- Dynamic addition/removal of cache levels.
- Data is stored in-memory with no external dependencies.

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/DynamicMultilevelCachingSystem.git
   cd DynamicMultilevelCachingSystem
