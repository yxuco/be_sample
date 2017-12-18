@echo off

:loop
echo "Waiting for BETestLib_PU to start ..."
ping localhost -n 10 >nul
curl -s http://localhost:8989/testservice/test/function?preproc=true
if %ERRORLEVEL% NEQ 0 (
  goto loop
)
echo "BETestLib_PU is active."