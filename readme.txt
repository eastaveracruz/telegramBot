Запуск приложения

В telBot.bat задать параметры telegram.bot.name telegram.bot.token
Запустить telBot.bat


---------------------------------------------------------


REST HTTP-запросы

create()    - put "/rest" + json{"cityName", "description"}
update()    - put "/rest" + json{"id", "cityName", "description"}
getAll()    - get "/rest"
get()       - get "/rest/{name}"
delete()    - delete "/rest/name/{name}"
delete()    - delete "/rest/id/{id}"
delete()    - delete "/rest/object" + json{"id", "cityName", "description"}

count()     - get "/rest/count")