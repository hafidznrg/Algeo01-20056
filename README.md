# First Major Assignment IF2123 Geometric and Linear Algebra

## Description
This program is developed to to fulfill the First Major Assignment of IF2123 Linear Algebra and Geometry course. Here we use Java programming languages to develop this program. This program is intended to:
1. Calculate solution of System of Linear Equations using Gauss Elimination, Gauss-Jordan Elimination, Inverse Matrix, and Crammer rule.
2. Calculate matrix's determinant using Gauss Elimination and Cofactor Expansion.
3. Calculate inverse matrix.
4. Solve polynomial interpolation problem and multiple linear regression.

This program contain several directories:
1. `bin` directory contains binary codes or `*.class` files
2. `docs` directory contains documentation and report of this assignment.
3. `lib` directory contains `jar` file (library of this program).
4. `src` directory contains `java` files or source codes of this program.
5. `test` directory contains test case files for this program.

## How to Run
### Install dependencies
Here are some things that need to be downloaded and install first:
- [Java](https://www.java.com/en/download/)
- [Java Development Kit](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### Execute program
1. Clone this repository
    ```sh
    git clone https://github.com/hafidznrg/Algeo01-20056.git
    ```
2. There are several ways to run this program:
    - Using command line
        ```bash
        cd src
        javac -d ../bin ./*.java
        cd ../bin
        java Main
        ```
    - Using `run.bat` file, you can either use `run` command in root folder or open the file with double click
    - Using `jar` file
        ```bash
        cd lib
        java --enable-preview -jar Algeo01-20056.jar
        ```
    
> Please note that when read and write file, you only need to specify file name without the directory. Default directory is on test folder. if you want to make new file for test case, kindly make it on test folder. Also don't use Java Process Console if you want to read and write file.

## Maintainer
This program was developed and is maintained by
1. Fikri Khoiron Fadhila             (13520056)
2. Malik Akbar Hashemi Rafsanjani    (13520105)
3. Hafidz Nur Rahman Ghozali         (13520117)