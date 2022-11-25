# Bookservice
Bookservice - приложение для поиска книг. Позволят производить поиск по id, названию книги, id автора, имени автора.
Реализована Backend часть приложения. Для работы с ним вы можете использовать Postman. 
При запросе приложение возвращает пользователю следующую информацию: id, book name, book description, author name. 

# Начало работы

## Подключение БД
  Приложение использует БД PostgreSQL.
Для подключения к БД, в файле application.yml укажите настройки:

```yaml
# Порт на котором стартует приложение
server:
  port: 8080

# Подключение к БД
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/bookservice
    username: <your username>
    password: <your password>
```

## REST Endpoints
```rest
/books - возвращает список всех книг
/books/{id} - возвращает книгу по id
/books/name?name=<book name> - возвращает список книг по названию без учета регистра
/books/authors/{id} - возвращает список книг по id автора
/books/authors/name?name=<author name> - возвращает список книг по имени автора без учета регистра
```

