@echo off

start "BETestLib_PU" ${tibco.be.home}/bin/be-engine --propFile ./be-engine-window.tra -n BETestLib_PU -c ../src/main/BETestLib/Deployments/BETestLib.cdd -u default ./${project.build.finalName}.ear
