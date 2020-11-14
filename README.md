# Проект c автонастройкой инициализации драйвера с dependency injection

Запуск теста локально, использую chromeDriver:
- в application.properties добавить путь к chromedriver.exe
- в application.properties пропертю selenoid.host оставить пустой
- запускаем любой тест в SelenoidTest или все тесты

Запуск теста через удаленный драйвер:
- в application.properties заполненным оставляем selenoid.host
- запускаем любой или все тесты в SelenoidTest

