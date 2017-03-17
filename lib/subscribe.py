#!/usr/bin/python

import paho.mqtt.client as mqtt
import os

USER = os.environ['MQTT_USER']
PASS = os.environ['MQTT_PASSWORD']
HOST = os.environ['MQTT_HOST']
PORT = int(os.environ['MQTT_PORT'])

def on_message(mosq, obj, msg):
    Queue.distance = float(msg.payload.decode())

class Queue:
    distance = 40

    def start(self):
        mqttc = mqtt.Client()
        mqttc.username_pw_set(USER, PASS)
        mqttc.connect(HOST, PORT)

        mqttc.on_message = on_message
        mqttc.subscribe("frajola/distance", 0)
        mqttc.loop_start()
