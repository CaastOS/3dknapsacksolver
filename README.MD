<h1 align="center"> 3D Knapsack Solver</h1>
<p align="center"><img src="https://i.imgur.com/y0laulw.png"></p>

<hr><br>
<h2 align="center">Introduction</h2><br>
<p align="justify">In this project, the research on different packing problems is presented. This class of optimization problems involve attempting to pack objects together into an m-dimensional region. The main objects we’re going to cover are <a href="https://en.wikipedia.org/wiki/Pentomino">pentominoes</a>: polygons in the plane made of five adjacent, equal-sized squares. The three-dimensional packing will be addressed as a numeric version of the classical problem, called knapsack: given a set of objects j with an associated value v, determine the combination of j objects that maximises the value vtot . The problem will first be addressed with three-dimensional rectangular cuboids named parcels, and will then be adapted to work with pentominoes. We will discuss various algorithms implemented for different purposes, namely Greedy Algorithm, Genetic Algorithm and Algorithm X (Dancing Links).</p>
N.B. the pentominoes we implement as input are L, P and T. Image as reference:<br><br>
<p align="center"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Pentominos.svg/1200px-Pentominos.svg.png"></p><br>
<h2 align="center">Settings</h2><br>
<p align="justify">Each pentomino consists of 5 cubes of size 0.5m x 0.5m x 0.5m, while the parcels have the following dimensions:</p> <p align="center">A: 1.0 x 1.0 x 2.0<br>
B: 1.0 x 1.5 x 2.0<br>
C: 1.5 x 1.5 x 1.5<br></p><br>
<p>These objects have to be packed inside a container 16.5 m long, 2.5 m wide and 4.0 m high. These settings were provided by the <a href="https://www.maastrichtuniversity.nl/research/department-data-science-and-knowledge-engineering-dke"> university department</a>, and are therefore final.</p>
<br><br></p>
<h2 align="center">Greedy Algorithm</h2><br>

<p align="justify">A <a href="https://en.wikipedia.org/wiki/Greedy_algorithm">greedy algorithm</a> is an algorithm that always takes the best immediate, or local, solution while finding an answer. In most problems, this solution is not the optimal one, but it’s a decent approximation found in a very reasonable amount of time. The algorithm sort the values provided as inputs and tries to fill the container with the parcel yielding the highest value first. When there is no more space to place those parcels, it tries to place the lower value ones, until all the possible parcels are placed.</p><br>

<h2 align="center">Genetic Algorithm</h2><br>

<p align="justify">
A <a href="https://en.wikipedia.org/wiki/Genetic_algorithm">genetic algorithm</a> is a search heuristic that is inspired by Charles Darwin’s theory of natural evolution. This algorithm imitates the process of natural selection where the fittest individuals are selected for reproduction in order to produce offspring of the next generation. In this case, the chromosomes of an Individual object consist of a two-dimensional array. The first dimension contains the order of the parcels considering a DBLF (deepest bottom left fill) approach. The second dimension contains the index of the rotation of that parcel in the database. Then, the fitness of each individual is calculated by computing the total value of the parcels in its chromosome. An elitist selection approach was implemented in a way that the top 10% after sorting the previous generation by fitness gets added to the next one, and the rest of the new population is filled by making two individuals in the top 50% mate. The crossover for the mating procedure was determined by two random points.</p>
<br>
<h2 align="center">Algorithm X and Dancing Links</h2><br>

<p align="justify"><a href="https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X">Algorithm X</a> is an algorithm designed to solve exact cover problems, i.e. filling an m-dimensional space entirely. The exact cover problem is represented in Algorithm X by a matrix consisting of 0s and 1s. The goal is to select a subset of the rows such that the digit 1 appears in each column only once. In order to implement this in practice, we used <a href="https://en.wikipedia.org/wiki/Dancing_Links">Dancing Links</a>, a technique made specifically for this problem and used to implement backtracking algorithms. The technique is based on the observation that an element x can be removed from a doubly-linked list where L[x] points to the predecessor and R[x] points to the successor in this simple way:
LR[x] ← L[x], RL[x] ← R[x],
but that element can be linked back by performing this subsequent operation:
LR[x] ← x, RL [ x ] ← x.
This might seem pointless, but the idea of backtracking lies on the possibility of removing what has just been added. In order to use this to solve an exact cover, Dancing Links represent the 1s as data objects. Each data object has the fields left, right, up and down. Any link that has no corresponding 1 in a suitable cell will link to itself instead. The last field is a link to a column object, a special data object that has an additional field, size. The column size is the number of data objects that are currently linked together from the column object, as shown in the following diagram:<br><br>
<p align="center"><img src="https://i.imgur.com/Y4gr2Sr.png"></p>
</p>
<br>
<p align="justify">The original version of Algorithm X is not necessarily a good solution to knapsack problems, since it’s not necessarily true that an exact cover yields the highest complessive value. In order to have a more suitable solution, we modified the algorithm such that, before every backtracking, it computes the total score of the current solution, and if that is higher than the previous one, it gets displayed as a possible solution.
</p><br>

<h2 align="center">Additional documentation</h2><br>
<p> Additional information about the details of the methods and the algorithms can be found in this <a href="https://drive.google.com/file/d/1dB_zij7-wUsGPzL6XhLANyoUpOeKkmYV/view">paper</a>. The code is provided with JavaDoc support, so additional documentation can be found by running the following command:</p><br>

```bash
javadoc PACKAGE|SOURCE_FILE OPTIONS @ARGFILES

#`javadoc` is the command which will generate java source code documentation.
#`PACKAGE|SOURCE_FILE` is the package or source file name in which documentation will be generated.
#`OPTIONS` enables different behavior of javadoc
#`@ARGFILES` are used to provide arguments to the javadoc command.
```
<br>
<h2 align="center">How to run the code</h2><br>
<p> In order to run the code, your machine has to have JavaFX correctly installed and set in the enviroment. In order to do that, please refer to <a href="https://openjfx.io/openjfx-docs/">JavaFX documentation</a>. After that, the steps consists of:<br><br>

```bash
$ git clone https://github.com/CaastOS/3dknapsacksolver # clone the repo
$ cd 3dknapsacksolver # change directory to the repo
$ javac Main.java # compile Main.java
$ java Main # run Main.class
```
<h2 align="center">Credits</h2><br>
<p align="justify">This repository is a sub-part of a bachelor semestral project at <a href="https://www.maastrichtuniversity.nl/research/department-data-science-and-knowledge-engineering-dke">Maastricht University, Department of Data Science and Knowledge Engineering</a>. All the references and the members componing the group are stated in the previously mentioned paper. To have further information about the project, please contact me in private through the links in the main page of my profile.</p>