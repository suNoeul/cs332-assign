# Clean Code Study

## Chapter 2 : Meaningful Names

**1. Use Intention-Revealing Names**

이름은 그 목적이 무엇인지 명확히 드러내야 함. 변수, 함수, 클래스의 이름이 무엇을 하는지, 왜 존재하는지, 어떻게 사용되는지를 설명해야 함. 주석이 필요할 정도라면 이름이 의도를 드러내지 않는 것임.

**2. Avoid Disinformation**

혼란을 줄 수 있는 이름을 피해야 함. 변수나 함수의 이름이 일반적인 용어와 혼동되지 않도록 주의해야 함.

**3. Make Meaningful Distinctions**

이름은 명확한 의미를 가져야 하며, 사소한 차이만으로 다른 이름을 사용하지 않아야 함. 같은 의미를 가진 이름에 숫자나 불필요한 단어를 추가해서는 안 됨.

**4. Use Pronounceable Names**

발음할 수 있는 이름을 사용하는 것이 좋음. 발음할 수 없는 이름은 코드 리뷰나 토론에서 어려움을 초래할 수 있음.

**5. Use Searchable Names**

검색 가능한 이름을 사용하는 것이 좋음. 한 글자짜리 이름이나 숫자 상수는 코드에서 검색하기 어렵기 때문에 피해야 함.

**6. Avoid Encodings**

변수 이름에 타입이나 범위 정보를 인코딩하지 않아야 함. 현대적인 IDE는 이러한 정보를 자동으로 제공하기 때문에 불필요한 인코딩은 가독성을 해칠 수 있음.

**7. Avoid Mental Mapping**

독자가 이름을 다른 의미로 변환하지 않도록 해야 함. 이름은 가능한 한 명확하고 직관적으로 작성해야 함.

- **Class Names**: 클래스와 객체는 명사나 명사구(예: Customer, Account)로 명명해야 함.클래스 이름은 행동이 아니라 역할을 나타내야 함.

- **Method Names**: 메서드는 동사나 동사구(예: postPayment, deletePage)로 이름을 붙여야 함. get, set, is와 같은 표준적인 접두사를 사용하여 접근자, 변경자, 프레디케이트를 명명하는 것이 좋음.


## Chapter 3 : Functions

**1. Small!**

함수는 작아야 함. 작을수록 더 읽기 쉽고 이해하기 쉬움. 대부분의 함수는 20줄을 넘지 않도록 하는 것이 좋음.

**2. Do One Thing**

함수는 하나의 일만 해야 함. 하나의 함수가 여러 가지 일을 한다면, 이를 더 작은 함수로 나누는 것이 좋음.

**3. One Level of Abstraction per Function**

함수 내의 모든 명령문은 동일한 수준의 추상화 레벨에 있어야 함. 서로 다른 수준의 추상화가 혼합되면 함수의 의도를 이해하기 어렵게 됨.

**4. Switch Statements**

switch문은 되도록 피해야 함. 사용해야 한다면, 상속 구조에서 다형성을 사용하여 숨겨야 함.

**5. Use Descriptive Names**

함수의 이름은 그 기능을 명확히 설명해야 함. 긴 이름이 짧고 이해하기 어려운 이름보다 나음.

**6. Function Arguments**

함수의 인자 수는 최소화해야 함. 이상적인 인자 수는 0개이며, 3개 이상의 인자는 피하는 것이 좋음.

**7. Have No Side Effects**

함수는 부작용이 없어야 함. 함수가 약속한 일 외에 다른 일을 하면 안 됨.

**8. Command Query Separation**

함수는 어떤 일을 하거나 질문을 해야지, 둘 다 동시에 하지 않아야 함.

**9. Prefer Exceptions to Returning Error Codes**

오류 코드를 반환하는 것보다 예외를 사용하는 것이 좋음. 예외를 사용하면 에러 처리 코드가 정상 흐름 코드와 분리될 수 있음.

**10. Don't Repeat Yourself (DRY)**

코드 중복을 피해야 함. 중복은 유지보수와 수정 시 문제를 일으킬 수 있음.

