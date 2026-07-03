from auth_system import validate_password
from auth_system import get_discount

def test_password_too_short():
    # Inputting a 5-character string should return False
    result = validate_password("12345")
    assert result is False

def test_password_exactly_eight():
    # Inputting exactly 8 characters should return True
    result = validate_password("abcdefgh")
    assert result is True

def test_password_long():
    # Inputting a 12-character string should return True
    result = validate_password("secure_pass1")
    assert result is True

def test_senior_discount():
    discount = get_discount(66)
    assert discount == 0.20

def test_senior_discount_at_boundary():
    discount = get_discount(65)
    assert discount == 0.20

def  test_regular_discount_below_boundary():
    discount = get_discount(64)
    assert discount == 0.00