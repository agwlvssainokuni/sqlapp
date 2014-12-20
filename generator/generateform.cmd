@echo off
setlocal
set curdir=%~dp0

:repeat

echo %1
set /a count=%count%+1
set props=%props% -PdefinitionFile.%count%=%1

if "%~2"=="" goto end
shift
goto repeat
:end

call gradle generateForm %props%

pause

@echo off
