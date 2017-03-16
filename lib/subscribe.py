#!/usr/bin/python

import paho.mqtt.client as mqtt

USER = 'fsbbzncn'
PASS = 'Zf12-88wh85n'
HOST = 'm10.cloudmqtt.com'
PORT = 13832

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
