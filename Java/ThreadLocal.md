## 동시성 문제

하나의 인스턴스에 여러 쓰레드가 동시에 접근하는 경우 발생

![image](https://user-images.githubusercontent.com/57944099/208605176-598bd7d8-dcfc-4933-8c84-084b3078a629.png)

### 특징

- 지역변수에선 발생하지 않음 (stack 영역은 상관없음)
- 같은 인스턴스의 필드 (주로 singleton인 경우) / static 필드인 경우 발생할 수 있음
- 값을 읽기만 하면 상관없음, 값을 변경할 때가 문제다

## ThreadLocal

해당 쓰레드만 접근할 수 있는 특별한 저장소

- 같은 인스턴스의 쓰레드 로컬 필드에 접근해도 문제 없음

### 사용법

- 선언 & 초기화 : `ThreadLocal<String> nameStore = new ThreadLocal<>();`
- 값 저장 : `set`
- 값 조회 : `get`
- 값 제거 : `remove` -> **요청이 끝난 후 꼭 해제할 것..!!**
