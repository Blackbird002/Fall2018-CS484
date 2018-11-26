#!/usr/bin/python3
import RPi.GPIO as GPIO
import time
import os
import subprocess
import socket

GPIO.cleanup()
################################################################################
# GPIO Setup
################################################################################
buzzerPinOut = 6
pirPinIn = 4
GPIO.setmode(GPIO.BCM)
GPIO.setup(pirPinIn, GPIO.IN) #PIR
GPIO.setup(buzzerPinOut, GPIO.OUT) #Buzzer
GPIO.setwarnings(False)

messageDetect = "Motion Detected"

################################################################################
# Function sendTCPMessage
################################################################################
def sendTCPMessage():
    # create TCP/IP socket
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # retrieve local hostname
    local_hostname = socket.gethostname()

    # get fully qualified hostname
    local_fqdn = socket.getfqdn()

    # get the according IP address
    ip_address = "192.168.1.4"

    # port number
    port = 23456
    
    # bind the socket to the port 23456, and connect
    server_address = (ip_address, port)  
    sock.connect(server_address)
    print ("connecting to %s (%s) with %s" % (local_hostname, local_fqdn, ip_address))

    # define example data to be sent to the server
    TCPmessage = "Motion Detected!"
    print ("data:",TCPmessage)
    new_data = str(TCPmessage).encode("utf-8")
    sock.sendall(new_data)

    # wait for two seconds
    time.sleep(1)

    # close connection
    sock.close()  
################################################################################
# Function countdonw
################################################################################
def countdown(t):
    while t:
        mins, secs = divmod(t, 60)
        timeformat = '{:02d}:{:02d}'.format(mins, secs)
        print(timeformat, end='\r')
        time.sleep(1)
        t -= 1

################################################################################
# The main function
################################################################################
def main():
    os.system("clear")
    counter = 0
    print ("Warming up the Sensor! - 30 seconds")
    countdown(5)
    while True:
        if (GPIO.input(pirPinIn) == 1):
            counter += 1
            print("Motion Detected...", counter)
                
            GPIO.output(buzzerPinOut, True)

            subprocess.call(['./SnapImagePiv2.sh'])
            time.sleep(1)
            sendTCPMessage()
            time.sleep(5)
                
            GPIO.output(buzzerPinOut, False)
        else:
            print("No Motion Detected...", end='\r')

#Run
if __name__ == '__main__':
    main()

