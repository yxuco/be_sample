#!/bin/bash
${tibco.be.home}/bin/be-engine --propFile ./be-engine.tra -n BETestLib_PU -c ../src/main/BETestLib/Deployments/BETestLib.cdd -u default ./${project.build.finalName}.ear
