<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<table border="1">
    <tr>
        <td>id</td>
        <td>author</td>
        <td>name</td>
    </tr>

    <#if bookList ??&&(bookList?size>0)>
        <#list bookList as book>
            <tr>
                <td>${book.id}</td>
                <td>${book.author}</td>
                <td>${book.name}</td>
            </tr>
        </#list>
    </#if>
</table>
</body>
</html>