@echo off

@REM target下のjavadoc.jar、source.jar、jarファイルをzip圧縮するバッチファイル
@REM target下のjarファイルを spring-boot-common.zip ファイル名で圧縮する
ECHO jarファイルをzip圧縮します
powershell Compress-Archive -Path ./target/*.jar -DestinationPath spring-boot-common.zip -Force
ECHO zip圧縮しました