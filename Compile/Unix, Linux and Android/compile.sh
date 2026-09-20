#!/bin/sh

# MIT License.
# Copyright (C) QMX Corporation (all authors, maintainers and collaborators),
# Licensed and Released under MIT License.

# Exit immediately if a command exits with a non-zero status
set -e

echo "[BUILD] Starting Askas Terminal compilation..."

# 1. Define base directories
BUILD_DIR="../build"
CLASSES_DIR="${BUILD_DIR}/classes"
LIBS_DIR="${BUILD_DIR}/libs"
SRC_DIR="../src"

# 2. Clean and recreate build directory structure
echo "[BUILD] Cleaning previous build artifacts..."
rm -rf "${BUILD_DIR}"
mkdir -p "${CLASSES_DIR}"
mkdir -p "${LIBS_DIR}"

# 3. Compile Java source files
echo "[BUILD] Compiling Java source files..."
javac -d "${CLASSES_DIR}" \
  "${SRC_DIR}/TerminalPrintOfView"/*.java \
  "${SRC_DIR}/TerminalPrintOfView/bootstrap"/*.java

# 4. Build executable JAR file
echo "[BUILD] Packaging into executable JAR file..."
jar cfe "${LIBS_DIR}/Askas.jar" TerminalPrintOfView.TerminalMain -C "${CLASSES_DIR}" .

echo "[BUILD] Compilation successful!"
echo "[BUILD] Executable generated at: build/libs/Askas.jar"
