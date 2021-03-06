# frajola
Take pictures with a DSLR camera with USB cable and send it to the twitter.
Using raspberry-pi with distance sensor to shoot the camera

## Installation
install libusb
```
sudo apt-get install libusb
```

install gphoto2
```
sudo apt-get install gphoto2
```

```
sudo rm /usr/share/dbus-1/services/org.gtk.Private.GPhoto2VolumeMonitor.service
sudo rm /usr/share/gvfs/mounts/gphoto2.mount
sudo rm /usr/share/gvfs/remote-volume-monitors/gphoto2.monitor
sudo rm /usr/lib/gvfs/gvfs-gphoto2-volume-monitor
```
* Restart the camera and call auto-detect
```
gphoto2 –auto-detect
```

take a picture
```
gphoto2 --image-capture-and-download
```

mqtt-client
```
pip3 install paho-mqtt
```
## Build

```
  $ lein ring uberjar
```

## Usage
Run
```
  $ lein ring server-headless
```

* take a picture and send to twitter
```
   $ java -jar frajola-0.1.0-standalone.jar &
   $ curl -i http://localhost:3000/take-picture
```

* Use distance sensor to shoot a photo
```
   $ python3 lib/main.py
```

## Configs
export environment variables to jar file
```
export TWITTER_CONSUMER_KEY=
export TWITTER_CONSUMER_SECRET=
export TWITTER_TOKEN=
export TWITTER_TOKEN_SECRET=
export PICTURE_PATH=/tmp/
```

* Distance sensor
Distance is changed through mqtt.
```
export MQTT_USER=
export MQTT_PASSWORD=
export MQTT_HOST=
export MQTT_PORT=
```

topic to change distance:
    frajola/distance

environment to access ibm watson
```
export IBM_API_KEY=
```

## Examples

```
   $ java -jar frajola-0.1.0-standalone.jar &
   $ python3 lib/main.py
```

## Debian

Gerando pacote debian.
* primeiro é necessário gerar o jar da aplicação
* depois fazer um build da imagem do Docker
* depois executar o container que deverá gerar um `.deb` na pasta `target`
* uma vez enviado para o Raspberry Pi, é necessário fazer o `apt-get install ./frajola_0.1_amd64.deb`

** Não esquecer de atualizar o arquivo `/etc/default/frajola` com as variáveis de ambiente

```
  $ lein ring uberjar
  $ docker build -t frajola .
  $ docker run --rm -v ${PWD}/target/:/data frajola
```


## License

Copyright © 2017 Ederson de Lima

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.


