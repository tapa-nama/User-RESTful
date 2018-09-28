#### Sort People & User-Contact

**1.给User(id=5)创建一个Contact**


    POST /api/users/{id}/contacts    RequestBody contact
**2.查询User(id=5)的Contacts**


    GET /api/users/{id}/contacts
**3.更新User(id=5)的某个Contact**


    PUT /api/users/{userId}/contacts/{contactId}  RequestBody contact
**4.删除User(id=5)的某个Contact**


    DELETE /api/users/{userId}/contacts/{contactId}
**5.查询User(name=sjyuan)的Contact(name=yang kaiguang)**

业务中一般会使用ID而非name来取资源：

    GET /api/users/{userId}/contacts/{contactId}

若要使用name取资源，仍采用包含上下文关系的URL：

    GET /api/users/{userName}/contacts/{contactName}
    
而不是

    GET  /api/users/contacts?userName="userName"&contactName="contactName"
    

