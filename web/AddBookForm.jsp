<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
    </head>
    <body>
        <form action="AddProduct" ENCTYPE="MULTIPART/FORM-DATA" id="addBookForm" method="POST">
            <table align="center" style="width: 50%;">
                <tr>
                    <td align="center">
                        <br><br>
                        <h1>Add Book</h1>
                        <br>
                        <input type="text" name="bookName" placeholder="Book Name"style="width: 40%;" required >
                        <br>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <input type="text" name="bookAuthor" placeholder="Author Name"style="width: 40%; " required>
                        <br>
                    </td>  
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <input type="number" name="quantity" placeholder="Quantity"style="width: 40%; " required >
                        <br>
                    </td>  
                </tr>
                 <tr>
                    <td align="center">
                        <br>
                        <input type="number" name="price" placeholder="Price"style="width: 40%; " required >
                        <br>
                    </td>  
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        Category
                        <select name="category" id="categories" >
                            <c:forEach items="${sessionScope.allcategories}" var="myCategory" varStatus="stat">
                                <fmt:parseNumber var="i" type="number" value="${stat.index}" ></fmt:parseNumber> 
                                <option value="${myCategory.name}">${myCategory.name}</option>
                            </c:forEach>
                        </select>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <textarea name="desc" form="addBookForm" placeholder="Write a description about the book ..." style="width: 50%; height:200px " required></textarea>   
                    </td>             
                </tr>

                <tr>     
                    <td align="center">
                        <br>
                        <h3>Choose a Cover Photo</h3> 
                        <h6><INPUT TYPE=FILE Name=fileName align="right" title="Choose a Cover Photo" required></h6>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br>
                        <input type="submit" value="Submit">
                        <br>
                        <br>    
                    </td>
                </tr>
            </table>
            <br>
        </form>
    </body>
</html>
