#!/usr/bin/env bash
# author vhg
# date 1.13.17
# Note: this allows running 2 DPEs in a same node


if [ -z ${1+x} ]; then
echo "Usage:"
echo "clara-dpe <session> [<clara-home> <plugin-dir> <fe-host>]"
echo "<session>         Clara data processing session"
echo
echo "optional parameters:"
echo "<clara-home       Clara home (default = $CLARA_HOME env variable)"
echo "<plugin-dir>      Clara plugin directory (default = $CLAS12DIR env variable)"
echo "<fe-host>         Front-End host name (default = localhost)"
exit;
fi


UPPER=8000
PORT=7000
dpe_port=0
while  [ $dpe_port == 0 ]
do
dpe_port=0
exec 6<>/dev/tcp/127.0.0.1/$PORT || dpe_port=1
if [ $dpe_port == 0 ]; then
let "PORT=PORT+10"
fi
done
echo "$PORT"


if ! [ -z ${2+x} ]; then CLARA_HOME=$2; fi
if ! [ -z ${3+x} ]; then CLAS12DIR=$3; fi
if [ -z ${4+x} ]; then FE_HOST="localhost" ; else FE_HOST=$4; fi

echo $FE_HOST

export CLARA_HOME
export CLAS12DIR
export CCDB_DATABASE="etc/data/database/clas12database.sqlite"
export CLASSPATH="$CLARA_HOME/lib/*:$CLAS12DIR/lib/clas/*:$CLAS12DIR/lib/services/*"


LOG_FILE=$CLARA_HOME/log/$HOST

echo "-------- Running Conditions ---------------"
echo " Start time         = "$(date)
echo " Clara distribution = $CLARA_HOME"
echo " Plugin directory   = $CLAS12DIR"
echo " Log file           = Clara distribution/log/$HOSTNAME-jfe.log"
echo " Note               = Running as local Front-End"
echo "------------------------------------------"
echo
j_dpe --port $PORT --host $FE_HOST --session $1 2>&1 | tee "$LOG_FILE-jfe.log"