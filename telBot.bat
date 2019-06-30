call mvn -DskipTests=true clean package
call java -jar -Dfile.encoding=UTF-8 -Dtelegram.bot.name="" -Dtelegram.bot.token="" target/demo-0.1.jar
