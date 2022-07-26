# Order-api

## 사용기술
- JAVA 11  
- SPRING BOOT
- Embedded Redis (로그인 로그아웃 기능을 위한)
- Spring Data JPA
- QueryDSL
- Domain에 대한 Junit 테스트코드 작성


### BUILD
```shell
./gradlew build
```
### RUN
```shell
java -jar build/libs/order-api-0.0.1-SNAPSHOT.jar
```
### Swagger 
```
http://localhost:8080/swagger-ui/index.html
```

## API 명세
## 로그인

```json
POST : /api/auth
{
  "email": "이메일",
  "password": "비밀번호"
}
```

- 성공시

```json
HTTP 상태코드 : 200 OK
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiUk9MRSI6IlJPTEVfQURNSU4iLCJpYXQiOjE2NTg4Mzk5ODgsImV4cCI6MTY1ODg2MTU4OH0.fKM80LjfOuij5L1Eis0j02Tut5WfOLoZdI9M9g4CGEqC3yJEM_mPNYT1RfCGlaI0KV5hz7_wABdb9wBgYCqdlA"
}
```

## 로그아웃

- 사전조건 : 로그인이 되어 있어야 함

```json
DELETE : /api/auth
```

- 성공시

```json
HTTP 상태코드 : 204 NO-CONTENT
```

## 회원가입

```json
POST : /api/accounts
{
  "name": "이름",
  "nickname": "닉네임",
  "password": "비밀번호",
  "phoneNumber": "전화번호",
  "email": "이메일",
  "accountGender": "성별"
}
```

- 요청 body
    - name : 이름 ( 한글 영문 대소문자만 가능)
    - nickname: 닉네임 (영문 소문자만 가능)
    - password : 비밀번호(영어 소대문자, 특수문자 숫자 각각 한개씩 포함)
    - phoneNumber : 전화번호 숫자만 입력가능
    - email : 이메일 형식 입력
    - accountGender : 성별 (미입력시 NONE으로 설정) `NONE` , `MALE` , `FEMALE` 만 입력가능
- 성공시

```json
HTTP 상태코드 : 201 Created
{
	"id": 12,
	"name": "김성호",
	"nickname": "asdfasdf",
	"phoneNumber": "123123123123124",
	"email": "test11we@test.com",
	"accountGender": "NONE"
}
```

## 내 정보 조회

- 사전조건 : 로그인이 되어 있어야 함

```json
GET : /api/accounts
```

- 성공시

```json
HTTP 상태코드 : 200 
{
	"id": 12,
	"name": "김성호",
	"nickname": "asdfasdf",
	"phoneNumber": "123123123123124",
	"email": "test11we@test.com",
	"accountGender": "NONE"
}
```

## 상품등록

```json
POST : /api/products
{
	"productName":"노트북🧑🏻‍💻"
}
```

- 요청 body
    - productName : 이름 ( 한글 영문 대소문자만 가능)
- 성공시

```json
HTTP 상태코드 : 201 Created
{
	"productId": 16,
	"productName": "노트북🧑🏻‍💻",
	"createdAt": "2022-07-26T22:07:05.183609"
}
```

## 상품단건 조회

- 사전조건 : 없음

```json
GET : /api/products/{producstId}
```

- 요청 Pathvariable
    - 상품의 id

- 성공시

```json
HTTP 상태코드 : 200 
{
	"productId": 16,
	"productName": "노트북🧑🏻‍💻",
	"createdAt": "2022-07-26T22:07:05.183609"
}
```

## 상품 전체 조회 (페이징)

- 사전조건 : 없음

```json
GET : /api/products?size=5&pageNumber=2
```

- 요청 RequestParam
    - size : 한 페이지에 가져올 요소의 갯수(defalut : 5)
    - pageNumber : 페이지의 번호 0부터 시작(defalut : 0)

- 성공시

```json
{
	"content": [
		{
			"productId": 16,
			"productName": "상품1🧑🏻‍💻 ",
			"createdAt": "2022-07-26T22:07:05.183609"
		},
		{
			"productId": 15,
			"productName": "오후상품",
			"createdAt": "2022-07-26T22:06:15.285471"
		},
		{
			"productId": 14,
			"productName": "오후1상품",
			"createdAt": "2022-07-25T17:08:20.388668"
		},
		{
			"productId": 13,
			"productName": "44🧑🏻‍💻🤟test",
			"createdAt": "2022-07-25T14:33:35.283732"
		},
		{
			"productId": 12,
			"productName": "2🧑🏻‍💻🤟tes323232t",
			"createdAt": "2022-07-25T08:04:36.842424"
		}
	],
	"pageable": {
		"sort": {
			"sorted": false,
			"unsorted": true,
			"empty": true
		},
		"pageNumber": 0,
		"pageSize": 5,
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"totalPages": 4,
	"totalElements": 16,
	"last": false,
	"numberOfElements": 5,
	"first": true,
	"number": 0,
	"sort": {
		"sorted": false,
		"unsorted": true,
		"empty": true
	},
	"size": 5,
	"empty": false
}
```

- content : 검색된 product요소
    - productId : 상품의 id
    - productName : 상품의 이름
    - createdAt : 상품 등록 날짜
- totalPages 총페이지 갯수
- totalElements: 총요소의 갯수
- numberOfElements: 검색된 요소의 갯수

## 주문

- 사전조건 : 로그인

```json
POST : /api/orders/products/{productId}
```

- 요청 Pathvariable
    - 상품의 id

- 성공시

```json
{
	"orderNumber": "576rShGoVls5qvmYwSXM",
	"productName": "2🧑🏻‍💻🤟test",
	"ordererId": 2,
	"orderEmail": "test@test.com",
	"payment": false,
	"orderDate": "2022-07-26T22:22:21.031253"
}
```

- orderNumber : 주문번호
- productName : 주문한 상품 이름
- ordererId : 주문자의 id
- orderEmail : 주문자의 email
- payment: 결제정보
- orderDate: 주문한 시간

## 결제

- 사전조건 : 로그인

```json
POST : /api/orders/{orderNumber}
```

- 요청 Pathvariable
    - 주문 번호

- 성공시

```json
{
	"orderNumber": "fqcprsQxf1Iv9gfikb5X",
	"productName": "2🧑🏻‍💻🤟test",
	"ordererId": 2,
	"orderEmail": "test@test.com",
	"payment": true,
	"paymentDate": "2022-07-26T13:25:18.04562Z",
	"orderDate": "2022-07-26T22:25:11.816664"
}
```

- orderNumber : 주문번호
- productName : 주문한 상품 이름
- ordererId : 주문자의 id
- orderEmail : 주문자의 email
- payment: 결제정보
- paymentDate : 주문한 시간 UTC 표준시
- orderDate: 주문한 시간

## 주문 정보 조회

- 사전조건 : 로그인

```json
GET : /api/orders/{orderNumber}
```

- 요청 Pathvariable
    - 주문 번호

- 성공시(결제를 완료한 사용자)

```json
{
	"orderNumber": "fqcprsQxf1Iv9gfikb5X",
	"productName": "2🧑🏻‍💻🤟test",
	"ordererId": 2,
	"orderEmail": "test@test.com",
	"payment": true,
	"paymentDate": "2022-07-26T13:25:18.04562Z",
	"orderDate": "2022-07-26T22:25:11.816664"
}
```

- 성공시(결제를 하지 않은 사용자)

```json
{
	"orderNumber": "VY1H1mGmwpurCuI37AbW",
	"productName": "2🧑🏻‍💻🤟test",
	"ordererId": 2,
	"orderEmail": "test@test.com",
	"payment": false,
	"orderDate": "2022-07-26T22:28:09.829109"
}
```

- orderNumber : 주문번호
- productName : 주문한 상품 이름
- ordererId : 주문자의 id
- orderEmail : 주문자의 email
- payment: 결제정보
- paymentDate : 주문한 시간 UTC 표준시
- orderDate: 주문한 시간

## 관리자 API

## 회원정보 accountId로 조회

- 사전조건 로그인후 권한이 ROLE_ADMIN 만 사용가능

```json
GET : /api/admin/accounts/{accountId}
```

- 요청 Pathvariable
    - 회원의 db상의 ID : 숫자 타입

- 성공시

```json
HTTP 상태코드 : 200 
{
	"id": 12,
	"name": "김성호",
	"nickname": "asdfasdf",
	"phoneNumber": "123123123123124",
	"email": "test11we@test.com",
	"accountGender": "NONE"
}
```

## 회원정보 이름, email로 조회(페이징)

- 사전조건 로그인후 권한이 ROLE_ADMIN 만 사용가능

```json
GET : /api/admin/accounts?name=김성호&pageNumber=0&size=10
OR
GET : /api/admin/accounts?email=test@test.com&pageNumber=0&size=10
OR
GET : /api/admin/accounts?name=김성호&email=test@test.com&pageNumber=0&size=10
```

- 요청 RequestParam
    - name : 사용자의 이름 (필수 아님)
    - email : 사용자의 이메일 (필수 아님)
    - size : 한 페이지에 가져올 요소의 갯수 (defalut : 5)
    - pageNumber : 페이지의 번호 0부터 시작 (defalut : 0)
- 성공시

```json
{
	"content": [
		{
			"id": 1,
			"name": "김성호",
			"nickname": "asdfasdf",
			"phoneNumber": "123123123123124",
			"email": "test2@test.com",
			"accountGender": "NONE",
			"lastOrderInfo": {
				"orderNumber": "Xl19naw5Hcz3ATJkInZ2",
				"productName": "2🧑🏻‍💻🤟test",
				"payment": false,
				"orderDate": "2022-07-25T03:09:11.50274"
			}
		},
		{
			"id": 2,
			"name": "김성호",
			"nickname": "asdfasdf",
			"phoneNumber": "123123123123124",
			"email": "test@test.com",
			"accountGender": "NONE",
			"lastOrderInfo": {
				"orderNumber": "VY1H1mGmwpurCuI37AbW",
				"productName": "2🧑🏻‍💻🤟test",
				"payment": false,
				"orderDate": "2022-07-26T22:28:09.829109"
			}
		},
		{
			"id": 3,
			"name": "김성호",
			"nickname": "asdfasdf",
			"phoneNumber": "123123123123124",
			"email": "test1@test.com",
			"accountGender": "NONE",
			"lastOrderInfo": {
				"orderNumber": "MsABkHqGfJqqHrjw0n0I",
				"productName": "2🧑🏻‍💻🤟test",
				"payment": false,
				"orderDate": "2022-07-25T03:17:30.572828"
			}
		},
		{
			"id": 5,
			"name": "김성호",
			"nickname": "asdfasdf",
			"phoneNumber": "123123123123124",
			"email": "test3@test.com",
			"accountGender": "NONE",
			"lastOrderInfo": {
				"orderNumber": "ddWBRu7wPIJDPRr6Yp0M",
				"productName": "2🧑🏻‍💻🤟test",
				"payment": false,
				"orderDate": "2022-07-25T03:17:32.025796"
			}
		},
		{
			"id": 6,
			"name": "김성호",
			"nickname": "asdfasdf",
			"phoneNumber": "123123123123124",
			"email": "test4@test.com",
			"accountGender": "NONE"
		}
	],
	"pageable": {
		"sort": {
			"sorted": false,
			"unsorted": true,
			"empty": true
		},
		"pageNumber": 0,
		"pageSize": 5,
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"totalPages": 3,
	"totalElements": 11,
	"last": false,
	"numberOfElements": 5,
	"first": true,
	"number": 0,
	"sort": {
		"sorted": false,
		"unsorted": true,
		"empty": true
	},
	"size": 5,
	"empty": false
}
```

- content : 검색된 account의 요소
    - id : 회원의 db상의 id
    - name : 회원이름
    - nickname : 회원의 닉네임
    - phoneNumber : 전화번호
    - email: 이메일
    - accountGender : 성별
    - lastOrderInfo : 회원의 마지막주문
        - orderNumber : 주문번호
        - productName : 상품명
        - payment : 결제여부
        - paymentDate : 결제시간 UTC 표준시
        - orderDate : 주문시간
- totalPages 총페이지 갯수
- totalElements: 총요소의 갯수
- numberOfElements: 검색된 요소의 갯수

## 회원의 주문목록 조회

- 사전조건 : 로그인

```json
GET : /api/admin/orders/accounts/{accountId}&pageNumber=0&size=5
```

- 요청 Pathvariable
    - 회원의 db상의 id
- 요청 RequestParam
    - size : 한 페이지에 가져올 요소의 갯수 (defalut : 5)
    - pageNumber : 페이지의 번호 0부터 시작 (defalut : 0)

- 성공시(결제를 완료한 사용자)

```json
{
	"content": [
		{
			"orderNumber": "2R23APyhm8si7ll3gx6I",
			"productName": "2🧑🏻‍💻🤟test",
			"ordererId": 2,
			"orderEmail": "test@test.com",
			"payment": true,
			"paymentDate": "2022-07-25T07:42:39.949272+09:00[Asia/Seoul]",
			"orderDate": "2022-07-25T03:17:30.624055"
		},
		{
			"orderNumber": "576rShGoVls5qvmYwSXM",
			"productName": "2🧑🏻‍💻🤟test",
			"ordererId": 2,
			"orderEmail": "test@test.com",
			"payment": true,
			"paymentDate": "2022-07-26T22:24:07.480402+09:00[Asia/Seoul]",
			"orderDate": "2022-07-26T22:22:21.031253"
		},
		{
			"orderNumber": "5Y179jRd7dx2qTZVZsGc",
			"productName": "2🧑🏻‍💻🤟test",
			"ordererId": 2,
			"orderEmail": "test@test.com",
			"payment": false,
			"orderDate": "2022-07-25T03:17:30.15745"
		},
		{
			"orderNumber": "aQnnCcbPOiZgnA2fvlSG",
			"productName": "2🧑🏻‍💻🤟test",
			"ordererId": 2,
			"orderEmail": "test@test.com",
			"payment": false,
			"orderDate": "2022-07-25T03:09:12.142839"
		},
		{
			"orderNumber": "b8cfJ2fWsqDQOw7tTMkV",
			"productName": "2🧑🏻‍💻🤟tes323232t",
			"ordererId": 2,
			"orderEmail": "test@test.com",
			"payment": false,
			"orderDate": "2022-07-25T08:05:23.38213"
		}
	],
	"pageable": {
		"sort": {
			"sorted": false,
			"unsorted": true,
			"empty": true
		},
		"pageNumber": 0,
		"pageSize": 5,
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"totalPages": 6,
	"totalElements": 28,
	"last": false,
	"numberOfElements": 5,
	"first": true,
	"number": 0,
	"sort": {
		"sorted": false,
		"unsorted": true,
		"empty": true
	},
	"size": 5,
	"empty": false
}
```

- content : 검색된 orderInfo의 요소
    - orderNumber : 주문번호
    - productName : 주문한 상품 이름
    - ordererId : 주문자의 id
    - orderEmail : 주문자의 email
    - payment: 결제정보
    - paymentDate : 주문한 시간 UTC 표준시
    - orderDate: 주문한 시간
- totalPages 총페이지 갯수
- totalElements: 총요소의 갯수
- numberOfElements: 검색된 요소의 갯수
