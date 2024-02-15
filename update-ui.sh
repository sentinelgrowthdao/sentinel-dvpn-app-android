#!/bin/bash

# Save the current directory
CURRENT_DIR=$(pwd)

# Repository URL
REPO_URL="git@github.com:sentinel-official/sentinel-dvpn-app-ui.git"

# Directory for cloning the repository
CLONE_DIR=~/Downloads/sentinel-dvpn-app-ui

# Clean clone directory
if [ -d "$CLONE_DIR" ]; then
    rm -rf $CLONE_DIR
fi

# Clone the repository
git clone "$REPO_URL" "$CLONE_DIR"

# Check if the clone operation was successful
if [ $? -ne 0 ]; then
    echo "Error: Failed to clone the repository."
    exit 1
fi

# Switch to the re-develop branch
cd "$CLONE_DIR" || exit
git checkout re-develop
cd $CURRENT_DIR

## Remove existing files in the destination directory
rm -rf ui_server/src/main/resources/*
#
## Copy contents to the destination directory
cp -r "$CLONE_DIR/build"/* ui_server/src/main/resources/

## Delete the zip file and unzip directory
rm -rf $CLONE_DIR
#
echo "UI has been updated."
