#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/mike.pem \
      target/webhook-0.0.1-SNAPSHOT.jar \
      tripoli80@85.238.101.101:/home/tripoli80/
echo 'Restart server...'

ssh -tt -i ~/.ssh/mike.pem tripoli80@85.238.101.101 <<EOF
pgrep java | xargs kill -9
nohup java -jar webhook-0.0.1-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'