# test_calculator.py
from calculator import check_age

def test_adult():
    assert check_age(20) == "adult"


