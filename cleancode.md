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


## Chapter 4 : Comments

### 1. Comments Do Not Make Up for Bad Code
- **나쁜 코드를 주석으로 설명하지 말고**, 코드를 정리해서 주석이 필요 없도록 만듦.

### 2. Explain Yourself in Code
- 주석을 대신해 **코드로 의도를 표현**하는 것이 더 나음.

### 3. Good Comments
- 좋은 주석은 몇 가지 경우에만 필요함:
  - **Legal Comments**: 법적 요구사항을 위한 주석.
  - **Informative Comments**: 정보 제공용 주석 (예: 함수의 반환 값 설명).
  - **Explanation of Intent**: 코드 작성 의도를 설명하는 주석.
  - **Clarification**: 복잡한 인자나 반환 값을 명확히 하는 주석.
  - **Warning of Consequences**: 특정 결과를 경고하는 주석.
  - **TODO Comments**: 나중에 해결할 작업을 나타내는 주석.
  - **Amplification**: 중요한 부분을 강조하는 주석.
  - **Javadocs in Public APIs**: 공개 API를 위한 주석.

### 4. Bad Comments
- 나쁜 주석은 코드를 어지럽히고 오해를 불러일으킴:
  - **Mumbling**: 애매한 주석.
  - **Redundant Comments**: 불필요하게 반복되는 주석.
  - **Misleading Comments**: 잘못된 정보를 제공하는 주석.
  - **Mandated Comments**: 의무적으로 작성된 무의미한 주석.
  - **Journal Comments**: 코드 변경 내역을 기록하는 주석.
  - **Noise Comments**: 불필요한 정보를 제공하는 주석.
  - **Scary Noise**: 코드에 혼란을 주는 주석.
  - **Don’t Use a Comment When You Can Use a Function or a Variable**: 주석 대신 변수나 함수로 표현할 수 있는 경우, 주석 사용을 피함.
  - **Position Markers**: 의미 없는 위치 표시 주석.
  - **Closing Brace Comments**: 중괄호 닫힘을 표시하는 주석.
  - **Attributions and Bylines**: 누가 작성했는지 표시하는 주석.
  - **Commented-Out Code**: 주석 처리된 코드.
  - **HTML Comments**: 코드에 HTML 태그를 사용하는 주석.
  - **Nonlocal Information**: 코드와 관련 없는 시스템 정보가 포함된 주석.
  - **Too Much Information**: 필요 이상의 정보를 제공하는 주석.
  - **Inobvious Connection**: 코드와 주석 간 연결이 명확하지 않은 주석.
  - **Function Headers**: 짧은 함수에 불필요한 주석.
  - **Javadocs in Nonpublic Code**: 비공개 코드에 작성된 Javadoc 주석.


## Chapter 7 : Error Handling

### 1. Use Exceptions Rather Than Return Codes
- 오류를 반환 코드 대신 **예외로 처리**해야 코드가 더 간결하고 가독성이 높아짐.

### 2. Write Your Try-Catch-Finally Statement First
- **try-catch-finally** 블록을 먼저 작성해 오류 처리 경계를 명확히 정의함.

### 3. Use Unchecked Exceptions
- **Unchecked 예외**를 사용해 코드의 캡슐화를 깨지 않도록 함.

### 4. Provide Context with Exceptions
- 예외에는 충분한 **맥락**(실패한 작업과 그 이유)을 제공해 문제를 파악할 수 있도록 함.

### 5. Define Exception Classes in Terms of a Caller’s Needs
- 호출자가 처리하기 쉽게 **예외 클래스를 정의**해 중복된 코드를 줄임.

### 6. Define the Normal Flow
- 예외를 줄이기 위해 **정상적인 흐름**을 처리하는 코드를 단순화함.

### 7. Don’t Return Null
- **null을 반환하지 않음**. 대신 특별한 객체를 반환하거나 예외를 던짐.

### 8. Don’t Pass Null
- **null을 함수 인자로 넘기지 않음**. null을 받으면 오류 발생 가능성이 높아짐.


## Chapter 9: Unit Tests

### 1. The Three Laws of TDD
- TDD는 **테스트를 먼저 작성**하고 그에 맞는 코드를 작성하는 것을 강조하며, 세 가지 법칙이 있음:
   - 실패하는 **유닛 테스트**를 작성하기 전까지는 프로덕션 코드를 작성할 수 없음.
   - 실패하는 테스트를 충족할 만큼만 유닛 테스트를 작성함.
   - **테스트를 통과할 정도**로만 프로덕션 코드를 작성함.

### 2. Keeping Tests Clean
- 테스트 코드는 **프로덕션 코드**만큼 중요하게 유지하고 관리해야 함. 더러워진 테스트는 코드를 수정하기 어렵게 만듦.

### 3. Tests Enable the -ilities
- **유닛 테스트**는 코드의 변경을 두려워하지 않고 유연성, 유지보수성, 재사용성을 보장해줌.

### 4. Clean Tests
- **읽기 쉬운 테스트** 코드는 클린 코드의 핵심이며, 테스트는 **간결하고 명확**해야 함.

### 5. Domain-Specific Testing Language
- **테스트에 맞는 도메인 특화 언어**를 만들어 코드의 가독성을 높임.

### 6. A Dual Standard
- 테스트 코드와 프로덕션 코드는 **다른 기준**이 적용되며, 테스트 코드는 단순하지만 **표현력**을 가져야 함.

### 7. One Assert per Test
- **테스트 함수마다 하나의 assert**만 사용하는 것이 좋지만, 경우에 따라 여러 개의 assert를 사용할 수 있음.

### 8. Single Concept per Test
- 테스트 함수는 **하나의 개념**만 테스트해야 하며, 긴 테스트 함수는 독립된 테스트로 분리해야 함.

### 9. F.I.R.S.T.
- **F**ast: 테스트는 빨라야 함.
- **I**ndependent: 테스트는 서로 의존하지 않음.
- **R**epeatable: 어디서든 실행 가능해야 함.
- **S**elf-Validating: 테스트는 명확한 참/거짓 결과를 반환해야 함.
- **T**imely: 테스트는 **코드 작성 전에** 작성해야 함.


## Chapter 10: Classes

### 1. Class Organization
- 클래스는 **변수 선언**으로 시작하며, **공개 상수**, **비공개 정적 변수**, **인스턴스 변수** 순으로 나열함.
- **공개 함수**는 변수 목록 다음에 오며, **사설 유틸리티**는 관련된 공개 함수 바로 뒤에 위치함.

### 2. Encapsulation
- **캡슐화**를 유지하기 위해 변수와 유틸리티 함수는 가능한 **비공개**로 설정함. 필요 시 테스트를 위해 **보호된** 접근 수준으로 조정 가능함.

### 3. Classes Should Be Small!
- **클래스는 작아야 하며**, 가능한 더 작게 유지해야 함. 작은 클래스가 더 좋은 **책임 분리**와 유지보수를 가능하게 함.

### 4. The Single Responsibility Principle
- **단일 책임 원칙(SRP)**: 클래스는 하나의 책임만 가져야 하며, **변경의 이유**는 하나뿐이어야 함.

### 5. Cohesion
- 클래스는 **높은 응집력**을 가져야 하며, 클래스의 모든 메서드가 클래스의 변수를 활용하는 것이 이상적임.

### 6. Maintaining Cohesion Results in Many Small Classes
- **응집성을 유지하기 위해** 큰 클래스를 여러 작은 클래스로 나누는 것이 좋음.

### 7. Organizing for Change
- 시스템은 계속 **변화**하므로, 클래스를 설계할 때 **변경의 위험을 줄이기** 위해 신중하게 조직해야 함.

### 8. Isolating from Change
- 클래스는 **추상화**에 의존해야 하며, **구체적인 구현**에 의존하면 변경에 취약해짐.

