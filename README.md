# Closest Pair of Points

Demo:

[![Closest Pair](https://img.youtube.com/vi/xchUv8C7Unw/0.jpg)](https://www.youtube.com/watch?v=xchUv8C7Unw)

This Java program finds the closest pair of points from a set of randomly generated points using both brute force and divide-and-conquer algorithms. The program uses the `DUDraw` library for drawing the points and visualizing the closest pairs.

## Features

- **Brute Force Algorithm**: Finds the closest pair of points by checking all possible pairs.
- **Divide and Conquer Algorithm**: Finds the closest pair of points using a more efficient divide-and-conquer approach.

## Usage

### Generate Points and Find Closest Pair

1. The program generates 10 random points within a 100x100 grid.
2. It finds the closest pair of points using the brute force algorithm and highlights them in red.
3. It then finds the closest pair of points using the divide-and-conquer algorithm and highlights them in blue.

## Code Overview

### Main Class: `ClosestPair`

- **Main Method**: Generates random points, finds the closest pairs using both algorithms, and visualizes the results.
- **closestPairBrute Method**: Finds the closest pair of points using the brute force algorithm.
- **distance Method**: Calculates the distance between two points.
- **closestPairDiv Method**: Finds the closest pair of points using the divide-and-conquer algorithm.
- **closestPair Method**: Helper method for the divide-and-conquer algorithm.

### Dependencies

- **DUDraw**: Library for drawing and handling user interactions.
