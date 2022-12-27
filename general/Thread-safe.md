## Thread-safe 하다?

> 어떤 클래스의 인스턴스가 여러 쓰레드에 의해 동시 참조 operation 되더라도 데이터의 정합성을 유지함.<br>
> 개발자가 의도한대로, 안정적으로 동작함 <br>
> 멀티쓰레드 환경에서 필수적인 요소 !!

### 관련 개념

- Spring bean : 싱글톤 객체이기 때문에 Thread-safe 하지 않음. 개발자가 멀티스레드 환경에 대해 고려하면서 프로그래밍할 필요가 있다 !

  - ex. 인스턴스 필드는 읽기 전용으로 ! setter 함부로 사용 X (불변성 고려)

- thread-safe 보장 :
  - ThreadLocal
  - ConcurrentHashMap (vs. HashMap)
