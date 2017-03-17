#!/bin/bash
set -e

# =================================================================================================
# faz o build do projeto. fazendo a geracao do JAR da aplicacao           =========================
# =================================================================================================

BUILD="lein ring uberjar"
APP_PATH="../"
NAME="frajola-0.1.0-standalone.jar"

echo "Fazendo o build da $NAME"

if (cd $APP_PATH && $BUILD)
then
  echo "$NAME gerado com sucesso"
else
  echo "$NAME Falha ao gerar o jar"
  exit 1
fi
exit 0
