<!DOCTYPE html>
<html>
<head>
    <title>Employee Profiles</title>
</head>
<body>
    <#list employees as employee>
        <h1>Profile of ${employee.name}</h1>
        <h2>Current Books</h2>
        <#if employee.currentBooks?has_content>
            <ul>
                <#list employee.currentBooks as book>
                    <li>${book.name} by ${book.author} - <img src="${book.imagePath}" alt="Book cover" style="height:100px;"></li>
                </#list>
            </ul>
        <#else>
            No current books.
        </#if>

        <h2>Past Books</h2>
        <#if employee.pastBooks?has_content>
            <ul>
                <#list employee.pastBooks as book>
                    <li>${book.name} by ${book.author} - <img src="${book.imagePath}" alt="Book cover" style="height:100px;"></li>
                </#list>
            </ul>
        <#else>
            No past books.
        </#if>
    </#list>
</body>
</html>
