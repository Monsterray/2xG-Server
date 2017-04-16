@echo off
title Local Compiler
:start
cls
echo Compileing...
"C:\Program Files\Java\jdk1.7.0_07\bin\javac.exe" -d ./ -s ./ MainFrame.java SearchFrame.java
echo ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
echo Compileing Complete!!!
pause
goto start