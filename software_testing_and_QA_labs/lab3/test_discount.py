# test_discount.py
from discount import get_discount

def test_member_discount():
    assert get_discount(True) == 10