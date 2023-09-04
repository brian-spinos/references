#!/bin/bash

# Simulate download progress
simulate_download() {
    for ((i = 0; i <= 100; i += 5)); do
        echo -n "Downloading: ["
        for ((j = 0; j < i; j += 5)); do
            echo -n "#"
        done
        for ((k = i; k < 100; k += 5)); do
            echo -n " "
        done
        echo -ne "] $i%\r"
        sleep 0.1  # Simulate download speed
    done
    echo -e "\nDownload complete!"
}

# Start the download simulation
simulate_download
