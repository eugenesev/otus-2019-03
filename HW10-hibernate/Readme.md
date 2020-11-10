# OTUS Java Homework 10
## Использование Hibernate

**Задание**

Работа должна использовать базу данных H2.

Возьмите за основу предыдущее ДЗ ([Самодельный ORM](https://github.com/eugenesev/otus-2019-03/tree/master/HW09-jdbc-template))
и реализуйте функционал сохранения и чтения объекта User через Hibernate.
(Рефлейсия больше не нужна)

Конфигурация Hibernate должна быть вынесена в файл.
Добавьте в User поля:
адрес (OneToOne)
  `class AddressDataSet {
  private String street;
  }`
и телефон (OneToMany)
  `class PhoneDataSet {
  private String number;
  }`

Разметьте классы таким образом, чтобы при сохранении/чтении объека User каскадно сохранялись/читались вложенные объекты.
Не забывайте про сохранение абстракций в приложении (см. комментарий в вебинаре).

[![Структура проекта в IDEA](https://github.com/eugenesev/otus-2019-03/blob/master/img/HW-10.png)](https://github.com/eugenesev/otus-2019-03/tree/master/HW10-hibernate)
