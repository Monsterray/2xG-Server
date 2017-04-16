@echo off
title 2xG Server CMD
color 70
:start
echo 2xG Server Starting
"a:\Documents\java\jre7/bin/java.exe" -Xmx1300m -cp deps/data/CompiledFiles;deps/poi.jar;deps/mysql.jar;deps/RuneTopListV2.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; server.Server
goto start