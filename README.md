# Lab Exercise 1 – Concurrency
## CIS 656 – Fall 2017

## Objective
The objective of this project is to give the student hands-on experience with the notion of concurrency in programming.  In particular we will look at the consequences of not properly synchronizing the updates of shared data, as well as the costs associated with synchronizing.

## Problem definition
Write a Java program (Program A) that spawns n threads, where n is a program argument. These threads access a shared counter (initialized as 0) in a loop. In each iteration, each thread reads the shared counter to a local (stack) variable, increments it, and stores it back to the shared counter. When all threads complete 1 million iterations each, the program stops and prints the value of the shared counter and the total elapsed time.  Do NOT use any kind of thread synchronization in this program. 

Next, create a new version of the same program (Program B) that uses one of Java’s synchronization primitives to ensure that only one thread updates the shared counter at a time.  You can use any Java mechanisms you want for this (e.g., implicit monitor/synchronize, ReentrantLock, etc.).

## How to run
Please ensure that _maven_ is installed in your system by running ```mvn -v```.
After that, sitting on the folder where you got this project just run:

```bash
$ mvn package
$ java -jar target/lab1-0.0.1-SNAPSHOT.jar <# of threads>
```

For example:
```
$ java -jar target/lab1-0.0.1-SNAPSHOT.jar 2
Program A:
Number of threads: 2
Execution Time: 10 milliseconds
Counter Value: 1061199
********************************


Program B:
Number of threads: 2
Execution Time: 66 milliseconds
Counter Value: 1300688
```