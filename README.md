# Дипломный проект по курсу «Разработчик-Тестировщик»
## О проекте
В рамках данного проекта необходимо автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Покупка тура возможна с помощью карты и в кредит. Данные по картам обрабатываются отдельными сервисами (Payment Gate, Credit Gate)

Полные условия и исходные данные описанного кейса можно посмотреть [https://docs.google.com/document/d/1oilPBsQtBTNY_OlCgjg1Qo9EfMLxkvnf/edit] #ссылка на дипломную работу

## Документация 

[Отчёт о проведённом тестировании] https://docs.google.com/presentation/d/1s4grt0-Odj6sDnSK1xg9F0n_i2dUp0xA/edit?usp=sharing&ouid=101188291838802488304&rtpof=true&sd=true

[Комплексный отчёт о проведённой автоматизации тестирования] https://drive.google.com/file/d/19D6PSwbiSyMaLuzAZVKFVsOIw3o90L6J/view?usp=sharing

## Запуск приложения

Перед запуском необходимо выполнить следующие предусловия. Если у вас уже есть необходимое ПО, то понадобится только п.1 и запуск Docker.

*Предусловия:*
1. Необходимо склонировать репозиторий git@github.com:Marya373/Diplom_QA.git или скачать архив по [https://drive.google.com/drive/folders/1EIIDldDva1-KUqFJ5V9QP6PbWN_WRNdk?usp=drive_link]. Или воспользоваться VCS Git, встроенной в IntelliJ IDEA.
2. Установать и запустить Docker Desktop. Это можно сделать [здесь](https://docs.docker.com/desktop/) в зависимости от операционной системы. 
3. Открыть проект в IntelliJ IDEA

### Запуск
1. Запустить необходимые базы данных (MySQL и PostgreSQL), а также NodeJS. Параметры для запуска хранятся в файле `docker-compose.yml`. Для запуска необходимо ввести в терминале команду:
```
docker-compose up
```
2. В новой вкладке терминала ввести следующую команду в зависимости от базы данных
- `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar` - для MySQL
- `java -Dspring.datasource-postgresql.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar` - для PostgreSQL
3. Приложение должно запуститься 
![app run] ссылка на запуск
работать по адресу http://localhost:8080/

## Запуск тестов
1. В новой вкладке терминала ввести команду в зависимости от запущенной БД в п.2 Запуска:
- `gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app` - для MySQL
- `gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app` - для PostgreSQL

## Запуск отдельных тестовых классов
Чтобы не запускать все тесты разом, предусмотрено два варианта запуска отдельных тестовых классов:
### Вариант 1
1. В `build.gradle` изменить адрес БД. Для этого нужно заменить строчку `systemProperty 'db.url', System.getProperty('db.url')` на:
- `systemProperty 'db.url', System.getProperty('db.url', 'jdbc:mysql://localhost:3306/app')` - для MySQL
- `systemProperty 'db.url', System.getProperty('db.url', 'jdbc:postgresql://localhost:5432/app')` - для PostgreSQL
2. Запустить приложение (раздел "Запуск", в зависимости от БД)
3. Запустить необходимый тестовый класс командой в терминале: `gradlew clean test --tests PayHappyPathTest` , где PayHappyPathTest - тестовый класс, подлежащий запуску. Или запустить необходимый тестовый класс через IDE с помощью команды Run

### Вариант 2
1. В `build.gradle` в раздел test добавить следующее:
    ```
    filter {
        includeTestsMatching('*PayHappyPathTest')
    }
    ```
где PayHappyPathTest - тестовый класс, подлежащий запуску

2. Выполнить раздел "Запуск"

3. Выполнить раздел "Запуск тестов"

## Перезапуск приложения и тестов
Если необходимо перезапустить приложение и/или тесты (например, для другой БД), необходимо выполнить остановку работы в запущенных ранее вкладках терминала, нажав в них Ctrl+С
    
## Формирование отчета AllureReport по результатам тестирования
В новой вкладке терминала или нажав двойной Ctrl ввести команду:
```
gradlew allureServe
```
Сгенерированный отчет откроется в браузере автоматически. После просмотра и закрытия отчета можно остановить работу команды, нажав Ctrl+С или закрыть вкладку Run и нажать Disconnect.
