#!/bin/bash
#A script that takes an image with the attached PI Camera V2 and puts it in the root directory

DATE=$(date +"%Y-%m-%d_%H%M%S")

echo -e "Snapping a picture!"
raspistill  -w 800 -h 600 -vf -hf --timeout 1  -o /home/pi/Desktop/PiHomeSecuritySystem/webcam/$DATE.jpg
echo -e "Done! Returning back to Python Script..."
