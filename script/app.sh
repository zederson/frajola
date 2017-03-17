#!/bin/bash
set -e

# =================================================================================================
# Script para executar a aplicacao                                                         ========
# ao subir a aplicacao eh gerado dois arquivos com o pid para controlar a execucao         ========
# ./app.sh start - faz a execucao sobe o servico web e executa o script python lib/main.py ========
# ./app.sh stop  - para os processos                                                       ========
# =================================================================================================

NAME=frajola
JAR_PATH="../target/frajola-0.1.0-standalone.jar"
PYTHON_APP_PATH="../lib/main.py"

APP_PATH=`pwd`
FRAJOLA_JAVA_PID_FILE="$APP_PATH/.frajola_clojure.pid"
FRAJOLA_PYTHON_PID_FILE="$APP_PATH/.frajola_python.pid"

case "$1" in
  start)
    echo "Iniciando o $NAME"

    if ([ -f $FRAJOLA_JAVA_PID_FILE ] || [ -f $FRAJOLA_PYTHON_PID_FILE ])
    then
      echo "$NAME esta em execucao"
      echo "Tente executar app.sh stop"
      exit 1
    fi

    # carrega as variáveis de ambiente
    if [ -f ./default ]
    then
      echo "Carregando as variavies de ambiente"
      source ./default
    else
      echo "Nao carregou variaveis de ambeinte"
      echo "Para que o sistema funcione eh importante carregalas"
    fi

    # validando se as variaveis do twitter estao configuradas
    if ([ -n "${TWITTER_CONSUMER_KEY+set}" ] && [ -n "${TWITTER_CONSUMER_SECRET+set}" ] && [ -n "${TWITTER_TOKEN+set}" ] && [ -n "${TWITTER_TOKEN_SECRET+set}" ])
    then
      echo "Twitter esta configurado"
    else
      echo "Variaveis de ambiente para acesso ao Twitter nao esta configurado."
      echo "consulte https://github.com/zederson/frajola#configs"
      exit 1
    fi

    # validando se as variaveis do MQTT estao consfiguradas
    if ([ -n "${MQTT_USER+set}" ] && [ -n "${MQTT_PASSWORD+set}" ] && [ -n "${MQTT_HOST+set}" ] && [ -n "${MQTT_PORT+set}" ])
    then
      echo "MQTT esta configurado"
    else
      echo "Variaveis de ambiente para acesso ao ao MQTT nao esta configurado."
      echo "consulte https://github.com/zederson/frajola#configs"
      exit 1
    fi

    # verifica se existe o JAR
    if [ -f $JAR_PATH ]
    then
      echo "O JAR do $NAME sera executado em $JAR_PATH"
    else
      echo "\nJAR NAO ENCONTRADO para $JAR_PATH"
      echo "Coloque o JAR em $JAR_PATH ou altere o valor JAR_PATH nesse script para o path do seu jar"
      exit 1
    fi

    # executa o JAR
    java -jar $JAR_PATH&
    PID_TEMP=$!
    echo "aplicacao Clojure gerou o processo $PID_TEMP"
    echo $PID_TEMP > $FRAJOLA_JAVA_PID_FILE

    # executa o script python
    python3 $PYTHON_APP_PATH&
    PID_TEMP=$!
    echo "aplicacao Python gerou o processo $PID_TEMP"
    echo $PID_TEMP > $FRAJOLA_PYTHON_PID_FILE

  ;;
  stop)
    echo "Parando o $NAME"

    if ([ -f $FRAJOLA_PYTHON_PID_FILE ])
    then
      PID_TEMP=$(cat $FRAJOLA_PYTHON_PID_FILE)
      rm $FRAJOLA_PYTHON_PID_FILE
      kill $PID_TEMP
    fi

    if ([ -f $FRAJOLA_JAVA_PID_FILE ])
    then
      PID_TEMP=$(cat $FRAJOLA_JAVA_PID_FILE)
      rm $FRAJOLA_JAVA_PID_FILE
      kill $PID_TEMP
    fi
    echo "$NAME parado"
  ;;
esac

exit 0
