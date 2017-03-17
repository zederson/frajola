#!/bin/bash
set -e

# =================================================================================================
# faz o deploy da aplicao, fazendo a execucao do ./build.sh e gerando os arquivos necessarios======
# em um ZIP (frajola.zip).                                                =========================
#  ./deploy.sh -r  (executa o SCP para o destino remoto)                  =========================
#  ./deploy.sh -r ip user  (executa o SCP para o ip e usuario informado)  =========================
# =================================================================================================

echo "Iniciando o deploy ...."

APP_PATH=`pwd`
TEMP_PATH="$APP_PATH/frajola_deploy"
ZIP_NAME="frajola.zip"

IP="192.168.25.8"
USER="pi"

echo "Gerando o artefato"
# sh build.sh

cd ../

if [ -d $TEMP_PATH ]
then
  rm -Rf $TEMP_PATH
fi

echo "copiando os arquivos necessarios"

mkdir -p $TEMP_PATH/target
mkdir -p $TEMP_PATH/lib

cp target/frajola*-standalone.jar $TEMP_PATH/target
cp lib/*.py $TEMP_PATH/lib
cp script/app.sh $TEMP_PATH
cp script/default $TEMP_PATH

echo "Compactando arquivos"
cd $TEMP_PATH
zip -r $APP_PATH/$ZIP_NAME app.sh default lib target

rm -Rf $TEMP_PATH

if ([ -n "$2" ] && [ -n "$3" ] )
then
  IP=$2
  USER=$3
fi

while getopts ":r" optname
do
  case "$optname" in
    "r")
      echo "\nEnviando arquivo.... para $USER@$IP "
      echo "scp $APP_PATH/$ZIP_NAME $USER@$IP:/home/$USER/Downloads"
    ;;
  esac
done

echo "\nDeploy feito com sucesso $ZIP_NAME"
exit 0
