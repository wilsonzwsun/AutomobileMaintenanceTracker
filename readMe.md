# Automobile Maintanence Tracker (demo)

## env
 - springboot 2.4.2 + mysql 5
 - Java ： JDK 1.8
 - Maven ：4.0.0
 - IDE ： IDEA
 - unit test: junit 4.13 \ mockmvc
 ----
 ## Features
  * list car and car maintenance info 
  * save car and car maintenance  into db
  * update car's record
  * remove car's record
 
## Restful API
```
listing : query maintenance record by id
[GET] /api/v{version}/cars/{id}/list
[GET] /api/v{version}/cars/{id}/maintenance/list

adding : insert a car info or maintenance record
[POST]/api/v{version}/cars/save    with carjson @RequestBody
[POST]/api/v{version}/cars/{id}/maintenance/save  maintenance record with carmaintenancejson @RequestBody

updating : a car info or maintenance record 
[PUT] /api/v{version}/cars/{id}/{token}/update   carjson @RequestBody
[PUT] /api/v{version}/cars/{id}/{token}/maintenance/update  maintenance record

deleting : delete a record 
[DELETE] /api/v{version}/cars/{id}/{token}/delete
[DELETE] /api/v{version}/cars/{id}/{token}/maintenance/delete

```
----  
     
 ## improvement
  It's simple demo. Improvement are too many.... 