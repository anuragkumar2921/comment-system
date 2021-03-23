steps to run :-
1. run command - mvn clean install
2. java -jar target/comment-system-0.0.1-SNAPSHOT.jar

api details:-

**1. POST localhost:8080/v1/comment**
"parentId" - if first comment ... enter any postId may be 1...for any subsequest request for reply pass commentId in which reply  is to be added

`_sampleRequest-
{
    "parentId" : "1" 
    "message" :  " comment xyz"
}`_

**2. GET localhost:8080/v1/all**
for pagenated output of all the comments

`__sampleRequest-
 localhost:8080/v1/all?startPage=0&endPage=10_`
 
 **3. GET localhost:8080/v1/comment/{parentId}**
 to get all the subsequence comments for a given comment/reply/post ID
 
 **4. POST localhost:8080/v1/comment/{commentId}**
 to update the comment if no reply is present for the comment/reply ID
 
**5. GET localhost:8080/v1/comment/filter**
 to filter comments based on start time and end time

time format - 2021-03-11T03:30:01.578Z (UTC)

`sample request
{
    "startTime" : "2021-03-11T03:30:01.578Z",
    "endTime" : "2021-03-11T03:30:01.578Z",
    "message" : "xcde"
}`

**6. localhost:8080/v1/comment/sort/{sortType}**
sort comment old to new or new to old

`sortType- NEWEST_FIRST, OLDEST_FIRST
`
 
**7. localhost:8080/v1/comment/sort/{sortType}/{n}**
sort and return n number of comments

`n - 0,1,2,...
`