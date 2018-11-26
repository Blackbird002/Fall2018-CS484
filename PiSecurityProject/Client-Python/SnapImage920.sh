#!/bin/bash
#A script that takes an image with the attached USB webcam and puts it in the root directory

DATE=$(date +"%Y-%m-%d_%H%M")

echo -e "Snapping a picture!"
fswebcam -r 640x480 /home/pi/Desktop/PiHomeSecuritySystem/webcam/$DATE.jpg
echo -e "Done! Returning back to Python Script..."
