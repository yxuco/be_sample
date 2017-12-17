#!/bin/bash
${tibco.be.home}/bin/be-engine --propFile ./be-engine.tra -n BETestLib_PU -c ../src/main/BETestLib/Deployments/BETestLib.cdd -u default ./${project.build.finalName}.ear > /dev/null 2>&1 &
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
