## 구현할 기능 목록

- 입력받은 "자판기가 보유하고 있는 금액"으로 동전을 무작위로 생성
- 상품명과 가격, 수량을 입력 받음
  - 가격 : 10원 단위 이어야 한다.
  - 가격 : 100원 보다는 크거나 같아야 한다.
- 투입 금액과 상품 명을 입력받아 구매가 되어야 한다. (잔돈 돌려줄 조건에 도달할 때까지 반복)
  - 조건 : 남은 금액 >= 해당 상품 금액
- 잔돈을 돌려줄 때 현재 보유한 최소 개수의 동전으로 잔돈을 돌려준다.
  - 잔돈 돌려줄 조건
    - 남은 금액이 상품의 최저 가격보다 적음
    - 모든 상품이 소진된 경우
  - 돌려주는 잔돈 조건
    - 입력받은 돈은 잔돈으로 쓰이지 않는다. (동전으로 바뀌지 않음)
    - 잔돈을 반환할 수 없는 경우 잔돈으로 반환할 수 있는 금액만 반환
    - 반환되지 않은 금액은 자판기에 남는다.

## Class(객체)별 구현할 기능 목혹

- Coin
  - [x] getAmount()

- Goods
  - [x] 생성시 조건 확인 #Goods(이름, 가격)
    - 가격 : 10원 단위 이어야 한다.
    - 가격 : 100원 보다는 크거나 같아야 한다.
    - 이름 : 빈칸이 아니어야 한다.
  - [x] isEqualName()
    - 이름이 일치하는지 확인
  - [x] getName()

- VendingMachine
  - [x] 상품 등록 #enrollGoods()
    - 상품의 이름이 중복되는지 확인
    - 이미 등록 되었는지 확인
  - [x] 금액 넣기 #addInputPrice()
  - [x] 보유 동전 주기 #getCoins()
  - [x] 구매 기능 #buyGoods()
    - 상품의 이름이 있는지 확인
    - 상품이 등록 되었는지 확인
    - 상품이 품절되었는지 확인
  - [x] 남은 투입 금액 보여주는 기능 #getInputPrice()
  - [ ] 잔돈 돌려주기 기능 #getChanges()
    - 입력받은 돈은 잔돈으로 쓰이지 않는다. (동전으로 바뀌지 않음)
    - 잔돈을 반환할 수 없는 경우 잔돈으로 반환할 수 있는 금액만 반환
    - 반환되지 않은 금액은 자판기에 남는다.
  - [x] 잔돈 돌려줄 조건이 만족하는지 확인 #isDoneBuying()
    - 남은 금액이 **구매 가능한** 상품의 최저 가격보다 적음
    - 모든 상품이 소진된 경우

- InitialCoinCreator
  - [x] initializeCoinCondition()
    - 초기 금액을 받아 Coin 개수를 생성함
    - 가장 작은 단위로 나누어 떨어지지 않을 경우 예외를 발생시킴
    - `camp.nextstep.edu.missionutils.Randoms.pickNumberInList()` 이용

- Dto 객체들
  - [x] GoodsDto (이름, 가격, 개수)

- Mapper
  - [x] #goodsDtosToGoods()

- InputView
  - [x] 자판기 보유 금액 입력 #readHoldingAmount()
  - [ ] 상품명과 가격, 수량 입력 #readGoods()
  - [x] 투입 금액 입력 #readInputAmount()
  - [ ] 구매할 상품명 입력 #readGoodsName()

- OutputView
  - [ ] 자판기 보유 동전 출력 #printHodingCoins()
    ```text
    자판기가 보유한 동전
    500원 - 0개
    100원 - 4개
    50원 - 1개
    10원 - 0개
    ```
  - [ ] 투입 금액 (현재 자판기 남은 투입 금액) 출력 #printInputAmount()
    ```text
    투입 금액: 3000원
    ```
  - [ ] 잔돈 출력 #printChanges()
    ```text
    잔돈
    100원 - 4개
    50원 - 1개
    ```

- VendingMachineApplication
  - [x] 실질적인 기능 코드 구현
    - "프로그래밍 실행 결과 예시"를 참고하여 기능 순서 구현