# Module 1: Task Parallelism

## Overview
This module covers the fundamentals of task parallelism in Java, including:
- Task creation and termination
- Fork/Join framework
- Computation graphs, work, span, and parallel speedup
- Amdahl’s Law

## Mini Project 1: Reciprocal Array Sum
**Goal:** Implement a parallel version of the reciprocal array sum using Java’s Fork/Join framework.

## Folder Structure
- `src/main/java/edu/rice/parallel/module1_task/ReciprocalArraySum.java` – Main implementation
- `src/test/java/edu/rice/parallel/module1_task/ReciprocalArraySumTest.java` – Unit tests

## How to Run
```bash
./gradlew :test
```

## Key Learnings
- How to use Java’s Fork/Join framework for task parallelism
- Measuring speedup and understanding parallel performance

## References
- [Rice University Parallel Programming in Java](https://www.coursera.org/learn/parallel-programming-in-java)
