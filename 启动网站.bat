@echo off
chcp 65001 >nul
cd /d "%~dp0"
if not exist "%~dp0runtime-java\bin\java.exe" (echo 缺少便携Java运行环境。& pause & exit /b 1)
if not exist "%~dp0dongbei-specialty-shop-1.0.0.jar" (echo 缺少网站程序文件。& pause & exit /b 1)
start "东北特产网站服务" /min "%~dp0runtime-java\bin\java.exe" -jar "%~dp0dongbei-specialty-shop-1.0.0.jar"
timeout /t 5 /nobreak >nul
start "" http://localhost:8080
echo 网站已启动。请保留最小化的服务窗口，关闭该窗口即可停止网站。
