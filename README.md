# spring_lv4

# API 설계

1. User & Profile API

|API 명|Method|URL|Request Cookie|Request|Response|Response Cookie|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|회원가입|POST|api/user/signup||{"username": "ggbeom1234", "password": "rkdqja123”}|{"msg": "회원가입 성공", "statusCode": 200}||
|로그인|POST|api/user/login||{"username": "ggbeom1234", "password": "rkdqja123”}|{"msg": "로그인 성공", "statusCode": 200}|Cookie : {"Authorization" : "Bearer... "}|


2. Post API

|API 명|Method|URL|Request Cookie|Request|Response|Response Cookie|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|전체 게시글 조회|GET|api/post|||[ {"title": "title", "username": "ggbeom1234", "contents": "content", "createdAt": "2023-06-28T10:00:04.032185", "modifiedAt": "2023-06-28T10:00:04.032185", "commentList": [] },  ]||
|선택 게시글 조회|GET|api/post/{id}|||{"title": "title", "username": "ggbeom1234", "contents": "content", "createdAt": "2023-06-28T10:00:04.032185", "modifiedAt": "2023-06-28T10:00:04.032185", "commentList": [] }||
|게시글 생성|POST|api/post|Cookie : {"Authorization" : "Bearer... "}|{"title": "title", "contents": "content"}||
|게시글 수정|PUT|api/post/{id}|Cookie : {"Authorization" : "Bearer... "}|{"title": "good", "contents": "no, bad" }|{"title": "good", "username": "ggbeom1234", "contents": "no, bad", "createdAt": "2023-06-28T09:59:48.081672", "modifiedAt": "2023-06-28T10:00:56.0170722", "commentList": [] }||
|게시글 삭제|DELETE|api/post/{id}|Cookie : {"Authorization" : "Bearer... "}||{"msg": "게시글 삭제 성공", "statusCode": 200}||
|게시글 좋아요 등록|POST|api/post/{id}/like|Cookie : {"Authorization" : "Bearer... "}||{"msg": "게시글 좋아오 성공", "statusCode": 200}||
|게시글 좋아요 해제|DELETE|api/post/{id}/like|Cookie : {"Authorization" : "Bearer... "}||{{"msg": "게시글 좋아요 취소", "statusCode": 200}

3. Comment API

|API 명|Method|URL|Request Cookie|Request|Response|Response Cookie|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|댓글 생성|POST|api/comment|Cookie : {"Authorization" : "Bearer... "}|{"postId": 2, "content": "post"}|{"commentId": 1, "username": "part", "content": "nice post"}||
|댓글 수정|PUT|api/comment/{id}|Cookie : {"Authorization" : "Bearer... "}|{"content": "YKW? it was actually bad.."}|{"commentId": 1, "username": "part", "content": "YKW? it was actually bad.."}||
|댓글 삭제|DELETE|api/comment/{id}|Cookie : {"Authorization" : "Bearer... "}||{"msg": "댓글 삭제 성공", "statusCode": 200}||
|댓글 좋아요 등록|POST|api/comment/{id}/like|Cookie : {"Authorization" : "Bearer... "}||{"msg": "댓글 좋아요 성공", "statusCode": 200}||
|댓글 좋아요 해제|DELETE|api/comment/{id}/like|Cookie : {"Authorization" : "Bearer... "}||{{"msg": "댓글 좋아요 취소", "statusCode": 200}


ERD
![image](https://github.com/bor1gunbbang/spring_lv4/assets/79289862/f2566969-5336-4079-ac02-d367b4715049)

