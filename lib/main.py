#!/usr/bin/python

from subscribe import Queue
import RPi.GPIO as GPIO
import time
import http.client

ECHO = 29
TRIG = 31

def take_picture():
    conn = http.client.HTTPConnection("localhost", 3000)
    conn.request("POST", "/take-picture")
    r1 = conn.getresponse()
    print(r1.status)

def setup_sensor():
    GPIO.setmode(GPIO.BOARD)
    GPIO.setwarnings(False)

    GPIO.setup(ECHO, GPIO.IN)
    GPIO.setup(TRIG, GPIO.OUT)

def roda_medicao():
    global distancia_cm
    distancia_cm = 0
    while True:
        time.sleep(2)
        GPIO.output(TRIG, GPIO.HIGH)
        time.sleep (0.000010)
        GPIO.output(TRIG, GPIO.LOW)
        while GPIO.input(ECHO) == 0:
            pulso_inicial = time.time()
        while GPIO.input(ECHO) == 1:
            pulso_final = time.time()
        duracao_pulso = pulso_final - pulso_inicial
        distancia_cm =  34300 * (duracao_pulso/2)
        distancia_cm = round(distancia_cm, 0)
        print(distancia_cm, 'cm   ',end="\r")

        if distancia_cm < Queue.distance:
            print('disparando foto')
            take_picture()
            time.sleep(1)
            print('foto enviada')

queue = Queue()
queue.start()

setup_sensor()
roda_medicao()
