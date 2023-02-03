
Технологический стек: REST-сервис на базе Spring Boot, Maven, Lombok, Базы данных, SQL, JPA, Hibernate

Приложение ExploreWithMe - это афиша, где можно предложить какое-либо событие от выставки до похода в кино и набрать 
компанию для участия в нём.
Приложение условно делится на два модуля:
1) Основной сервис — содержит всё необходимое для работы продукта.
API основного сервиса разделите на три части. Первая — публичная, доступна без регистрации любому пользователю сети. 
Вторая — закрытая, доступна только авторизованным пользователям. Третья — административная, для администраторов сервиса. 
К каждой из частей свои требования.
2) Сервис статистики — хранит количество просмотров и позволяет делать различные выборки для анализа работы приложения.
Второй сервис, статистики, призван собирать информацию. Во-первых, о количестве обращений пользователей к спискам 
событий и, во-вторых, о количестве запросов к подробной информации о событии. На основе этой информации должна 
формироваться статистика о работе приложения.

