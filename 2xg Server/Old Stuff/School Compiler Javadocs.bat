@echo off
Title 2xG Client Compiler
echo Compileing Javadocs...
"H:\Documents\java\jdk1.6.0_35\bin\javadoc.exe" -classpath deps/log4j-1.2.15.jar;deps/jython.jar;deps/xstream.jar;deps/mina.jar;deps/mysql.jar;deps/RuneTopListV2.jar;deps/poi.jar;deps/slf4j.jar;deps/slf4j-nop.jar -d deps/Data/Javadocs source\server\model\items\*.java source\server\model\minigames\*.java source\server\model\npcs\*.java source\server\model\objects\*.java source\server\model\players\*.java source\server\model\players\skills\*.java source\server\model\players\packets\*.java source\server\model\shops\*.java source\server\net\*.java source\server\task\*.java source\server\util\*.java source\server\world\*.java source\server\util\log\*.java source\server\*.java source\server\clip\*.java source\server\world\map\*.java source\server\clip\region\*.java source\server\model\players\Content\*.java
echo ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
echo Thank you for waiting!
Echo Compileing Complete!
pause
