# JATS
JATS (Java Assistant Tools) using [JQuestion](https://github.com/dinhtienloc/jquestion) to provide many various command-line utilities to rapidly develop Java Web Application

## Requirement
* JDK 8
* Maven 2.3.2

## Building
```
mvn package
```

## Running
* Create a bat file named as `jats.bat`:
```
@echo off
java -jar (path_to_jats_project)\target\jats.jar
```

* Add the directory of folder contains `jats.bat` to the PATH environment variable.
* Open `cmd`, type `jats`
![JATS's welcome banner](https://i.imgur.com/nEJJMcm.png)
