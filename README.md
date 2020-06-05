# SoftWiss Test Tasks

### First task: [package](src/test/java/io/testtask/tests/first)
Необходимо создать небольшое тестовое окружение и автоматизировать следующие сценарии.

1) **Сценарий 1**: \
   1.1) Открыть go.dev \
   1.2) Проверить, что Navigation Bar содержит следующие элементы: Why Go, Getting Started, Discover Packages, About

2) **Сценарий 2**:\
   2.1) Открыть go.dev\
   2.2) Выполнить поиск по "setter"\
   2.3) Сделать навигацию и найти пакет "github.com/mikekonan/protoc-gen-setter" (на 4ой странице)\
   2.4) Открыть информацию о пакете\
   2.5) Проверить, что следующие табы представлены на странице: Doc, Overview, Subdirectories, Versions, Imports, Imported By, Licenses\
   2.6) Проверить значения следующих элементов: Published, Version, Module
  
### Second task: 
1) Написать функцию, которая объединяет два целочисленных одномерных массива в один. Исходные массивы лежат в файлах. Результат вывести в консоль. [package](src/main/java/io/testtask/IntArray.java)
    > **Исходные данные**: для каждого массива отдельный файл, разделителем чисел является одинарный пробел\
    **Выходные данные**: одномерный отсортированный массив уникальных значений

2) Написать тесты на функцию, описанную выше: [package](src/test/java/io/testtask/tests/second/IntArrayTest.java)
