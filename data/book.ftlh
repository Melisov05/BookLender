<!DOCTYPE html>
<html>
<head>
    <title>${book.name}</title>
</head>
<body>
    <h1>${book.name}</h1>
    <p><strong>Автор:</strong> ${book.author}</p>
    <p><strong>Статус:</strong> <#if book.isGiven()>Выдана<#else>В наличии</#if></p>
    <img src="${book.imagePath}" alt="Cover image for ${book.name}" style="width:100px; height:auto;">
</body>
</html>
