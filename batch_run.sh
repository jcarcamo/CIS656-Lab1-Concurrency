#!/bin/bash
# Tested using bash version 4.1.5
runs=$1
threads=$2
for ((i=1;i<=$runs;i++)); 
do 
  java -jar target/lab1-0.0.1-SNAPSHOT.jar $threads
done
