#!/bin/bash

while :
do
  echo "Waiting for BETestLib_PU to start ..."
  sleep 10
  curl -s http://localhost:8989/testservice/test/function?preproc=true
  if [ $? -eq 0 ]
  then
    echo "BETestLib_PU is active."
    break
  fi
done
