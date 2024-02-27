<!DOCTYPE html>
<html>
<head>
    <title>Список книг</title>
</head>
<body>
<h1>Список книг</h1>
<ul>
    <#list books as book>
    <li>${book.name} - Автор: ${book.author}, Статус: <#if book.isGiven()>Выдана <#else>В наличии
    </#if></li>
    <img src="${book.imagePath}" alt="Cover image for ${book.name}" style="width:100px; height:auto;">
</#list>
</ul>
</body>
</html>
