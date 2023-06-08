package cart.exception;

import cart.domain.Member;

public class CouponException extends RuntimeException {

    public CouponException(String message) {
        super(message);
    }

    public static class WrongDiscountType extends CouponException {
        public WrongDiscountType(String input) {
            super("잘못된 할인정책 명이 입력되었습니다. - " + input);
        }
    }

    public static class AlreadyUsed extends CouponException {
        public AlreadyUsed() {
            super("이미 사용된 쿠폰입니다.");
        }
    }

    public static class AlreadHaveSameCouponException extends CouponException {
        public AlreadHaveSameCouponException() {
            super("이미 가지고 있는 쿠폰입니다.");
        }
    }

    public static class IllegalMember extends CouponException {
        public IllegalMember(Member member) {
            super("쿠폰의 소유자 아닙니다. 접근한 사용자 : " + member.getEmail());
        }
    }

    public static class NotFound extends CouponException {
        public NotFound() {
            super("쿠폰 정보를 찾을 수 없습니다.");
        }
    }
}
