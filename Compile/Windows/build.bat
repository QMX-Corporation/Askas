@echo off
rem MIT License.
rem Copyright (C) QMX Corporation (all authors, maintainers and collaborators),
rem Licensed and Released under MIT License.

echo [BUILD] Starting Askas Terminal compilation for Windows...

rem 1. Define base directories (relative to Compile/Windows)
set "BUILD_DIR=..\..\build"
set "CLASSES_DIR=%BUILD_DIR%\classes"
set "LIBS_DIR=%BUILD_DIR%\libs"
set "SRC_DIR=..\..\src"

rem 2. Clean and recreate build directory structure
echo [BUILD] Cleaning previous build artifacts...
if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%CLASSES_DIR%"
mkdir "%LIBS_DIR%"

rem 3. Compile Java source files
echo [BUILD] Compiling Java source files...
javac -d "%CLASSES_DIR%" "%SRC_DIR%\TerminalPrintOfView\*.java" "%SRC_DIR%\TerminalPrintOfView\bootstrap\*.java"

if %errorlevel% neq 0 (
    echo [BUILD ERROR] Compilation failed!
    exit /b %errorlevel%
)

rem 4. Build executable JAR file
echo [BUILD] Packaging into executable JAR file...
jar cfe "%LIBS_DIR%\Askas.jar" TerminalPrintOfView.TerminalMain -C "%CLASSES_DIR%" .

if %errorlevel% neq 0 (
    echo [BUILD ERROR] Packaging failed!
    exit /b %errorlevel%
)

echo [BUILD] Compilation successful!
echo [BUILD] Executable generated at: build\libs\Askas.jar
